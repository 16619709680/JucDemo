package juc.T_015_RefTypeAndThreadLocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;

/**
 * 虚引用
 */
public class T04_PhantomReference {

    private static final LinkedList l = new LinkedList();
    private static final ReferenceQueue r = new ReferenceQueue();


    public static void main(String[] args) {

        PhantomReference<M> mPhantomReference = new PhantomReference<>(new M(), r);

        new Thread(() -> {

            while (true) {
                byte[] bytes = new byte[1024 * 1024];
                l.add(bytes);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(mPhantomReference.get());
            }
        }).start();


        new Thread(() -> {
            while (true) {
                Reference poll = r.poll();

                if (poll != null) {

                    System.out.println("--- 虚引用对象被jvm回收了 ---- " + poll);
                }

            }


        }).start();


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
