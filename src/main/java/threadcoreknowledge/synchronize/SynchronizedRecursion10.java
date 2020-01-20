package threadcoreknowledge.synchronize;

/**
 * 可重入粒度测试：递归调用本方法
 *
 * @author yangxin
 * 2020/01/20 09:56
 */
public class SynchronizedRecursion10 {
    private int a = 0;

    public static void main(String[] args) {
        SynchronizedRecursion10 synchronizedRecursion10 = new SynchronizedRecursion10();
        synchronizedRecursion10.method1();
    }

    private synchronized void method1() {
        System.out.println("这是method01, a = " + a);
        if (a == 0) {
            a++;
            method1();
        }
    }
}
