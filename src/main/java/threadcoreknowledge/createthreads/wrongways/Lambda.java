package threadcoreknowledge.createthreads.wrongways;

/**
 * lambda表达式创建线程
 *
 * @author yangxin
 * 2019/09/14 16:20
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
