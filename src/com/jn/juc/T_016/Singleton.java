package juc.T_016;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 线程安全的单例模式：
 * <p>
 * 阅读文章：http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * <p>
 * 更好的是采用下面的方式，既不用加锁，也能实现懒加载
 */
public class Singleton {

    private Singleton() {
        System.out.println("Singleton.........");
    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    public static Singleton getSingleton() {
        return Inner.s;
    }


    public static void main(String[] args) {

        Thread[] threads = new Thread[200];

        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(() -> {

                System.out.println(Singleton.getSingleton());
            });

        }

        Arrays.asList(threads).forEach((o) -> o.start());

    }
}
