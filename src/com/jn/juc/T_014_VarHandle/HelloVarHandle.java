package juc.T_014_VarHandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
public class HelloVarHandle {

/*
    int x = 8;

    private static VarHandle varHandle;


    static {

        try {
            varHandle = MethodHandles.lookup().findVarHandle(HelloVarHandle.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

         HelloVarHandle helloVarHandle = new HelloVarHandle();

        System.out.println((int)varHandle.get(helloVarHandle));
        varHandle.set(helloVarHandle,9);
        System.out.println(helloVarHandle.x);

        varHandle.compareAndSet(helloVarHandle,9,10);
        System.out.println(helloVarHandle.x);

        varHandle.getAndAdd(helloVarHandle,10);
        System.out.println(helloVarHandle.x);

    }
*/


}
