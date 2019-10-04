package threadcoreknowledge.singleton;

/**
 * 双重检查（推荐面试使用）
 *
 * @author yangxin
 * 2019/10/04 18:12
 */
public class Singleton6 {
    private volatile static Singleton6 instance;

    private Singleton6() {

    }

    public synchronized static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
