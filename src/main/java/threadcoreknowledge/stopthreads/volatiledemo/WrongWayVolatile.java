package threadcoreknowledge.stopthreads.volatiledemo;

/**
 * 演示用volatile的局限：part1 看似可行
 *
 * @author yangxin
 * 2019/09/17 14:45
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaAvoidManuallyCreateThread"})
public class WrongWayVolatile implements Runnable {

    private volatile boolean canceled = false;

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;

                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile r = new WrongWayVolatile();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(5000);
        r.canceled = true;
    }
}
