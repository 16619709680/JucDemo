package juc.T_001;

/**
 * 如何创建线程
 */
public class T02_HowToCreateThread {

    //1.实现Runnable ，重写run方法
    private static class T1 implements Runnable {

        @Override
        public void run() {
            System.out.println("实现Runnable ......");
        }
    }


    //2.继承Thread
    private static class T2 extends Thread {

        @Override
        public void run() {
            System.out.println("继承Thread ......");
        }
    }


    public static void main(String[] args) {


        //启动一个线程三种方式：1.继承Thread 2.实现Runable 3.Executors.newCachedThread


        new Thread(new T1()).start();

        new T2().start();

        new Thread(() -> {
            System.out.println("Lambdal! ");
        }).start();


    }
}
