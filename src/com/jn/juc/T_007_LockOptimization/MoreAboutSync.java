package juc.T_007_LockOptimization;

/**
 *
 */
public class MoreAboutSync {

    String a = "hello";
    String b = "hello";

    void m1() {
        synchronized (a) {

        }
    }

    void m2() {
        synchronized (b) {

        }
    }


    public static void main(String[] args) {

        new MoreAboutSync().m1();
        new MoreAboutSync().m2();

    }
}
