package juc.T_006_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * Volatile 关键字：使一个变量在多个线程间可见
 * A B 线程都用到了一个变量，java 默认A线程中保存了一份copy,这样的话，如果B线程更改了变量值，A线程未必知道
 * 使用 volatile 关键字可以让所有的线程都可以读到这个变量的修改值
 * <p>
 * 下面的代码中，running 存在于堆内存的 t01对象中
 * 当线程一开始运行时，会将running变量从内存中保存一份到 t01线程的工作区，在运行的过程中会直接使用这个copy,并不会每次都去读取对堆内存
 * ，当主线程修改了running的值之后，t01线程感知不到，所以不会停止运行
 * <p>
 * 使用volatile 关键字可以强制所有的线程都去堆内存中读取running 的值
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html(plese look)
 * volatile 关键字并不能够保证多个线程同时更改running变量带来的不一致问题，也就是说colatile 无法替代synchronized 关键字
 */
public class T01_Volatile {

    /*volatile*/ boolean running = true;

    void m() {

        System.out.println(" m start ....");

        while (running) {
            //System.out.println("running..........");
        }
        System.out.println(" m end ....");
    }


    public static void main(String[] args) {

        T01_Volatile t01_volatile = new T01_Volatile();

        new Thread(t01_volatile::m, "T1").start();

        try {
            TimeUnit.MICROSECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t01_volatile.running = false;
    }
}
