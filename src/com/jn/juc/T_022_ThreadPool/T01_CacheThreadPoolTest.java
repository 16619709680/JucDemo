package juc.T_022_ThreadPool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T01_CacheThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程：" + Thread.currentThread().getName() + "执行。。。。。");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
