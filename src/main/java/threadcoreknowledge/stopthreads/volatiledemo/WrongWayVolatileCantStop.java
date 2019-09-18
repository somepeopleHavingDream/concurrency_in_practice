package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.BlockingQueue;

/**
 * 演示用volatile的局限 part2
 * 陷入阻塞时，volatile是无法中断线程的
 * 此例中，生产者的生产速度很快，消费者速度慢，所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 *
 * @author yangxin
 * 2019/09/18 14:57
 */
public class WrongWayVolatileCantStop {
}

class Producer implements Runnable {
    public volatile boolean canceled = false;

    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + "是100的倍数，被放到仓库中了。");
                }
                num++;

                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}

class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }
}
