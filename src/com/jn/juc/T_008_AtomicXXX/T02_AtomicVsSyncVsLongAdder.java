package juc.T_008_AtomicXXX;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Synchronized AtomicXXX LongAdder 性能对比
 *
 */
public class T02_AtomicVsSyncVsLongAdder {

    static int count = 0;

    static AtomicLong atomicLong = new AtomicLong(0L);

    static LongAdder longAdder = new LongAdder();


    public static void main(String[] args) {

        Thread[] threads = new Thread[1000];

        // 1. sync
        Object o = new Object();
        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000000; j++) {
                        synchronized (o) {
                            count++;
                        }
                    }
                }
            });
        }

        long start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("Sync:" + (end - start));

        // 2. AtomicLong

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {
                    atomicLong.incrementAndGet();
                }
            });

        }

        start = System.currentTimeMillis();

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();

        System.out.println("AtomicLong:" + (end - start));


        // 3. LongAdder
        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {

                    longAdder.increment();

                }
            });

        }

        start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();

        System.out.println("LongAdder:" + (end - start));

    }

}
