package juc.T_010_ReentrantLock;

import java.util.concurrent.Exchanger;

/**
 *
 */
public class T12_Exchanger {

    static Exchanger <String> exchanger = new Exchanger <>();

    public static void main(String[] args) {

        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "  s: " + s);

        }, "xx").start();


        new Thread(() -> {
            String s = "T2";

            try {
                s = exchanger.exchange(s);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " s:" + s);

        }, "XX2").start();


    }

}
