package threadcoreknowledge.synchronize.freeclass;

import java.util.concurrent.TimeUnit;

/**
 * 对象锁示例1，代码块形式
 *
 * @author yangxin
 * 2020/01/19 11:21
 */
public class SynchronizedObjectMethod3 implements Runnable {

    private static final SynchronizedObjectMethod3 instance = new SynchronizedObjectMethod3();

    @Override
    public void run() {
        try {
            method();
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public synchronized void method() throws InterruptedException {
        System.out.println("我的对象锁的方法修饰符形式，我叫" + Thread.currentThread().getName());
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！");
    }
}
