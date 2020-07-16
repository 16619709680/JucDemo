package juc.T_022_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i <10 ; i++) {
            int x = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程："+Thread.currentThread().getName()+"执行："+x);
                }
            });
        }
    }
}
