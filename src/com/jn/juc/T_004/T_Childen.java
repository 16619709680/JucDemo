package juc.T_004;

import javax.crypto.spec.PSource;
import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法调用另一个同步方法，一个线程已经具有当前对象的锁，再次申请时依旧可以获得当前对象锁
 * 也就是说 synchronized锁  是可重入的
 * 此种情况可能存在与父子类中，子类调用父类的同步方法
 */
public class T_Childen {


    synchronized void t1() {
        System.out.println("t1.....start");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1.....end");
    }

    public static void main(String[] args) {

        new TT().t1();
    }


    static class TT extends T {

        @Override
        synchronized void t1() {
            System.out.println("children .....  start");
            super.t1();
            System.out.println("children .....  end");
        }
    }


}
