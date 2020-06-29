package juc.T_015_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 线程局部变量
 * ThreadLocal 是用空间来交换时间，synchronized 是用时间来换空间
 * Hibernate 中session 就存在与 ThreadLocal中，避免synchronized 的使用
 */
public class ThreadLocal2 {

    static class Person {
        String name = "张三";
    }

    //volatile static Person p = new Person();

    static java.lang.ThreadLocal<Person> p = new java.lang.ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(p.get());
        }).start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.set(new Person());

        }).start();

    }

}
