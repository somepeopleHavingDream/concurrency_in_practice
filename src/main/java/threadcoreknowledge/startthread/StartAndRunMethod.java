package threadcoreknowledge.startthread;

/**
 * 对比start和run两种启动线程的方式
 *
 * @author yangxin
 * 2019/09/17 13:58
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        runnable.run();

        new Thread(runnable).start();
    }
}
