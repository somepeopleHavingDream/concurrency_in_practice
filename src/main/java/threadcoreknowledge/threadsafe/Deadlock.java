package threadcoreknowledge.threadsafe;

import java.util.concurrent.TimeUnit;

/**
 * 第二章线程安全问题，演示死锁
 *
 * @author yangxin
 * 2019/10/03 16:16
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class Deadlock implements Runnable {
    private int flag = 1;
    private static final Object O1 = new Object();
    private static final Object O2 = new Object();

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (O1) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (O2) {
                    System.out.println("1");
                }
            }
        }

        if (flag == 0) {
            synchronized (O2) {
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (O1) {
                    System.out.println("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        Deadlock r1 = new Deadlock();
        Deadlock r2 = new Deadlock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
