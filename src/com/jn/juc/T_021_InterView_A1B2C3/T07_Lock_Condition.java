package juc.T_021_InterView_A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class T07_Lock_Condition {

    public static void main(String[] args) {

        char[] a = "1234567".toCharArray();
        char[] b = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char x : b) {
                    System.out.println(x);
                    condition.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                condition.signal();
            } finally {
                lock.unlock();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                lock.lock();
                for (char x : a) {
                    System.out.println(a);
                    condition.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                condition.signal();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
