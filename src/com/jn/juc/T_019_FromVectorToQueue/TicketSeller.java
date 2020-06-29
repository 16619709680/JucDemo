package juc.T_019_FromVectorToQueue;

import java.util.ArrayList;

/**
 * 卖票
 *  到最后 会出现空票
 */
public class TicketSeller {

    static ArrayList<String> ticket = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            ticket.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                while (ticket.size()>0) {
                    System.out.println(Thread.currentThread().getName()+"出票：" + ticket.remove(0));
                }
            }).start();


        }


    }

}
