package threadcoreknowledge.threadsafe;

/**
 * 第一种：运行结果出错
 * 演示计数不准确（减少），找出具体出错的位置
 */
public class MultiThreadsError implements Runnable {
    static MultiThreadsError instance = new MultiThreadsError();
    int index = 0;

    @Override
    public void run() {
//        while (index < 10000) {
//            index++;
//        }

        for (int i = 0; i < 10000; i++) {
            index++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(instance.index);
    }
}
