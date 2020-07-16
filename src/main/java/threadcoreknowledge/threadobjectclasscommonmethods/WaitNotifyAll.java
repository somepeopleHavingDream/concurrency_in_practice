package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 3个线程，线程1和线程2首先被阻塞，线程3唤醒它们。notify，notifyAll
 *
 * @author yangxin
 * 2019/09/20 11:31
 */
public class WaitNotifyAll implements Runnable {

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + " got resourceA lock");

            try {
                System.out.println(Thread.currentThread().getName() + " waits to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "'s waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                resourceA.notifyAll();
                System.out.println("ThreadC notified.");
            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(200);
        threadC.start();
    }
}
