package threadcoreknowledge.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

/**
 * 用ThreadMXBean检测死锁
 *
 * @author yangxin
 * 2019/10/05 16:01
 */
@SuppressWarnings({"DuplicatedCode", "AlibabaAvoidManuallyCreateThread"})
public class ThreadMxBeanDetection implements Runnable {

    private int flag = 1;

    private static final Object O1 = new Object();
    private static final Object O2 = new Object();

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (O1) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (O2) {
                    System.out.println("线程1成功拿到两把锁");
                }
            }
        }
        if (flag == 0) {
            synchronized (O2) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (O1) {
                    System.out.println("线程2成功拿到两把锁");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadMxBeanDetection r1 = new ThreadMxBeanDetection();
        ThreadMxBeanDetection r2 = new ThreadMxBeanDetection();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

        TimeUnit.MILLISECONDS.sleep(1000);
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMxBean.findDeadlockedThreads();
        for (long deadlockedThread : deadlockedThreads) {
            ThreadInfo threadInfo = threadMxBean.getThreadInfo(deadlockedThread);
            System.out.println("发现死锁" + threadInfo.getThreadName());
        }
    }
}
