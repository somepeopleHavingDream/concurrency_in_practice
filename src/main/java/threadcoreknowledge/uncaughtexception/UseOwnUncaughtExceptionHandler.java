package threadcoreknowledge.uncaughtexception;

/**
 * 使用刚才自己写的UncaughtExceptionHandler
 *
 * @author yangxin
 * 2019/09/25 15:33
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class UseOwnUncaughtExceptionHandler implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));

        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-1").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-2").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-3").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-4").start();
    }
}
