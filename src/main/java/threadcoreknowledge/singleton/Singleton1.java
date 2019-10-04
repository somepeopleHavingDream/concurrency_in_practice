package threadcoreknowledge.singleton;

/**
 * 饿汉式（静态常量）（可用）
 *
 * @author yangxin
 * 2019/10/04 18:09
 */
public class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
