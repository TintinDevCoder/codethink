package changjingti;

/**
 * 生产者-消费者模型：例如一个厨子4s生产一个，一个客人10s消费一个
 */

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class t3 {
    private static final Queue<String> queue = new ConcurrentLinkedQueue<>();
    private static final int MAX_CAPICITY = 5;

    private static int num = 1;
    private static final Lock lock = new ReentrantLock();
    private static final Condition isFull = lock.newCondition();
    private static final Condition isEmpty = lock.newCondition();

    public static void main(String[] args) {
        Thread chefThread = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == MAX_CAPICITY) {
                        System.out.println("Queue is full, Chef is waiting...");
                        isFull.await();
                    }

                    String dish = "Dish-" + num++;
                    queue.add(dish);
                    System.out.println("Chef produced" + dish);

                    isEmpty.signalAll();
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread customerThread = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (queue.isEmpty()) {
                        System.out.println("Queue is empty, Customer is waiting...");
                        isEmpty.await();
                    }

                    String dish = queue.poll();
                    System.out.println("Costomer consumed: " + dish);

                    isFull.signalAll();
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        chefThread.start();
        customerThread.start();
    }
}
