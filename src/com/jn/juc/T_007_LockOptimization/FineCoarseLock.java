package juc.T_007_LockOptimization;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 优化：
 * 同步代码块里面的语句越少越好，进行锁粒度细化，提高执行效率
 */
public class FineCoarseLock {

    int count = 0;

    synchronized void m1() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //业务逻辑中只有下面这行代码需要加锁，没必要将整个m1 方法加上锁 ，只需要针对特定的代码块加锁
        count++;


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    //优化
    void m2() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //业务逻辑中只有下面这行代码需要加锁，没必要将整个m1 方法加上锁 ，只需要针对特定的代码块加锁
        synchronized (this) {
            count++;
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
