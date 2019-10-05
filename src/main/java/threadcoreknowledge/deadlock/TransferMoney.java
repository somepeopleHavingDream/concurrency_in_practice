package threadcoreknowledge.deadlock;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 转账时候遇到死锁，一旦打开注释，便会发生死锁
 *
 * @author yangxin
 * 2019/10/05 15:07
 */
public class TransferMoney implements Runnable {
    int flag = 1;

    static Account a = new Account(500);
    static Account b = new Account(500);

    @Override
    public void run() {
        if (flag == 1) {
            try {
                transferMoney(a, b, 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (flag == 0) {
            try {
                transferMoney(b, a, 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void transferMoney(Account from, Account to, int amount) throws InterruptedException {
        synchronized (from) {
//            Thread.sleep(500);
            synchronized (to) {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败");
                }
                from.balance -= amount;
                to.balance = to.balance + amount;
                System.out.println("成功转账" + amount + "元");
            }
        }
    }

    @AllArgsConstructor
    @ToString
    static class Account {
        int balance;
    }

    public static void main(String[] args) throws InterruptedException {
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a的余额" + a.balance);
        System.out.println("b的余额" + b.balance);
    }
}
