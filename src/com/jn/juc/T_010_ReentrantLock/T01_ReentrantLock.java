package juc.T_010_ReentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * ReentrantLock 用于替代 synchronized
 * 下面的例子中m1锁定了this,只有m1 执行完毕之后，m2才会得到执行的机会
 */

public class T01_ReentrantLock {

    synchronized void m1(){

        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(i);

            if(i ==2){
                m2();
            }

        }

    }

    synchronized void m2(){

        System.out.println("m2.............");
    }


    public static void main(String[] args) {
         T01_ReentrantLock t01_reentrantLock = new T01_ReentrantLock();

         new Thread(t01_reentrantLock::m1,"xx").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t01_reentrantLock::m2,"xxxx").start();

    }


}
