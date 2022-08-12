package threadcoreknowledge.sixstates;

/**
 * 展示线程的NEW、RUNNABLE、TERMINATED状态。
 * 即使是正在运行，也是Runnable状态，而不是Running
 *
 * @author yangxin
 * 2019/09/19 11:00
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaUndefineMagicConstant"})
public class NewRunnableTerminated implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        // 打印出NEW的状态
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出RUNNABLE的状态，即使是正在运行，也是RUNNABLE,而不是RUNNING
        System.out.println(thread.getState());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出TERMINATED状态
        System.out.println(thread.getState());
    }
}
