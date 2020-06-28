package juc.T_009_Unsafe;


import sun.misc.Unsafe;

/**
 *
 */
public class HelloUnsafe {

    static class M {

        public M() {
        }

        int i = 0;

    }


    public static void main(String[] args) {

        Unsafe unsafe = Unsafe.getUnsafe();
        try {
            M o = (M) unsafe.allocateInstance(M.class);
            o.i = 9;

            System.out.println(o.i);

        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
