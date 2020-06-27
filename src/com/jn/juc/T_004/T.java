package juc.T_004;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法调用另一个同步方法，一个线程已经具有当前对象的锁，再次申请时依旧可以获得当前对象锁
 * 也就是说 synchronized锁  是可重入的
 */
public class T {


    synchronized void t1() {
        System.out.println("t1.....start");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2();
        System.out.println("t1.....end");
    }

    synchronized void t2() {
        System.out.println("t2.....start");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t2.....end");
    }


    public static void main(String[] args) {

        new T().t1();
    }
}
