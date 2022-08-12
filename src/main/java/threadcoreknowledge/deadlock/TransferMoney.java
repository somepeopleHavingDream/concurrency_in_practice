package threadcoreknowledge.deadlock;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 转账时候遇到死锁，一旦打开注释，便会发生死锁
 *
 * @author yangxin
 * 2019/10/05 15:07
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "CommentedOutCode"})
public class TransferMoney implements Runnable {

    private int flag = 1;

    private static final Account A = new Account(500);
    private static final Account B = new Account(500);
    private static final Object LOCK = new Object();

    @Override
    public void run() {
        if (flag == 1) {
            try {
                transferMoney(A, B, 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (flag == 0) {
            try {
                transferMoney(B, A, 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void transferMoney(Account from, Account to, int amount) throws InterruptedException {

        class Helper {

            private void transfer() {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账" + amount + "元");
            }
        }

        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (LOCK) {
                synchronized (to) {
                    synchronized (from) {
                        new Helper().transfer();
                    }
                }
            }
        }

//        synchronized (from) {
////            Thread.sleep(500);
//            synchronized (to) {
//                if (from.balance - amount < 0) {
//                    System.out.println("余额不足，转账失败");
//                }
//                from.balance -= amount;
//                to.balance += amount;
////                to.balance = to.balance + amount;
//                System.out.println("成功转账" + amount + "元");
//            }
//        }
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
        System.out.println("a的余额" + A.balance);
        System.out.println("b的余额" + B.balance);
    }
}
