package threadcoreknowledge.cas;

/**
 * 两个线程竞争
 *
 * @author yangxin
 * 2019/12/26 11:07
 */
public class TwoThreadsCompetition implements Runnable {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }

        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition twoThreadsCompetition = new TwoThreadsCompetition();
        twoThreadsCompetition.value = 0;

        Thread t1 = new Thread(twoThreadsCompetition, "Thread 1");
        Thread t2 = new Thread(twoThreadsCompetition, "Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(twoThreadsCompetition.value);
    }
}
