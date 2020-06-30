package juc.T_021_InterView_A1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 * 打印A1B2C3D4........
 */
public class T01_LockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {

        char[] a = "123456".toCharArray();
        char[] b = "ABCDEF".toCharArray();


        t1 = new Thread(() -> {
            for (int i = 0; i < b.length; i++) {
                System.out.print(b[i]);
                LockSupport.unpark(t2);//唤醒t2
                LockSupport.park();//阻塞t1

            }
        }, "t1");


        t2 = new Thread(() -> {


            for (int i = 0; i < a.length; i++) {
                LockSupport.park();//阻塞t2
                System.out.print(a[i]);
                LockSupport.unpark(t1);//唤醒t1
            }
        }, "t2");


        t1.start();
        t2.start();

    }

}
