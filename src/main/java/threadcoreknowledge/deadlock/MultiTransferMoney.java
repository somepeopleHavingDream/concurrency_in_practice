package threadcoreknowledge.deadlock;

import java.util.Random;

/**
 * 多人同时转账，依然很危险
 *
 * @author yangxin
 * 2019/10/05 15:23
 */
public class MultiTransferMoney {

    private static final int NUM_ACCOUNT = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATIONS = 1000000;
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random random = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNT];
//        for (TransferMoney.Account account : accounts) {
//            account = new TransferMoney.Account(NUM_MONEY);
//        }

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney.Account(NUM_MONEY);
        }

//        for (TransferMoney.Account account : accounts) {
//            System.out.println(account);
//        }

        class TransferThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = random.nextInt(NUM_ACCOUNT);
                    int toAcct = random.nextInt(NUM_ACCOUNT);
                    int amount = random.nextInt(NUM_MONEY);

                    try {
                        TransferMoney.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
