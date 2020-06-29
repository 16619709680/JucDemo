package juc.T_010_ReentrantLock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class T08_Phaser {

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
                    return true;
                default:
                    return true;

            }


        }


        public static void main(String[] args) {

            myPhaser.bulkRegister(5);
            for (int i = 0; i < 5; i++) {
                final int nameIndex = i;
                new Thread(() -> {
                    Person p = new Person("Person" + nameIndex);

                    p.come();
                    myPhaser.arriveAndAwaitAdvance();


                    p.eat();
                    myPhaser.arriveAndAwaitAdvance();

                    p.leave();
                    myPhaser.arriveAndAwaitAdvance();

                }).start();
            }
        }


        static class Person {
            private String name;

            public Person(String name) {
                this.name = name;
            }

            public void eat() {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 吃完了\n", name);
            }

            public void come() {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 到达婚礼现场\n", name);
            }

            public void leave() {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 离开婚礼现场\n", name);
            }
        }
    }
}
