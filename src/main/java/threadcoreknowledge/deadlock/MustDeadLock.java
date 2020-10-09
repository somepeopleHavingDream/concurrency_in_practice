package threadcoreknowledge.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 必定发生死锁的情况
 *
 * 死锁的4个必要条件
 * 1. 互斥条件
 * 2. 请求与保持条件
 * 3. 不剥夺条件
 * 4. 循环等待条件
 *
 * @author yangxin
 * 2019/10/05 14:51
 */
@SuppressWarnings("DuplicatedCode")
public class MustDeadLock implements Runnable {

    private int flag = 1;

    private static final Object o1 = new Object();
    private static final Object o2 = new Object();

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("线程1成功拿到两把锁");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("线程2成功拿到两把锁");
                }
            }
        }
    }

    public static void main(String[] args) {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
