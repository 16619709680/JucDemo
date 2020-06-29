package juc.T_013_AQS;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 */
public class TestReentrantLock {

    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        /*reentrantLock.lock();
        i++;
        reentrantLock.unlock();*/

        synchronized (TestReentrantLock.class){
            i++;
        }

        System.out.println(i);
    }


}
