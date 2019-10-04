package threadcoreknowledge.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示重排序的现象
 * “直到达到某个条件才停止”，测试小概率事件
 *
 * @author yangxin
 * 2019/10/04 15:03
 */
public class OutOfOrderExecution {
    private volatile static int x = 0, y = 0;
    private volatile static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread two = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });
            two.start();
            one.start();
            latch.countDown();
            one.join();
            two.join();

            String result = "第" + i + "次（" + x + "," + y + ")";
            if (x == 0 && y == 0) {
//            if (x == 1 && y == 1) {
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }
    }
}
