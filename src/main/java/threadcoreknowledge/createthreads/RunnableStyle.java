package threadcoreknowledge.createthreads;

/**
 * 用Runnable方式创建线程
 *
 * @author yangxin
 * 2019/09/14 15:36
 */
public class RunnableStyle implements Runnable{

    public void run() {
        System.out.println("用Runnable方式实现线程");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
}
