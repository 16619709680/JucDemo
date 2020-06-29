package juc.T_011_InterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：
 * 实现一个容器，提供两个方法：add() 、size()
 * 写两个线程，线程1 添加十个元素到容器，线程二实现监控元素的个数，当个数打到5的时候，线程2 给出提示并结束
 * 可以使用wait() 和notify()实现，wait()会释放锁，但是notify()不会释放锁
 * 但是，需要保证t2 先执行，首先要让t2 监听
 * <p>
 * 分析下面的程序运行结果，发现，输出结果并不是在size=5的时候，t2退出，而是t1结束的时候才接到通知退出。
 * 原因：t1 虽然notify(),但是并没有释放自身持有的锁，因此t2依旧得不到执行机会，只有等待t1执行结束才可以执行
 * 因此，notify()之后，t1必须释放锁，t2 退出之后，也必须notify()，通知t1执行
 */
public class T04_NotifyFreeLock {

    volatile List list = new ArrayList();

    void add(Object o) {
        list.add(o);
    }

    int size() {

        return list.size();
    }


    public static void main(String[] args) {

        final Object o = new Object();

        T04_NotifyFreeLock t03_notifyLock = new T04_NotifyFreeLock();

        new Thread(() -> {
            synchronized (o) {
                System.out.println("t2 .....start");
                if (t03_notifyLock.size() != 5) {

                    try {
                        o.wait();//释放锁，t1 获得锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 .....end");
                o.notify();
            }
        }, "t2").start();


        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; i++) {

                    t03_notifyLock.add(i);
                    System.out.println("Add:" + i);

                    if (t03_notifyLock.size() == 5) {
                        o.notify(); //唤醒t2,但是 t1并不会释放当前持有的锁，因此 t2 依旧得不到执行机会，除非t1 执行完毕

                        try {
                            o.wait();//释放锁，给t2执行机会
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();


    }


}
