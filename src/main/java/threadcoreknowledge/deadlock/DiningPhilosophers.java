package threadcoreknowledge.deadlock;

import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * 演示哲学家就餐问题导致的死锁
 *
 * @author yangxin
 * 2019/10/05 16:23
 */
public class DiningPhilosophers {

    @AllArgsConstructor
    public static class Philosopher implements Runnable {

        private final Object leftChopstick;
        private final Object rightChopstick;

        @SuppressWarnings("InfiniteLoopStatement")
        @Override
        public void run() {
            try {
              while (true) {
                  doAction("Thinking");
                  synchronized (leftChopstick) {
                      doAction("Pick up left chopstick");
                      synchronized (rightChopstick) {
                          doAction("Picked up right chopstick - eating");
                          doAction("Put down right chopstick");
                      }
                      doAction("Put down left chopstick");
                  }
              }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            TimeUnit.MILLISECONDS.sleep((long) (Math.random()) * 10);
        }
    }

    public static void main(String[] args) {
        // 五个哲学家
        Philosopher[] philosophers = new Philosopher[5];

        // 五只筷子，注意：不是五双哦。
        Object[] chopsticks = new Object[philosophers.length];
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];

            philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
//            if (i == philosophers.length - 1) {
//                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
//            } else {
//                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
//            }
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
        }
    }
}
