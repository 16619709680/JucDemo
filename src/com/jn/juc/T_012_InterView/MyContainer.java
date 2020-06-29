package juc.T_012_InterView;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 使用wait和notify/notifyAll来实现
 */
public class MyContainer<T> {

    final private LinkedList <T> linkedList = new LinkedList();
    final private int MAX = 10;

    private int count = 0;


    public synchronized void put(T t) {

        while (linkedList.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        linkedList.add(t);
        ++count;
        this.notifyAll();
    }


    public synchronized T get() {
        T t = null;
        while (linkedList.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t = linkedList.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }


    public static void main(String[] args) {
        MyContainer <String> objectMyContainer = new MyContainer <>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(objectMyContainer.get());
                }
            }, "消费者" + i).start();
        }


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    objectMyContainer.put(Thread.currentThread().getName() + " " + j);
                }
            }, "生产者" + i).start();
        }
    }
}
