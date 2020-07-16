package juc.T_022_ThreadPool;

import java.util.concurrent.*;

/**
 *
 *
 */
public class T03_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable <Object> objectCallable = new Callable() {

            @Override
            public Object call() throws Exception {
                return "Hello Word";
            }
        };


        ExecutorService executorService = Executors.newCachedThreadPool();

        Future <Object> submit = executorService.submit(objectCallable);

        System.out.println(submit.get());

        executorService.shutdown();
    }


}
