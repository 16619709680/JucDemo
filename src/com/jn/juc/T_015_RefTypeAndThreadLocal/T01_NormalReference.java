package juc.T_015_RefTypeAndThreadLocal;

import java.io.IOException;

/**
 * 强引用，手动调用GC 有可能回收垃圾
 */
public class T01_NormalReference {

    public static void main(String[] args) throws IOException {

        M m = new M();
        m = null;
        System.gc();

        System.in.read();

    }
}
