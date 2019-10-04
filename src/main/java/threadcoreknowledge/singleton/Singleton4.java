package threadcoreknowledge.singleton;

/**
 * 懒汉式（线程安全）（可用，不推荐）
 *
 * @author yangxin
 * 2019/10/04 18:12
 */
public class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {

    }

    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
