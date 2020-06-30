package juc.T_021_InterView_A1B2C3;

/**
 *
 */
public class T05_Sync_wait_notify {


    public static void main(String[] args) {
        final Object object = new Object();
        char[] a = "1234567".toCharArray();
        char[] b = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (object) {
                for (char x : b) {
                    System.out.print(x);
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }).start();

        //**************************************************************************************************************

        new Thread(() -> {
            synchronized (object) {
                for (char x : a) {
                    System.out.print(x);
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }).start();
    }

}
