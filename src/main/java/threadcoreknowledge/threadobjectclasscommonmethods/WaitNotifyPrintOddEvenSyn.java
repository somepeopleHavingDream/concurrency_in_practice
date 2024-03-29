package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 *
 * @author yangxin
 * 2019/09/20 14:49
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaUndefineMagicConstant"})
public class WaitNotifyPrintOddEvenSyn {

    private static int count;
    private static final Object LOCK = new Object();

    /**
     * 新建2个线程
     * 1个只处理偶数，第二个只处理奇数（用位运算）
     * 用synchronized来通信
     */
    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                synchronized (LOCK) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }
        }, "偶数").start();

        new Thread(() -> {
            while (count < 100) {
                synchronized (LOCK) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }
        }, "奇数").start();
    }
}
