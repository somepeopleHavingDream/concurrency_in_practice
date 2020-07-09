package threadcoreknowledge.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 对象锁示例1，代码块形式
 *
 * @author yangxin
 * 2020/01/19 11:21
 */
public class SynchronizedObjectCodeBlock2 implements Runnable {

    private static final SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println("我是lock1。我叫" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "lock1部分运行结束。");
        }

        synchronized (lock2) {
            System.out.println("我是lock2。我叫" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "lock2运行结束。");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (true) {
            if (!t1.isAlive() && !t2.isAlive()) break;
        }
        System.out.println("finished");
    }
}
