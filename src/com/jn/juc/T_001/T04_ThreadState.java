package juc.T_001;

import java.util.concurrent.TimeUnit;

/**
 * 线程状态
 */
public class T04_ThreadState {

    static class T1 extends Thread {

        @Override
        public void run() {

            System.out.println(this.getState());

            for (int i = 0; i < 10; i++) {

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public static void main(String[] args) {

        T1 t1 = new T1();

        System.out.println("1...." + t1.getState());

        t1.start();

        System.out.println("2...." + t1.getState());

        try {
            t1.join();
            System.out.println("3...." + t1.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




/*      1....NEW
        2....RUNNABLE
        RUNNABLE
        3....TERMINATED*/


}
