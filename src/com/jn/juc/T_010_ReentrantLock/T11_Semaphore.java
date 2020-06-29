package juc.T_010_ReentrantLock;

import java.util.concurrent.Semaphore;

/**
 *
 */
public class T11_Semaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2, true);


        new Thread(() -> {
            try {

                semaphore.acquire();

                System.out.println("Thread1......start");

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println("Thread1......end");

        }).start();


        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread2..........start");
                Thread.sleep(1000);
                System.out.println("Thread2..........end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }


        }).start();


    }


}
