package juc.T_005;
/**
 * 当前线程执行出错，会释放锁，需要进行捕获
 */

import java.util.concurrent.TimeUnit;

public class T {

    int count = 0;

    synchronized void s() {
        System.out.println(Thread.currentThread().getName() + "start........");

        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + "start........");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                if (count == 5) {
                    int i = 1 / 0;//此处抛出异常，当前线程支持的锁还被释放，如果想当前对象继续持有锁，需要将异常进行捕获，让循环继续
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        T t = new T();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                t.s();
            }
        };


        new Thread(runnable, "T1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(runnable, "T2").start();


    }


}
