package juc.T_004;

import java.util.concurrent.TimeUnit;

/**
 * 面试题：模拟银行账户取钱过程
 * 对业务写方法枷锁
 * 对业务读方法不加锁
 * 就会产生脏读
 */
public class Account {
    private String name;
    private Double balance;


    public synchronized void set(String name, Double balance) {
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    private synchronized double getBalance(String name) {
        return this.balance;
    }


    public static void main(String[] args) {
        Account account = new Account();

        new Thread(() -> account.set("zhangsan", 100.00)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> account.getBalance("zhangsan")).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(account.getBalance("zhangsan"));
    }


}
