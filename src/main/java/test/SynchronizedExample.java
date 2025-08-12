package test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedExample {
    private int count = 0;
    public synchronized void increment(){
        count++;
    }
    public int getCount() {
        return count;
    }


    public static void main(String[] args) {

    }
    public void SychronizedBlock() {
        ConcurrentHashMap<Integer, AtomicInteger> hashMap = new ConcurrentHashMap<>();
        // 创建多个线程
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                // 使用 computeIfAbsent 初始化 AtomicInteger
                AtomicInteger value = hashMap.computeIfAbsent(1, key -> new AtomicInteger(0));
                synchronized (value) {
                    // 安全地自增
                    int newValue = value.incrementAndGet();
                    System.out.println("Current value: " + newValue);
                }
            });
            threads[i].start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void SynchronizedFunction() {
        SynchronizedExample example = new SynchronizedExample();

        // 创建多个线程
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    example.increment();
                    System.out.println(example.getCount());
                }
            });
            threads[i].start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Final count: " + example.getCount());
    }


}
