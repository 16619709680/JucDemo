package juc.T_010_ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  ReentrantLock 可以指定为公平锁或者非公平锁
 */
public class T05_ReentrantLock extends Thread {

    private static ReentrantLock reentrantLock = new ReentrantLock(false);

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                reentrantLock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        T05_ReentrantLock t05_reentrantLock = new T05_ReentrantLock();

        Thread thread = new Thread(t05_reentrantLock);
        Thread thread2 = new Thread(t05_reentrantLock);

        thread.start();
        thread2.start();
    }
}
