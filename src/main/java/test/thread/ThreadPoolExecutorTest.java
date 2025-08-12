package test.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {
    public void executeMethod() {
        //1.创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                3, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //2.创建任务
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行了。。。");
        };
        //3.提交任务
        for (int i = 0; i < 50; i++) {
            poolExecutor.execute(runnable);
        }
        //4.关闭线程池
        poolExecutor.shutdown();
    }
    public static void submitMethod() throws ExecutionException, InterruptedException {
        //1.创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,
                1, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //2.提交任务，Future标识将要执行任务的结果，submit可以传入一个Callable<T>对象
        Future<Integer> future = poolExecutor.submit(new Callable<Integer>() {
            private int sum = 0;
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    sum += i;
                    Thread.sleep(10);
                }
                return sum;
            }
        });
        //3.获取任务的结果（等待任务执行完毕才返回，阻塞）
        System.out.println(future.get());
        //4.关闭线程池
        poolExecutor.shutdown();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //submitMethod();
        AtomicInteger atomicInteger = new AtomicInteger();

    }
}