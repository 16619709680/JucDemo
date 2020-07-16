package juc.T_022_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程：" + Thread.currentThread().getName() + "执行。。。。。。。");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println();
    }
}
