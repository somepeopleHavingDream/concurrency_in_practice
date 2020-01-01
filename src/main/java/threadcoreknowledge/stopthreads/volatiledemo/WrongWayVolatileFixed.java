package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用中断来修复刚才的无尽等待问题
 *
 * @author yangxin
 * 2019/09/18 15:24
 */
public class WrongWayVolatileFixed {
    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileFixed body = new WrongWayVolatileFixed();

        ArrayBlockingQueue storage = new ArrayBlockingQueue<>(10);
        Producer producer = body.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        Consumer consumer = body.new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        // 一旦消费者不需要更多数据了，我们应该让生产者也停下来，但是实际情况
//        producer.canceled = true;
//        System.out.println(producer.canceled);
        producerThread.interrupt();
    }

    class Producer implements Runnable {
        public volatile boolean canceled = false;

        BlockingQueue storage;

        Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 100000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        storage.put(num);
                        System.out.println(num + "是100的倍数，被放到仓库中了。");
                    }
                    num++;
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

        Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        boolean needMoreNums() {
            return Math.random() < 0.95;
        }
    }
}


