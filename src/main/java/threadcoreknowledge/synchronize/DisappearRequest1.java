package threadcoreknowledge.synchronize;

/**
 * 消失的请求
 *
 * @author yangxin
 * 2020/01/19 10:54
 */
public class DisappearRequest1 implements Runnable {

    private static Integer count = 0;

    private static final DisappearRequest1 instance = new DisappearRequest1();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 100000; i++) {
            count++;
        }
    }
}
