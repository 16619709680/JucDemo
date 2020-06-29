package juc.T_011_InterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：
 * 实现一个容器，提供两个方法：add() 、size()
 * 写两个线程，线程1 添加十个元素到容器，线程二实现监控元素的个数，当个数打到5的时候，线程2 给出提示并结束
 * <p>
 * 下面程序分析，是否可以完成 上述需求
 */
public class T01_WithoutVolatile {

    List list = new ArrayList <>();

    void add(Object o) {
        list.add(o);
    }

    int size() {
        return list.size();
    }


    public static void main(String[] args) {

        T01_WithoutVolatile t01_withoutVolatile = new T01_WithoutVolatile();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                t01_withoutVolatile.add(i);
                System.out.println("Add:" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();


        new Thread(() -> {
            while (true) {
                if (t01_withoutVolatile.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();


    }


}
