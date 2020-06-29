package juc.T_017_Containers;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * 队列
 *
 */
public class HelloQueue {

    public static void main(String[] args) {

         ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(1);

         arrayBlockingQueue.add(1);
         /*arrayBlockingQueue.add(2);
         arrayBlockingQueue.add(3);
         arrayBlockingQueue.add(4);*/

        System.out.println(arrayBlockingQueue);

    }


}
