package juc.T_020_Queue;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 集合高并发下，放入元素   不到 100W
 */
public class T01_ConcurrentHashMap {

    public static void main(String[] args) {
        //ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ConcurrentSkipListMap<Object, Object> concurrentHashMap = new ConcurrentSkipListMap<>();//高并发并且排序

        // treeMap   HashMap  HashTable  Collections.sync

        Thread[] threads = new Thread[100];

        Random r = new Random();
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);

        long start = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {

                for (int j = 0; j < 100000; j++) {
                    concurrentHashMap.put("A" + r.nextInt(1000000), "B" + r.nextInt(1000000));
                }
                countDownLatch.countDown();
            });
        }

        Arrays.asList(threads).forEach((o) -> o.start());


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));

        System.out.println(concurrentHashMap.size());
    }
}
