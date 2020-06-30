package juc.T_021_InterView_A1B2C3;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 */
public class T02_BlockingQueue {

    static BlockingQueue q1 = new ArrayBlockingQueue(1);
    static BlockingQueue q2 = new ArrayBlockingQueue(1);

    public static void main(String[] args) {

        char[] a = "123456".toCharArray();
        char[] b = "ABCDEF".toCharArray();

        new Thread(() -> {
            for (int i = 0; i < b.length; i++) {
                System.out.print(b[i]);

                try {
                    q1.put("OK");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();


        new Thread(() -> {
            for (int i = 0; i < a.length; i++) {
                try {
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.print(a[i]);

                try {
                    q2.put("OK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t2").start();


    }

}
