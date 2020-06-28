package juc.T_006_Volatile;

/**
 * volatile 引用类型（数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */
public class T02_VolatileReference_02 {

    static class TT {
        int a, b;

        public TT(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    volatile static TT tt;


    public static void main(String[] args) {

        Thread Writer = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                tt = new TT(i, i);
            }
        });

        Thread Reader = new Thread(() -> {
            while (tt == null) {
            }
            int x = tt.a;
            int y = tt.b;

            if (x == y) {
                System.out.printf("a=%s, b=%s%n", x, y);
            }

        });

        Reader.start();
        Writer.start();

        try {
            Reader.join();
            Writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("......end");

    }

}
