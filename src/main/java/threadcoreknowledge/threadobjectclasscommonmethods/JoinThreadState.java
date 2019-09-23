package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * 先join再mainThread.getState()
 * 通过debugger看线程join前后状态对比
 *
 * @author yangxin
 * 2019/09/23 17:30
 */
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
                System.out.println(mainThread.getState());
                System.out.println("Thread-0运行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        System.out.println("等待子线程运行完毕");
        thread.join();
        System.out.println("子线程运行完毕");
    }
}
