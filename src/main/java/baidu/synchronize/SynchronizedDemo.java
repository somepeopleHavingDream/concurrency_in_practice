package baidu.synchronize;

/**
 * @author yangxin
 * 2020/07/10 16:42
 */
public class SynchronizedDemo {

//    private static int count = 0;
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 1000000; i++) {
//            count++;
//        }
//    }
//
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(new SynchronizedDemo()).start();
//        }
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("result: " + count);
//    }

    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {

        }
        method();
    }

    private static void method() {

    }
}
