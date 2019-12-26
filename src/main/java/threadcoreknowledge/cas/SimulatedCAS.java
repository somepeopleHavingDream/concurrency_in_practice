package threadcoreknowledge.cas;

/**
 * 模拟cas操作，等价代码
 *
 * @author yangxin
 * 2019/12/26 11:01
 */
public class SimulatedCAS {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }

        return oldValue;
    }
}
