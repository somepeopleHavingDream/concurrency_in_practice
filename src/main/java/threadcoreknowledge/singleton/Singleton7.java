package threadcoreknowledge.singleton;

/**
 * 静态内部类方式，可用
 *
 * @author yangxin
 * 2019/10/04 18:12
 */
public class Singleton7 {
    private Singleton7() {

    }

    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public synchronized static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
