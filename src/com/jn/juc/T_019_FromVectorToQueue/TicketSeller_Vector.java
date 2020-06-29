package juc.T_019_FromVectorToQueue;

import java.util.ArrayList;
import java.util.Vector;

/**
 *  卖票
 *  Vector 同步容器
 */
public class TicketSeller_Vector {

    static Vector<String> ticket = new Vector<>();

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
