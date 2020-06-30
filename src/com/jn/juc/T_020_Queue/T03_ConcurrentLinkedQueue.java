package juc.T_020_Queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 *
 */
public class T03_ConcurrentLinkedQueue {

    public static void main(String[] args) {

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        for (int i = 0; i < 10; i++) {

                concurrentLinkedQueue.offer(i);

        }

        System.out.println(concurrentLinkedQueue);
        System.out.println(concurrentLinkedQueue.size());


        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.size());

        System.out.println(concurrentLinkedQueue.peek());
        System.out.println(concurrentLinkedQueue.size());

    }

}
