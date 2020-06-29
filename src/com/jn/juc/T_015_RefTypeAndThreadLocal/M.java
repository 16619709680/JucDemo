package juc.T_015_RefTypeAndThreadLocal;

public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize............");
    }
}
