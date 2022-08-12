package threadcoreknowledge.sixstates;

/**
 * @author yangxin
 * 2020/08/07 17:44
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ToGetLockAfterNotify implements Runnable {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private int flag;

    public static void main(String[] args) {
        ToGetLockAfterNotify toGetLockAfterNotify1 = new ToGetLockAfterNotify();
        ToGetLockAfterNotify toGetLockAfterNotify2 = new ToGetLockAfterNotify();
        toGetLockAfterNotify1.flag = 0;
        toGetLockAfterNotify2.flag = 1;

        new Thread(toGetLockAfterNotify1).start();
        new Thread(toGetLockAfterNotify2).start();
    }

    @Override
    public void run() {
        // flag为0的线程先拿到锁1
        if (flag == 0) {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "拿到lock1.");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // flag为1的线程先拿到锁2
        if (flag == 1) {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "拿到lock2.");
                System.out.println(Thread.currentThread().getName() + "尝试获取lock1...");

                System.out.println(Thread.currentThread() + " " + Thread.currentThread().getState());
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "拿到lock1.");
                }
            }
        }
    }
}
