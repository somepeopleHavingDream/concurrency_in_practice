package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * 通过讲解join原理，分析出join的代替写法
 *
 * @author yangxin
 * 2019/09/23 17:56
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaRemoveCommentedCode"})
public class JoinPrinciple {

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });

        thread1.start();
        System.out.println("开始等待子线程运行完毕");
        synchronized (thread1) {
            thread1.wait();
        }
//        thread1.join();
        System.out.println("所有子线程执行完毕");
    }
}
