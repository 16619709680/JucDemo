package juc.T_003;

/**
 * 输出分析
 */

public class T_01 implements Runnable {

    private /*volatile*/ int count = 100;


    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "  count = " + count);

    }


    public static void main(String[] args) {
        T_01 t_01 = new T_01();
        for (int i = 0; i < 10; i++) {
            new Thread(t_01, "Thread" + i).start();
        }
    }
}


//count 值 无序

   /*   Thread1  count = 92
        Thread4  count = 94
        Thread8  count = 97
        Thread7  count = 96
        Thread3  count = 91
        Thread0  count = 99
        Thread9  count = 98
        Thread5  count = 95
        Thread2  count = 93
        Thread6  count = 90*/