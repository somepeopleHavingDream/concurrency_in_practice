package threadcoreknowledge.stopthreads;


/**
 * 带有sleep的中断线程的方法
 *
 * @author yangxin
 * 2019/09/17 14:52
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaAvoidManuallyCreateThread"})
public class RightWayStopThreadWithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
