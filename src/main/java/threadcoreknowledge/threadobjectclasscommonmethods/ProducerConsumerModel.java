package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 用wait/notify来实现
 *
 * @author yangxin
 * 2019/09/20 14:24
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ProducerConsumerModel {

    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

/**
 * @author yangxin
 * 2020/07/23 20:36
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
class Producer implements Runnable {

    private final EventStorage storage;

    Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

/**
 * @author yangxin
 * 2020/07/23 20:45
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
class Consumer implements Runnable {

    private final EventStorage storage;

    Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

/**
 * @author yangxin
 * 2020/07/23 20:36
 */
@SuppressWarnings("CallToPrintStackTrace")
class EventStorage {

    private final int maxSize;
    private final Queue<Date> storage;

    EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    synchronized void put() {
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("仓库里有了" + storage.size() + "个产品");
        notify();
    }

    synchronized void take() {
        while (storage.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿到了" + storage.poll() + "，现在仓库还剩下" + storage.size());
        notify();
    }
}
