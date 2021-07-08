package threadcoreknowledge.createthreads.wrongways;

/**
 * 匿名内部类实现线程
 *
 * @author yangxin
 * 2019/09/14 16:16
 */
@SuppressWarnings({"Convert2Lambda", "AnonymousHasLambdaAlternative"})
public class AnonymousInnerClassDemo {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
