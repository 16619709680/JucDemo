package juc.T_021_InterView_A1B2C3;

import java.util.concurrent.Exchanger;

/**
 *
 */
public class T09_Exchange_Not_Work {

    private static Exchanger <String> exchanger = new Exchanger <>();


    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (int i = 0; i < aC.length; i++) {
                System.out.print(aC[i]);
                try {
                    exchanger.exchange("T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();


        new Thread(() -> {

            for (int i = 0; i < aI.length; i++) {

                try {
                    exchanger.exchange("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(aI[i]);
            }
        }).start();


    }

}
