package juc.T_010_ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock  可以实现 synchronized  相同的功能
 * 但是 ReentrantLock 需要自己手动去加锁、释放锁
 * sync 执行中遇到异常会自动释放锁，但是 ReentrantLock 必须手动去释放锁，因此 unlock()经常写在 Finally 中
 */

public class T03_ReentrantLock {

    Lock lock = new ReentrantLock();

    private void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 7; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 使用tryLock 进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据tryLock的返回值来判断是否锁定
     * 也可以制定trylock的时间，由于tryLock的时间 抛出的异常，所以unlock 必须放在finally 中
     */

    private void m2() {
       /* lock.lock();
        try {
            System.out.println("m2........");
        } finally {
            lock.unlock();
        }*/
        boolean b = false;
        try {
            b = lock.tryLock(5, TimeUnit.SECONDS);

            System.out.println("m2..." + b);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (b) {
                lock.unlock();
            }
        }


    }


    public static void main(String[] args) {
        T03_ReentrantLock t02_reentrantLock = new T03_ReentrantLock();


        new Thread(t02_reentrantLock::m1).start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t02_reentrantLock::m2).start();


    }

}
