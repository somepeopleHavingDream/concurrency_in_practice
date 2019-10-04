package threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不适用于volatile的场景2
 *
 * @author yangxin
 * 2019/10/04 17:18
 */
public class NoVolatile2 implements Runnable {
    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            done = !done;
            realA.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new NoVolatile2();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((NoVolatile2) r).done);
        System.out.println(((NoVolatile2) r).realA.get());
    }
}
