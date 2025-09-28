package changjingti;

import java.util.concurrent.Semaphore;

/**
 * 多线程交替打印：打印内容为ABC循环或者交替打印一段话
 */
public class t1 {
    public static Semaphore semaphore1 = new Semaphore(1);
    public static Semaphore semaphore2 = new Semaphore(0);
    public static Semaphore semaphore3 = new Semaphore(0);
    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        semaphore1.acquire();
                        System.out.println("A");
                        semaphore2.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        semaphore2.acquire();
                        System.out.println("B");
                        semaphore3.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        semaphore3.acquire();
                        System.out.println("C");
                        semaphore1.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
