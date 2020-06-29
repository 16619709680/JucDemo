package juc.T_010_ReentrantLock;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class T06_CountDownLatch {

    public static void main(String[] args) {

        m1();
        m2();
    }


    private static void m2() {
        Thread[] threads = new Thread[1000];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int count = 0;

                for (int j = 0; j < 10000; j++) {
                    count += j;
                    countDownLatch.countDown();
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            countDownLatch.await();
            System.out.println("countDownLatch...............end ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private static void m1() {


        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {

                int count = 0;

                for (int j = 0; j < 10000; j++) {

                    count += j;
                }

            });
        }


        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("join.............end");
    }
}
