package threadcoreknowledge.synchronize;

import java.util.concurrent.TimeUnit;

@SuppressWarnings({"DuplicatedCode", "AlibabaClassMustHaveAuthor", "AlibabaAvoidManuallyCreateThread"})
public class SynchronizedClassStatic4 implements Runnable {

    private static final SynchronizedClassStatic4 INSTANCE_1 = new SynchronizedClassStatic4();
    private static final SynchronizedClassStatic4 INSTANCE_2 = new SynchronizedClassStatic4();

    @Override
    public void run() {
        try {
            method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(INSTANCE_1);
        Thread t2 = new Thread(INSTANCE_2);
        t1.start();
        t2.start();
        while (true) {
            if (!t1.isAlive() && !t2.isAlive()) {
                break;
            }

        }
        System.out.println("finished");
    }

    public static synchronized void method() throws InterruptedException {
        System.out.println("我是类锁的第一种形式：static形式。我叫：" + Thread.currentThread().getName());
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "运行结束！");
    }
}
