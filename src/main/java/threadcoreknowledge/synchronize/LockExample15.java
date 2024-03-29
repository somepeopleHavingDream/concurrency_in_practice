package threadcoreknowledge.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 展示Lock的方法
 *
 * @author yangxin
 * 2020/01/20 14:13
 */
@SuppressWarnings({"AlibabaLockShouldWithTryFinally", "ResultOfMethodCallIgnored"})
public class LockExample15 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        lock.tryLock();
    }
}
