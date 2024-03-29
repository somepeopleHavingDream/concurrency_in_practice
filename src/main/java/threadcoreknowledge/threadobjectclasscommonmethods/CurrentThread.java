package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 演示打印main，Thread-0，Thread-1
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaClassMustHaveAuthor"})
public class CurrentThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new CurrentThread().run();
        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }
}
