package juc.T_018_FromHashTableToCHM;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

public class T02_TestHashMap {

    static HashMap<UUID, UUID> m = new HashMap<>();

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
    }

}
