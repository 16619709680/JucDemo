package juc.T_018_FromHashTableToCHM;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class T03_TestSynchronizedHashMap {

    static Map<UUID, UUID> m = Collections.synchronizedMap(new HashMap<UUID, UUID>());

    static int count = Constants.COUNT;
    static final int THREAD_COUNT = Constants.THREAD_COUNT;//线程数

    static UUID[] names = new UUID[count];
    static UUID[] values = new UUID[count];

    static {
        for (int i = 0; i < count; i++) {
            names[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }


    static class MyThread extends Thread {
        int start;
        int gap = count / THREAD_COUNT;

        //构造
        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {

            for (int i = start; i < gap + start; i++) {
                m.put(names[i], values[i]);
            }
        }
    }


    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i * (count / THREAD_COUNT));
        }

        for (Thread thread : threads
        ) {
            thread.start();
        }


        for (Thread thread : threads
        ) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));

        System.out.println(m.size());


        //****************************************

        start = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {
                    m.get(names[10]);
                }
            });

        }

        for (Thread thread : threads
        ) {
            thread.start();
        }


        for (Thread thread : threads
        ) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();

        System.out.println("耗时2：" + (end - start));
        System.out.println(m.size());

    }

}
