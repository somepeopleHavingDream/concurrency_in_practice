package threadcoreknowledge.synchronize;

/**
 * @author yangxin
 * 2020/07/07 11:42
 */
public class Synchronized {

    /**
     * synchronized关键字可放于方法返回值前任意位置，本示例应当注意到sleep()不会释放对监视器的锁定
     * 实例方法
     */
    public synchronized void instanceMethod() {
        for (int i = 0; i < 5; i++) {
            System.out.println("instanceMethod.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 静态方法
     */
    public synchronized static void staticMethod() {
        for (int i = 0; i < 5; i++) {
            System.out.println("staticMethod.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void thisMethod() {
        // this对象
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println("thisMethod.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void classMethod() {
        // class对象
        synchronized (Synchronized.class) {
            System.out.println("classMethod.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void anyObject() {
        // 任意对象
        synchronized ("anything") {
            for (int i = 0; i < 5; i++) {
                System.out.println("anyObject");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
