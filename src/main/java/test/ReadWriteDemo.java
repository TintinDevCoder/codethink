package test;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteDemo {
    //创建读写锁
    ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private int value;
    //设置
    public void setValue(int value){
        //上锁
        readWriteLock.writeLock().lock();
        try {
            Thread.sleep(1000);
            this.value=value;
            System.out.println(Thread.currentThread().getId()+"修改了value:"+this.value);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public int getvalue(){
        readWriteLock.readLock().lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId()+"读取了value:"+this.value);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
        return -1;
    }
    //测试代码
    public static void main(String[] args) {
        ReadWriteDemo readWriteDemo=new ReadWriteDemo();
        //修改
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                readWriteDemo.setValue(new Random().nextInt(100));
            }).start();
        }
        //读取
        for (int i = 0; i < 8; i++) {
            new Thread(()->{
                readWriteDemo.getvalue();
            }).start();
        }
    }
}