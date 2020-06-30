package juc.T_021_InterView_A1B2C3;

/**
 *
 */
public class T06_Sync_wait_notify {

    private static volatile boolean s = false;

    public static void main(String[] args) {
        final Object object = new Object();
        char[] a = "1234567".toCharArray();
        char[] b = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (object) {

                while (!s) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

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
