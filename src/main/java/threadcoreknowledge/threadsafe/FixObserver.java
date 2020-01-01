package threadcoreknowledge.threadsafe;

import java.util.concurrent.TimeUnit;

/**
 * 用工厂模式修复刚才的初始化问题
 *
 * @author yangxin
 * 2019/10/03 17:23
 */
public class FixObserver {
    private int count;
    private EventListener listener;

    private FixObserver(MySource source) {
        listener = e -> System.out.println("\n我得到的数字是" + count);
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static FixObserver getInstance(MySource source) {
        FixObserver safeListener = new FixObserver(source);
        source.registerListener(safeListener.listener);
        return safeListener;
    }

    public static void main(String[] args) {
        Observer.MySource mySource = new Observer.MySource();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Observer.Event() {
            });
        }).start();

        Observer observer = new Observer(mySource);
    }

    static class MySource {
        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Observer.Event e) {
            if (listener != null) {
                listener.onEvent(e);
                return;
            }

            System.out.println("还未初始化完毕");
        }
    }

    interface EventListener {
        void onEvent(Observer.Event e);
    }

    interface Event {}
}
