package threadcoreknowledge.singleton;

/**
 * 懒汉式（线程不安全）（不可用，不推荐）
 *
 * @author yangxin
 * 2019/10/04 18:12
 */
public class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {

    }

    public synchronized static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
