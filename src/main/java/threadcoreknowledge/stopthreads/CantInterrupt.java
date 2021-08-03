package threadcoreknowledge.stopthreads;

/**
 * 如果while里面放try/catch，会导致中断失效
 *
 * @author yangxin
 * 2019/09/18 13:50
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaAvoidManuallyCreateThread"})
public class CantInterrupt {

    @SuppressWarnings("BusyWait")
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
