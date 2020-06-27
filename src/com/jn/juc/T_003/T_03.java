package juc.T_003;

/**
 * 同步方法和非同步方法是否可以同时调用   YES
 */
public class T_03 {

    private synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "m1 .....start");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + "m1 .....end");
    }


    private void m2() {
        System.out.println(Thread.currentThread().getName() + "m1 .....start");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + "m1 .....end");
    }


    public static void main(String[] args) {
        T_03 t_03 = new T_03();

        /*new Thread(() -> t_03.m1(),"T1").start();
        new Thread(() -> t_03.m2(),"T2").start();*/

        new Thread(t_03::m1, "T1").start();
        new Thread(t_03::m2, "T2").start();

    }

}
