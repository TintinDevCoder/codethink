package changjingti;

import java.util.*;
import java.util.concurrent.*;

/**
 * 多线程场景题：有 5 个人，在那赛跑，请你设计一个多线程的裁判程序给出他们赛跑的结果顺序，5个人的速度随机处理
 */
public class t2 {
    static List<RunnerResult> results = new CopyOnWriteArrayList<>();

    static class Runner implements Callable<RunnerResult> {
        private final String name;
        private final int speed;

        Runner(String name) {
            this.name = name;
            this.speed = new Random().nextInt(100) + 1;
        }
        @Override
        public RunnerResult call() throws Exception {
            long time = 1000 / speed;
            Thread.sleep(time);
            results.add(new RunnerResult(name, time));
            return new RunnerResult(name, time);
        }
    }

    static class RunnerResult {
        String name;
        long time;
        RunnerResult(String name, long time) {
            this.name = name;
            this.time = time;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            String name = "Runner" + (i + 1);
            executorService.submit(new Runner(name));
        }
        executorService.shutdown();
        try {
            // 等待所有任务完成
            if (executorService.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES)) {
                // 打印结果
                for (RunnerResult runnerResult : results) {
                    System.out.println("st place: " + runnerResult.name + " (Time: " + runnerResult.time + " ms)");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
