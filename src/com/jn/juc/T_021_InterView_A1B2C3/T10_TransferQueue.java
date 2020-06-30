package juc.T_021_InterView_A1B2C3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 *
 */
public class T10_TransferQueue {

    public static void main(String[] args) {
        char[] a = "1234567".toCharArray();
        char[] b = "ABCDEFG".toCharArray();

        TransferQueue <Object> objects = new LinkedTransferQueue <>();


        new Thread(() -> {
            for (int i = 0; i < b.length; i++) {
                try {
                    System.out.print(objects.take());
                    objects.transfer(a[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < a.length; i++) {
                try {
                    objects.transfer(b[i]);
                    System.out.print(objects.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
