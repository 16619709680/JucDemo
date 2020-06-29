package juc.T_007_LockOptimization;

/**
 * 不要以字符串常量作为锁定对象
 * 下面例子中，m1  m2 锁定的其实是同一个对象
 * 这种情况会发生一个特别诡异的现象，比如你用到了一个类库，在类库中代码锁定了字符串"hello"
 * 但是你读不到源码，所以你自己的代码中也锁定了"hello",这时候就会发生非常诡异的死锁阻塞。
 * 因为你的程序和你的类库不经意使用了同一把锁
 */
public class T02_DonotLockString {

    String a = "hello";
    String b = "hello";

    void m1() {
        synchronized (a) {
            System.out.println("..........a");
        }
    }

    void m2() {
        synchronized (b) {
            System.out.println("..........B");
        }
    }


    public static void main(String[] args) {

        T02_DonotLockString moreAboutSync = new T02_DonotLockString();

        new Thread(moreAboutSync::m1, "m1").start();
        new Thread(moreAboutSync::m2, "m1").start();


    }
}
