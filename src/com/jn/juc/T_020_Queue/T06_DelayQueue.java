package juc.T_020_Queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class T06_DelayQueue {

    static DelayQueue delayQueue = new DelayQueue();

    static Random random = new Random();


    static class MyTesk implements Delayed {

        String name;
        long runningTime;

        public MyTesk(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
        }


        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MICROSECONDS) < o.getDelay(TimeUnit.MICROSECONDS))
                return -1;

            if (this.getDelay(TimeUnit.MICROSECONDS) > o.getDelay(TimeUnit.MICROSECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return "MyTesk{" +
                    "name='" + name + '\'' +
                    ", runningTime=" + runningTime +
                    '}';
        }
    }


    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        MyTesk t1 = new MyTesk("t1", start + 1000);
        MyTesk t2 = new MyTesk("t2", start + 2000);
        MyTesk t3 = new MyTesk("t3", start + 1500);
        MyTesk t4 = new MyTesk("t4", start + 2500);
        MyTesk t5 = new MyTesk("t5", start + 500);


        delayQueue.add(t1);
        delayQueue.add(t2);
        delayQueue.add(t3);
        delayQueue.add(t4);
        delayQueue.add(t5);

        System.out.println(delayQueue);


        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(delayQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
