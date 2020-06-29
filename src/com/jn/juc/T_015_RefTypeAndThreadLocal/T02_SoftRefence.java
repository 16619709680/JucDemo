package juc.T_015_RefTypeAndThreadLocal;

import java.lang.ref.SoftReference;

/**
 * 软引用   用来做缓存使用
 */
public class T02_SoftRefence {

    public static void main(String[] args) {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(softReference.get());

        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(softReference.get());

        //再分配一个数组，heap将装不下，这个时候系统会进行垃圾回收，先回收一次，如果不够，会将 软引用干掉
        byte[] bytes = new byte[1024 * 1024 * 15];

        System.out.println(softReference.get());

    }
}
