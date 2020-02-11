package threadcoreknowledge.singleton;

/**
 * 饿汉式（静态代码块）（可用）
 *
 * @author yangxin
 * 2019/10/04 18:09
 */
public class Singleton2 {
    private final static Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }
}
