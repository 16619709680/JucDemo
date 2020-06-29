package juc.T_015_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 线程局部变量
 */
public class ThreadLocal {

    static class Person {
        String name = "张三";
    }

    volatile static Person p = new Person();

    public static void main(String[] args) {

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(p.name);
        }).start();


        new Thread(() -> {


            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            p.name = "李四";

        }).start();

    }

}
