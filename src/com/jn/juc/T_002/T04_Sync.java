package juc.T_002;

public class T04_Sync {

    private int i = 10;

    //private static Object o = new Object();


    private synchronized void run() {//等同于方法执行时要synchronized

        i--;
        System.out.println(Thread.currentThread().getName() + "..i =" + i);

    }

    private void S() {
        i++;
        System.out.println();
    }


    public static void main(String[] args) {

        new T04_Sync().run();
        new T04_Sync().S();
    }
}
