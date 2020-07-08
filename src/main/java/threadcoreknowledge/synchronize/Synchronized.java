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
            for (int i = 0; i < 5; i++) {
                System.out.println("classMethod.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

    public static void main(String[] args) {
        // 我们会发现输出结果总是以5个为最小单位交替出现，证明synchronized(this)和在实例方法上使用synchronized使用的是同一监视器。
//        Synchronized syn = new Synchronized();
//        for (int i = 0; i < 10; i++) {
//            new Thread(syn::thisMethod).start();
//            new Thread(syn::instanceMethod).start();
//        }

        // 输出以5个最小单位交替出现，证明两段代码是同一把锁，如果去掉任一synchronized，则会无规律地交替出现
        for (int i = 0; i < 10; i++) {
            Synchronized syn = new Synchronized();
            new Thread(Synchronized::staticMethod).start();
            new Thread(syn::classMethod).start();
        }
    }
}
