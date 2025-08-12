package test;

import java.util.concurrent.locks.ReentrantLock;

public class ExplicitLockExample {
    private int counter = 0;

    public void increment() {
        counter++; // 受保护的代码
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        ExplicitLockExample example = new ExplicitLockExample();
        final ReentrantLock lock = new ReentrantLock(true);

        // 启动多个线程
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                lock.lock(); // 获取锁
                try {
                    // 受保护的代码
                    System.out.println("Final counter value: " + example.getCounter());
                    example.increment();
                } finally {
                    lock.unlock(); // 确保释放锁
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

        // 打印最终计数
        System.out.println("Final counter value: " + example.getCounter());
    }
}
