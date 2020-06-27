package juc.T_002;

public class T01_Sync {

    private static int i = 10;

    private static Object o = new Object();


    private static void run() {
        synchronized (o) {//任何情况下，需要执行synchronized 的代码，需要先拿到O的锁
            i--;
            System.out.println(Thread.currentThread().getName() + "..i =" + i);
        }
    }


    public static void main(String[] args) {

        run();

    }
}
