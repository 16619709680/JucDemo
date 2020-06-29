package juc.T_012_InterView;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class MyContainer2<T> {

    final private LinkedList <T> linkedList = new LinkedList();
    final private int MAX = 10;

    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition producter = lock.newCondition();
    private Condition customer = lock.newCondition();


    public void put(T t) {
        try {
            lock.lock();
            while (linkedList.size() == MAX) {
                producter.await();
            }
            linkedList.add(t);
            ++count;
            customer.signalAll();//通知消费者消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public T get() {
        T t = null;
        try {
            lock.lock();
            while (linkedList.size() == 0) {
                customer.await();
            }

            t = linkedList.removeFirst();
            count--;
            producter.signalAll();//通知生产者生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }


    public static void main(String[] args) {
        MyContainer2 <String> objectMyContainer = new MyContainer2 <>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(objectMyContainer.get());
                }
            }, "消费者" + i).start();
        }


       /* try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    objectMyContainer.put(Thread.currentThread().getName() + " " + j);
                }
            }, "生产者" + i).start();
        }
    }
}
