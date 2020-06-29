package juc.T_020_Queue;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 集合高并发下，放入元素   不到 100W
 */
public class T02_CopyOnWriteList {

    public static void main(String[] args) {

        CopyOnWriteArrayList<Object> concurrentHashMap = new CopyOnWriteArrayList<>();

        Thread[] threads = new Thread[100];

        Random r = new Random();

        for (int i = 0; i < threads.length; i++) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        concurrentHashMap.add("A" + r.nextInt(1000000));
                    }
                }
            };
            threads[i] = new Thread(runnable);
        }

        runAndComputeTime(threads);

        System.out.println(concurrentHashMap.size());


    }

    private static void runAndComputeTime(Thread[] threads) {
        long start = System.currentTimeMillis();
        Arrays.asList(threads).forEach(o -> o.start());

        Arrays.asList(threads).forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));

    }
}
