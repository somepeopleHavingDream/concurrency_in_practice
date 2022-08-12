package threadcoreknowledge.synchronize;

import java.util.concurrent.TimeUnit;

@SuppressWarnings({"DuplicatedCode", "AlibabaClassMustHaveAuthor", "AlibabaAvoidManuallyCreateThread"})
public class SynchronizedClassClass5 implements Runnable {

    private static final SynchronizedClassClass5 INSTANCE_1 = new SynchronizedClassClass5();
    private static final SynchronizedClassClass5 INSTANCE_2 = new SynchronizedClassClass5();

    @Override
    public void run() {
        try {
            method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void method() throws InterruptedException {
        synchronized (SynchronizedClassClass5.class) {
            System.out.println("我是类锁的第二种形式：synchronized(*.class)。我叫：" + Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "运行结束！");
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
}
