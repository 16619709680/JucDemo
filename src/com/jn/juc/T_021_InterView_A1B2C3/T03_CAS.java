package juc.T_021_InterView_A1B2C3;

/**
 *
 */
public class T03_CAS {

    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun readyToRun = ReadyToRun.T1;//  volatile


    public static void main(String[] args) {

        char[] a = "123456".toCharArray();
        char[] b = "ABCDEF".toCharArray();

        new Thread(() -> {
            for (int i = 0; i < b.length; i++) {
                while (readyToRun != ReadyToRun.T1) {
                }
                System.out.print(b[i]);
                readyToRun = ReadyToRun.T2;
            }
        }, "t1").start();


        new Thread(() -> {
            for (int i = 0; i < a.length; i++) {
                while (readyToRun != ReadyToRun.T2) {
                }
                System.out.print(a[i]);
                readyToRun = ReadyToRun.T1;
            }
        }, "t2").start();


    }
}
