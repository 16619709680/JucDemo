package juc.T_010_ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 */
public class T10_ReadWriterLock {

    static Lock lock = new ReentrantLock();
    private static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static Lock readLock = readWriteLock.readLock();
    static Lock writerLock = readWriteLock.writeLock();


    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("Read Over.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static  void wirter(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("Wirter Over");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) {

        Runnable read =()-> read(readLock);
        Runnable writer =()-> wirter(writerLock,new Integer(5));

        for (int i = 0; i < 20; i++) {
            new Thread(read).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(writer).start();
        }

    }

}
