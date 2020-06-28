package juc.T_008_AtomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class T01_AtomicInteger {

    volatile int count1 = 0;

    AtomicInteger atomicInteger = new AtomicInteger(0);

    void m() {
        for (int i = 0; i < 10000; i++) {

            count1++;

        }

    }


    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List <Thread> threads = new ArrayList <>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "Thread" + i));
        }

        threads.forEach((o) -> {
            o.start();
        });


        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count1);

    }

}
