package juc.T_007_LockOptimization;

import java.util.concurrent.TimeUnit;

/**
 * 锁定对象o,如果o的属性发生改变，不会影响锁的使用
 * 但是如果o变成了另一个对象，则锁的对象发生改变
 * 应该避免将锁定对象的引用变为另外的对象
 */
public class T03_SyncSameObject {

    Object o = new Object();

    void m() {
        synchronized (o) {

            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }

    }


    public static void main(String[] args) {
        T03_SyncSameObject t03_syncSameObject = new T03_SyncSameObject();

        new Thread(t03_syncSameObject::m, "XX").start();


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(t03_syncSameObject::m, "XX2");

        t03_syncSameObject.o = new Object();//锁对象发生改变，所以XX2 能够执行，如果注释掉这句话的话，XX2 永远无法执行

        thread.start();
    }

}
