package threadcoreknowledge.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 同时访问同步方法和非同步方法
 *
 * @author yangxin
 * 2020/01/19 17:32
 */
public class SynchronizedYesAndNo6 implements Runnable {

    private static final SynchronizedYesAndNo6 instance1 = new SynchronizedYesAndNo6();
    private static final SynchronizedYesAndNo6 instance2 = new SynchronizedYesAndNo6();

    @Override
    public void run() {
        try {
            if (Thread.currentThread().getName().equals("Thread-0")) {
                method1();
            } else {
                method2();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void method1() throws InterruptedException {
        System.out.println("我是加锁的方法。我叫：" + Thread.currentThread().getName());
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！");
    }

    public void method2() throws InterruptedException {
        System.out.println("我是没加锁的方法。我叫：" + Thread.currentThread().getName());
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (true) {
            if (!t1.isAlive() && !t2.isAlive()) break;

        }
        System.out.println("finished");
    }
}
