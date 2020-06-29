package juc.T_019_FromVectorToQueue;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 卖票
 * Queue 同步容器
 */
public class TicketSeller_Queue {

    static Queue<String> ticket = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) {
            ticket.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                while (true) {
                    if (ticket.poll() == null) break;
                    else
                        System.out.println(Thread.currentThread().getName() + "出票：" + ticket.poll());
                }

            }).start();


        }


    }

}
