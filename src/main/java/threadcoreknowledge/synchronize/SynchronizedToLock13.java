package threadcoreknowledge.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized以锁的形式等价表达出来
 *
 * @author yangxin
 * 2020/01/20 10:19
 */
public class SynchronizedToLock13 {

    private final Lock lock = new ReentrantLock();

    private synchronized void method1() {
        System.out.println("我是Synchronized形式的锁");
    }

    private void method2() {
        lock.lock();
        try {
            System.out.println("我是lock形式的锁");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizedToLock13 synchronizedToLock13 = new SynchronizedToLock13();
        synchronizedToLock13.method1();
        synchronizedToLock13.method2();
    }
}
