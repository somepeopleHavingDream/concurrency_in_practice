package threadcoreknowledge.jmm;

/**
 * 演示可见性带来的问题
 *
 * @author yangxin
 * 2019/10/04 15:38
 */
public class FieldVisibility {
    int a = 1;
    int b = 2;
    int c = 2;
    int d = 2;
//    volatile int b = 2;

    private void change() {
        a = 3;
        b = 4;
        c = 5;
        synchronized (this) {
            d = 6;
        }
//        b = a;
    }

    private void print() {
        synchronized (this) {
            int aa = a;
        }
        int bb = b;
        int cc = c;
        int dd = d;
        System.out.println("b = " + b + "; a = " + a);
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility test = new FieldVisibility();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.change();
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.print();
            }).start();
        }
    }
}
