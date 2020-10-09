package threadcoreknowledge.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 方法抛异常后，会释放锁。展示不抛出异常前和抛出异常后的对比。
 * 一旦抛出了异常，第二个线程会立刻进入同步方法，意味着锁已经释放
 *
 * @author yangxin
 * 2020/01/19 17:51
 */
@SuppressWarnings("DuplicatedCode")
public class SynchronizedException9 implements Runnable {

    private static final SynchronizedException9 instance = new SynchronizedException9();

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

    public synchronized void method1() {
        System.out.println("我是非静态加锁的方法1。我叫：" + Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
//            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
//        System.out.println(Thread.currentThread().getName() + "运行结束！");
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
