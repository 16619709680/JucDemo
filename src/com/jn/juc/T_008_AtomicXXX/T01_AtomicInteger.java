package juc.T_008_AtomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题，更高效的办法 AtomicInteger
 * AtomicXXX 本身方法都是原子性的，但是不能保证多个方法连续的调用是原子性的
 */
public class T01_AtomicInteger {

    /*volatile  int count1 = 0;*/

    AtomicInteger atomicInteger = new AtomicInteger(0);


    void m() {
        for (int i = 0; i < 10000; i++) {
            //count1++;
            atomicInteger.incrementAndGet();
        }
    }


    public static void main(String[] args) {
        T01_AtomicInteger atomicInteger = new T01_AtomicInteger();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            threads.add(new Thread(atomicInteger::m, "Thread" + i));

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


        //System.out.println(atomicInteger.count1);
        System.out.println(atomicInteger.atomicInteger);

    }

}
