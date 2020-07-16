package juc.T_022_ThreadPool;

import java.util.concurrent.*;

/**
 *  自定义线程池
 */
public class T01_ThreadPoolExecutor implements Runnable {

    @Override
    public void run() {
        System.out.println("线程：" + Thread.currentThread().getName() + " Working.......");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //自定义线程池
        //构造方法
        /**
         * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable>
         1  corePoolSize	int	核心线程池大小
         2	maximumPoolSize	int	最大线程池大小
         3	keepAliveTime	long	线程最大空闲时间
         4	unit	TimeUnit	时间单位
         5	workQueue	BlockingQueue<Runnable>	线程等待队列
         6	threadFactory	ThreadFactory	线程创建工厂
         7	handler	RejectedExecutionHandler	拒绝策略
         */
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                4, 1, TimeUnit.SECONDS, arrayBlockingQueue);
        Runnable t01 = new T01_ThreadPoolExecutor();
        Runnable t02 = new T01_ThreadPoolExecutor();
        Runnable t03 = new T01_ThreadPoolExecutor();
        Runnable t04 = new T01_ThreadPoolExecutor();
        Runnable t05 = new T01_ThreadPoolExecutor();
        threadPoolExecutor.execute(t01);
        threadPoolExecutor.execute(t02);
        threadPoolExecutor.execute(t03);
        threadPoolExecutor.execute(t04);
        threadPoolExecutor.execute(t05);
        threadPoolExecutor.shutdown();
    }
}
