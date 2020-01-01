package threadcoreknowledge.threadsafe;

import java.util.concurrent.TimeUnit;

/**
 * 观察者
 *
 * @author yangxin
 * 2019/10/03 16:55
 */
public class Observer {
    private int count;

    Observer(MySource source) {
        source.registerListener(e -> System.out.println("\n我得到的数字是" + count));
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {
            });
        }).start();

        Observer observer = new Observer(mySource);
    }

    static class MySource {
        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
                return;
            }

            System.out.println("还未初始化完毕");
        }
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {}
}
