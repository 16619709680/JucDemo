package juc.T_010_ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock 还可以调用lockInterruptibly方法，可以对线程interrupt方法做出相应
 * 在一个线程等待锁的过程中可以被打断
 */
public class T04_ReentrantLock {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread1 .......start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("Thread1 .......end");
            } catch (InterruptedException e) {
                System.out.println("Interrupted1.........");
            } finally {
                lock.unlock();
            }
        });

        thread1.start();


        Thread thread2 = new Thread(() -> {
            try {
                //lock.lock();
                lock.lockInterruptibly();//可以对interupt()方法作出响应
                System.out.println("Thread2.....start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Thread2.....end");
            } catch (InterruptedException e) {
                System.out.println("Interrupted2...........");
            } finally {
                lock.unlock();
            }
        });

        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();//打断线程2 的等待
    }


    /*public static void main(String[] args) {
        Lock lock = new ReentrantLock();


        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            try {
                //lock.lock();
                lock.lockInterruptibly(); //可以对interrupt()方法做出响应
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt(); //打断线程2的等待

    }*/
}
