package threadcoreknowledge.createthreads;

/**
 * 用Thread方式实现线程
 *
 * @author yangxin
 * 2019/09/14 15:39
 */
public class ThreadStyle extends Thread {
    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
