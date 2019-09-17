package threadcoreknowledge.startthread;

/**
 * 演示不能两次调用start方法，否则会报错
 *
 * @author yangxin
 * 2019/09/17 14:09
 */
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
