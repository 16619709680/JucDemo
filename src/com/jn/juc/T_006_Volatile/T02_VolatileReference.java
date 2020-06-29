package juc.T_006_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile 引用类型（数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */
public class T02_VolatileReference {

    boolean running = true;

    volatile static T02_VolatileReference t02 = new T02_VolatileReference();


    void m() {

        System.out.println("m .....start ");

        while (running) {

          /*  try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }
        System.out.println("m .....end ");

    }


    public static void main(String[] args) {

        new Thread(t02::m, "tttt").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t02.running = false;
    }

}
