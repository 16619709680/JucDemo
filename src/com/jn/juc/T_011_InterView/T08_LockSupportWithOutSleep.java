package juc.T_011_InterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

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
 * <p>
 * 改用 CountDownLatch  实现
 */
public class T08_LockSupportWithOutSleep {

    volatile List list = new ArrayList();

    void add(Object o) {
        list.add(o);
    }

    int size() {
        return list.size();
    }

    static Thread thread = null, thread1 = null;

    public static void main(String[] args) {

        T08_LockSupportWithOutSleep t07_lockSupport = new T08_LockSupportWithOutSleep();

        thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t07_lockSupport.add(i);
                System.out.println("Add:" + i);

                if (t07_lockSupport.size() == 5) {
                    LockSupport.unpark(thread);
                    LockSupport.park();
                }

              /*  try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


            }
        }, "t1");

        thread = new Thread(() -> {
            /*if (t07_lockSupport.size() != 5) {
                LockSupport.park();
            }*/
            LockSupport.park();
            System.out.println("t2.....end");
            LockSupport.unpark(thread1);
        }, "t2");

        thread.start();
        thread1.start();
    }

}
