package juc.T_022_ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T01_ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        /*scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程："+Thread.currentThread().getName()+"执行.......");
            }
        },3, TimeUnit.SECONDS);//延迟一秒执行*/


        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程："+Thread.currentThread().getName()+"执行.......");
            }
        },3,2,TimeUnit.SECONDS);//延迟三秒之后 每两秒执行一次
    }
}
