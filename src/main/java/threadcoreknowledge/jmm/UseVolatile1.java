package threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile适用的情况1
 *
 * @author yangxin
 * 2019/10/04 17:26
 */
public class UseVolatile1 implements Runnable {
    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            done = true;
            realA.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new UseVolatile1();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((UseVolatile1) r).done);
        System.out.println(((UseVolatile1) r).realA.get());
    }
}
