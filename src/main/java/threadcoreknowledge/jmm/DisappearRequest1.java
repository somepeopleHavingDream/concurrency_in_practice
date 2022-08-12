package threadcoreknowledge.jmm;

/**
 * @author yangxin
 * 2019/10/04 17:44
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaAvoidManuallyCreateThread"})
public class DisappearRequest1 implements Runnable {

    private static final DisappearRequest1 INSTANCE = new DisappearRequest1();
    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            synchronized (INSTANCE) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 100; j++) {
            i = 0;
            Thread t1 = new Thread(INSTANCE);
            Thread t2 = new Thread(INSTANCE);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(i);
        }
    }
}
