package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 证明wait只释放当前的那把锁
 *
 * @author yangxin
 * 2019/09/20 11:51
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "WaitWhileHoldingTwoLocks"})
public class WaitNotifyReleaseOwnMonitor {

    private static final Object RESOURCE_A = new Object();
    private static final Object RESOURCE_B = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (RESOURCE_A) {
                System.out.println("ThreadA got resourceA lock");

                synchronized (RESOURCE_B) {
                    System.out.println("ThreadA got resourceB lock");

                    try {
                        System.out.println("ThreadA releases resourceA lock");
                        RESOURCE_A.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (RESOURCE_A) {
                System.out.println("ThreadB got resourceA lock");
                System.out.println("ThreadB tries to get resourceB lock");

                synchronized (RESOURCE_B) {
                    System.out.println("ThreadB got resourceB lock");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
