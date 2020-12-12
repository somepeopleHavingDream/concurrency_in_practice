package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
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

    @SuppressWarnings({"BusyWait", "DuplicatedCode"})
    public static void main(String[] args) throws InterruptedException {
        // 阻塞队列
        BlockingQueue<Integer> storage = new ArrayBlockingQueue<>(10);

        // 生产者，开启线程
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        // 消费者，进行消费
        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        // 一旦消费者不需要更多数据了，我们应该让生产者也停下来，但是实际情况
        producer.canceled = true;
        System.out.println(producer.canceled);
        System.out.println("此时生产者线程的状态是：" + producerThread.getState());
    }
}

/**
 * 生产者
 *
 * @author yangxin
 * 2020/01/21 17:17
 */
class Producer implements Runnable {

    volatile boolean canceled = false;

    private final BlockingQueue<Integer> storage;

    Producer(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    // 入队之后马上就会释放锁，陷入等待状态，导致canceled值变更后程序无法察觉
                    storage.put(num);
                    System.out.println(num + "是100的倍数，被放到仓库中了。");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 这句话未被打印，程序仍然在跑死循环
            System.out.println("生产者结束运行");
        }
    }
}

/**
 * 消费者
 */
class Consumer {

    BlockingQueue<Integer> storage;

    Consumer(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    boolean needMoreNums() {
        return Math.random() < 0.95;
    }
}
