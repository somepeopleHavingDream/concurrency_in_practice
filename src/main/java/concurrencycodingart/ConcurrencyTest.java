package concurrencycodingart;

/**
 * 演示串行和并发执行并累加操作的时间
 *
 * @author yangxin
 * 2020/07/09 14:58
 */
public class ConcurrencyTest {

    private static final Long count = 100001L;

    /**
     * 表1-1 测试结果
     * 循环次数 串行执行耗时/ms   并发执行耗时  并发比串行快多少
     * 1亿   130 77  约1倍
     * 1千万  18  9   约1呗
     * 1百万  5   5   差不多
     * 10万  4   3   慢
     * 1万   0   1   慢
     *
     * 结论：当并发执行累加操作不超过百万次时，速度会比串行执行累加操作要慢。
     * 那么，为什么并发执行的速度会比串行慢呢？这是因为线程有创建和上下文切换的开销。
     */
    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("serial: " + time + "ms, b = " + b + ", a = " + a);
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
            }
        });
        thread.start();

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency: " + time + "ms, b = " + b);
    }
}
