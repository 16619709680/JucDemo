package juc.T_020_Queue;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class T04_LinkedBlockingQueue {

    static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

    static Random random = new Random();


    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    linkedBlockingQueue.put("A" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                try {
                    TimeUnit.MICROSECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "S").start();


        for (int i = 0; i < 5; i++) {

            new Thread(() -> {
                for (; ; ) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " take -----" + linkedBlockingQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }, "T" + i).start();


        }


    }

}
