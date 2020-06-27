package juc.T_002;

public class T02_Sync {

    private static int i = 10;

    //private static Object o = new Object();


    private void run() {
        synchronized (this) {//任何情况下，需要执行synchronized 的代码，需要先拿到this的锁
            i--;
            System.out.println(Thread.currentThread().getName() + "..i =" + i);
        }
    }


    public static void main(String[] args) {

        new T02_Sync().run();

    }
}
