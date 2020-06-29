package juc.T_011_InterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

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
public class T09_Semaphore {

    volatile List list = new ArrayList();

    void add(Object o) {
        list.add(o);
    }

    int size() {
        return list.size();
    }

    static Thread thread = null;

    public static void main(String[] args) {
        T09_Semaphore t09_semaphore = new T09_Semaphore();
        Semaphore semaphore = new Semaphore(1);


        thread = new Thread(() -> {

            try {
                semaphore.acquire();
                System.out.println("t2......end");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        new Thread(() -> {
            try {
                semaphore.acquire();
                for (int i = 0; i < 5; i++) {
                    t09_semaphore.add(i);
                    System.out.println("Add:" + i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                semaphore.acquire();
                for (int i = 5; i < 10; i++) {
                    t09_semaphore.add(i);
                    System.out.println("Add:" + i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

    }


}
