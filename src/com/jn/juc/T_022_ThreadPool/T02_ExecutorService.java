package juc.T_022_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class T02_ExecutorService {


    public static void main(String[] args) {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
