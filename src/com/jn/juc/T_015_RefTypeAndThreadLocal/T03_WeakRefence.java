package juc.T_015_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 */
public class T03_WeakRefence {

    public static void main(String[] args) {

        WeakReference<M> mWeakReference = new WeakReference<M>(new M());

        System.out.println(mWeakReference.get());
        System.gc();
        System.out.println(mWeakReference.get());


        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        objectThreadLocal.set(new M());
        objectThreadLocal.remove();
    }

}
