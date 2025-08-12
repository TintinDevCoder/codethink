package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    //1表示A 2表示B 3表示C
    private int num=1;
    //创建Lock
    private Lock lock=new ReentrantLock();
    //创建Condition
    Condition conditionA=lock.newCondition();
    Condition conditionB=lock.newCondition();
    Condition conditionC=lock.newCondition();
    //打印A
    public void printA() {
        //上锁
        lock.lock();
        try {
            if (num!=1){
                //如果不是A
                try {
                    //当前线程进入队列等待，并释放锁（也就是不往下走了，直到被唤醒）
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //打印A
            System.out.println("A");
            //将标记该为“B”
            num=2;
            //唤醒B
            conditionB.signal();
        }finally {
            lock.unlock();
        }
    }
    //B和C的打印就不一一介绍了
    public void printB() {
        lock.lock();
        try {
            if (num!=2){
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B");
            num=3;
            conditionC.signal();
        }finally {
            lock.unlock();
        }
    }
    //打印C
    public void printC(){
        lock.lock();
        try {
            if (num!=3){
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C");
            num=1;
            conditionA.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest abc=new ConditionTest();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                abc.printA();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                abc.printB();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                abc.printC();
            }
        }).start();
    }
}