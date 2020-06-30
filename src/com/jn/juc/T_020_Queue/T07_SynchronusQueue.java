package juc.T_020_Queue;

import java.util.concurrent.SynchronousQueue;

/**
 *
 */
public class T07_SynchronusQueue {

    public static void main(String[] args) {

        SynchronousQueue <Object> objects = new SynchronousQueue <>();

        new Thread(() -> {

            try {
                System.out.println(objects.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        //objects.add("A");//阻塞等待消费者消费

        System.out.println(objects.size());

    }
}
