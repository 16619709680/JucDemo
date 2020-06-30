package juc.T_020_Queue;

import java.util.concurrent.LinkedTransferQueue;

/**
 *
 */
public class T08_TransferQueue {

    public static void main(String[] args) {

        LinkedTransferQueue <Object> objects = new LinkedTransferQueue <>();

        new Thread(() -> {

            try {
                System.out.println(objects.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        try {
            objects.transfer("AAAAA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //objects.put("BBBBB");

        System.out.println(objects.size());
    }


}
