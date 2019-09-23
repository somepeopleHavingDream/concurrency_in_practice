package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * Id从1开始，JVM运行起来后，我们自己创建线程的Id早已不是0
 *
 * @author yangxin
 * 2019/09/23 22:02
 */
public class Id {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的Id" + Thread.currentThread().getId());
        System.out.println("子线程的Id" + thread.getId());
    }
}
