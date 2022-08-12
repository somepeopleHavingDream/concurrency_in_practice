package threadcoreknowledge.createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器创建线程
 *
 * @author yangxin
 * 2019/09/14 16:12
 */
@SuppressWarnings("AlibabaAvoidUseTimer")
public class DemoTimerTask {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          System.out.println(Thread.currentThread().getName());
                                      }
                                  },
                1000, 1000);
    }
}
