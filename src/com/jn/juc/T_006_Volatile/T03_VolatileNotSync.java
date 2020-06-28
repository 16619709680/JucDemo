package juc.T_006_Volatile;

import java.util.ArrayList;
import java.util.List;

/**
 * Volatile 并不能保证多个线程修改running变量带来的数据不一致的问题，也就是说volatile不能替代synchronized
 */
public class T03_VolatileNotSync {

    volatile int count = 0;

    /*synchronized*/   void m() {//synchronized 可以保证原子性，volatile 只能保证可见性

        for (int i = 0; i < 10000; i++) {

            count++;
        }

    }


    public static void main(String[] args) {

        T03_VolatileNotSync t3 = new T03_VolatileNotSync();

        List <Thread> threadList = new ArrayList <>();

        for (int i = 0; i < 10; i++) {

            threadList.add(new Thread(t3::m, "Thread+" + i));
        }

        threadList.forEach((v) -> v.start());


        threadList.forEach((v) -> {
            try {
                v.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t3.count);
    }


}
