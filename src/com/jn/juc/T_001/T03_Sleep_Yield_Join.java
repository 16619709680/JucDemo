package juc.T_001;

import java.util.concurrent.TimeUnit;

/**
 * 线程方法
 * Sleep:不释放当前线程占有资源
 * Yield:暂定当前线程，让出CPU执行片
 * Join:从当前线程跳到另一个线程上执行，等待另一个线程执行完毕再次回到当前线程执行
 */

public class T03_Sleep_Yield_Join {

    public static void main(String[] args) {
        //testSleep();
        //testYield();
        testJoin();
    }


    static void testSleep() {

        new Thread(() -> {

            for (int i = 0; i < 100; i++) {

                System.out.println("A" + i);

                try {
                    //Thread.sleep(1000);
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testYield() {
        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                System.out.println("B" + i);

                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();

        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                System.out.println("C" + i);

                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }


    static void testJoin() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("D:" + i);
                try {
                    //TimeUnit.SECONDS.sleep(1);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread thread1 = new Thread(() -> {

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 10; i++) {
                System.out.println("E" + i);
                try {
                    //TimeUnit.SECONDS.sleep(1);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        thread1.start();

    }

}
