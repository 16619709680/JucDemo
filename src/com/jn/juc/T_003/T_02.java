package juc.T_003;

/**
 * 输出分析
 */

public class T_02 implements Runnable {

    private /*volatile*/ int count = 100;


    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "  count = " + count);

    }


    public static void main(String[] args) {
        T_02 t_01 = new T_02();
        for (int i = 0; i < 100; i++) {
            new Thread(t_01, "Thread" + i).start();
        }
    }
}
    //count 有序
    /*  Thread0  count = 99
        Thread8  count = 98
        Thread1  count = 97
        Thread2  count = 96
        Thread3  count = 95
        Thread4  count = 94
        Thread5  count = 93
        Thread6  count = 92
        Thread7  count = 91
        Thread9  count = 90*/