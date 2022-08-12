package threadcoreknowledge.sixstates;

/**
 * 展示BLOCKED、WAITING、TIMEDWAITING
 *
 * @author yangxin
 * 2020/07/17 15:03
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class BlockedWaitingTimedWaiting implements Runnable {

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        // 打印出TIMED_WAITING状态，因为正在执行Thread.sleep(1000)
        System.out.println(thread1.getState());
        // 打印出BLOCKED状态，因为thread2想拿到sync()的锁，却拿不到
        System.out.println(thread2.getState());

        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出WAITING状态，因为执行了wait()
        System.out.println(thread1.getState());
    }
}
