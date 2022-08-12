package threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不适用于volatile的场景
 *
 * @author yangxin
 * 2019/10/04 17:18
 */
@SuppressWarnings({"NonAtomicOperationOnVolatileField", "AlibabaUndefineMagicConstant", "AlibabaAvoidManuallyCreateThread"})
public class NoVolatile implements Runnable {

    private volatile int a;
    private final AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NoVolatile r = new NoVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.a);
        System.out.println(r.realA.get());
    }
}
