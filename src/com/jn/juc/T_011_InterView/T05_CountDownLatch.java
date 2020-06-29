package juc.T_011_InterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
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
 *
 * 改用 CountDownLatch  实现
 */
public class T05_CountDownLatch {

    volatile List list = new ArrayList();

    void add(Object o) {
        list.add(o);
    }

    int size() {
        return list.size();
    }

    static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {

        T05_CountDownLatch t05_countDownLatch = new T05_CountDownLatch();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t05_countDownLatch.add(i);
                System.out.println("Add" + i);

                countDownLatch.countDown();

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();


        new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println("t2.......end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
