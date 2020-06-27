package juc.T_002;

public class T03_Sync {

    private static int i = 10;

    //private static Object o = new Object();


    private synchronized void run() {//等同于方法执行时要synchronized

        i--;
        System.out.println(Thread.currentThread().getName() + "..i =" + i);

    }


    public static void main(String[] args) {

        new T03_Sync().run();

    }
}
