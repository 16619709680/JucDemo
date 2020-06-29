package juc.T_019_FromVectorToQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * 卖票
 * Sync 同步容器
 */
public class TicketSeller_Sync {

    static List<String> ticket = new LinkedList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            ticket.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                while (true) {
                    synchronized (ticket) {
                        if (ticket.size() <= 0) break;
                        System.out.println(Thread.currentThread().getName() + "出票：" + ticket.remove(0));
                    }
                }
            }).start();
        }
    }

}
