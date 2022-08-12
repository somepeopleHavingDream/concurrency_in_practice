package threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile适用的情况1
 *
 * @author yangxin
 * 2019/10/04 17:26
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaUndefineMagicConstant"})
public class UseVolatile1 implements Runnable {

    private volatile boolean done = false;
    private final AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            done = true;
            realA.incrementAndGet();
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) throws InterruptedException {
        UseVolatile1 r = new UseVolatile1();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.done);
        System.out.println(r.realA.get());
    }
}
