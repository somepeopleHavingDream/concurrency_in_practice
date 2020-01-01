package threadcoreknowledge.jmm;

/**
 * @author yangxin
 * 2019/10/04 17:44
 */
public class DisappearRequest1 implements Runnable {
    private static final DisappearRequest1 instance = new DisappearRequest1();
    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            synchronized (instance) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
