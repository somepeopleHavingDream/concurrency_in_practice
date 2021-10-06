package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 用两个线程交替打印0~100的奇偶数，用wait和notify
 *
 * @author yangxin
 * 2019/09/20 15:01
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class WaitNotifyPrintOddEvenWait {

    private static int count = 0;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new TurningRunner(), "偶数").start();
        Thread.sleep(10);
        new Thread(new TurningRunner(), "奇数").start();
    }

    /**
     * 1. 拿到锁，我们就打印
     * 2. 打印完，唤醒其他线程，自己就休眠
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (LOCK) {
                    // 拿到锁就打印
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    LOCK.notify();

                    if (count <= 100) {
                        try {
                            // 如果任务还没结束，就让出当前的锁，并休眠
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
