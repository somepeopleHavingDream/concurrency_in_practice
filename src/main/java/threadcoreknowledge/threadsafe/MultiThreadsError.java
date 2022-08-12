package threadcoreknowledge.threadsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第一种：运行结果出错
 * 演示计数不准确（减少），找出具体出错的位置
 *
 * @author yangxin
 * 2019/10/03 16:14
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaAvoidManuallyCreateThread"})
public class MultiThreadsError implements Runnable {
    private static final MultiThreadsError INSTANCE = new MultiThreadsError();
    private int index = 0;
    private final boolean[] marked = new boolean[10000000];
    private static final AtomicInteger REAL_INDEX = new AtomicInteger();
    private static final AtomicInteger WRONG_COUNT = new AtomicInteger();
    private static final CyclicBarrier CYCLIC_BARRIER_1 = new CyclicBarrier(2);
    private static final CyclicBarrier CYCLIC_BARRIER_2 = new CyclicBarrier(2);

    @Override
    public void run() {
        marked[0] = true;

        for (int i = 0; i < 10000; i++) {
            try {
                CYCLIC_BARRIER_2.reset();
                CYCLIC_BARRIER_1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            index++;
            try {
                CYCLIC_BARRIER_1.reset();
                CYCLIC_BARRIER_2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            REAL_INDEX.incrementAndGet();

            synchronized (INSTANCE) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生错误" + index);
                    WRONG_COUNT.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(INSTANCE);
        Thread thread2 = new Thread(INSTANCE);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果是" + INSTANCE.index);
        System.out.println("真正运行的次数" + REAL_INDEX.get());
        System.out.println("错误次数" + WRONG_COUNT.get());
    }
}
