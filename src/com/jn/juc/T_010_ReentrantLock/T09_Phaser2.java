package juc.T_010_ReentrantLock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class T09_Phaser2 {

    static Random r = new Random();

    static MyPhaser myPhaser = new MyPhaser();


    static void milliSleep(int milli) {


        try {
            TimeUnit.MICROSECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class MyPhaser extends Phaser {

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {

                case 0:
                    System.out.println("所有人来了..............");
                    return false;
                case 1:
                    System.out.println("所有人吃完了");
                    return false;
                case 2:
                    System.out.println("所有人离开了 ");
                    System.out.println("婚礼结束");
                    return false;
                case 3:
                    System.out.println("洞房");
                    return true;
                default:
                    return true;
            }

        }


        public static void main(String[] args) {

            myPhaser.bulkRegister(7);

            for (int i = 0; i < 5; i++) {
                new Thread(new Person("Person" + i)).start();
            }

            new Thread(new Person("新郎")).start();
            new Thread(new Person("新娘")).start();
        }


        static class Person implements Runnable {
            private String name;

            public Person(String name) {
                this.name = name;
            }

            public void eat() {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 吃完了\n", name);
                myPhaser.arriveAndAwaitAdvance();
            }

            public void come() {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 到达婚礼现场\n", name);
                myPhaser.arriveAndAwaitAdvance();
            }

            public void leave() {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 离开婚礼现场\n", name);
                myPhaser.arriveAndAwaitAdvance();
            }

            public void df() {
                if ("新郎".equals(name) || "新娘".equals(name)) {
                    milliSleep(r.nextInt(1000));
                    System.out.printf("%s 洞房\n", name);
                    myPhaser.arriveAndAwaitAdvance();
                } else {
                    myPhaser.arriveAndDeregister();
                }

            }


            @Override
            public void run() {

                come();
                eat();
                leave();
                df();
            }
        }
    }
}
