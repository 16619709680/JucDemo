package juc.T_022_ThreadPool;

import java.util.concurrent.Executor;

/**
 *
 */
public class T01_Executor implements Executor {


    @Override
    public void execute(Runnable command) {
        //new Thread(command).run();
        command.run();
    }


    public static void main(String[] args) {

        new T01_Executor().execute(() -> System.out.println("Hello executor"));
    }
}
