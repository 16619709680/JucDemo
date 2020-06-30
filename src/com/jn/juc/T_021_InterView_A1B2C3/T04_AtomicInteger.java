package juc.T_021_InterView_A1B2C3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class T04_AtomicInteger {
    static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {

        char[] a = "1234567".toCharArray();
        char[] b = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char i : b) {
                while (atomicInteger.get() != 1) {
                }
                System.out.print(i);
                atomicInteger.set(2);
            }
        }, "t1").start();

        new Thread(() -> {
            for (char j : a) {
                while (atomicInteger.get() != 2) {
                }
                System.out.print(j);
                atomicInteger.set(1);
            }
        }, "t2").start();
    }


}
