package threadcoreknowledge.synchronize.freeclass;

import java.util.concurrent.TimeUnit;

/**
 * 同步类方法和普通方法
 *
 * @author yangxin
 * 2020/01/19 17:45
 */
public class SynchronizedStaticAndNormal8 implements Runnable {

    private static final SynchronizedStaticAndNormal8 instance = new SynchronizedStaticAndNormal8();

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

    public synchronized static void method1() throws InterruptedException {
        System.out.println("我是静态加锁的方法1。我叫：" + Thread.currentThread().getName());
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！");
    }

    public synchronized void method2() throws InterruptedException {
        System.out.println("我是非静态加锁的方法2。我叫：" + Thread.currentThread().getName());
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！");
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
