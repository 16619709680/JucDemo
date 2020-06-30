package juc.T_020_Queue;


import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class T05_ArrayBlockingQueue {

    static ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

    static Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            try {
                arrayBlockingQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            //arrayBlockingQueue.add("AAA");

            arrayBlockingQueue.put("AAA");

            arrayBlockingQueue.offer("AAA");

            arrayBlockingQueue.offer("11", 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(arrayBlockingQueue);
    }

}
