package juc.T_002;

public class T05_Sync {

    private int i = 10;

    //private static Object o = new Object();


    private synchronized void run() {//等同于方法执行时要synchronized

        i--;
        System.out.println(Thread.currentThread().getName() + "..i =" + i);

    }

    private void S() {
        synchronized (T05_Sync.class) {
            i++;
            System.out.println(i);
        }
    }


    public static void main(String[] args) {
        //new T05_Sync().run();
        new T05_Sync().S();
    }
}
