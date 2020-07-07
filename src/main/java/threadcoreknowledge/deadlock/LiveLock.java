package threadcoreknowledge.deadlock;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 演示活锁问题
 *
 * @author yangxin
 * 2019/10/05 17:18
 */
public class LiveLock {

    @AllArgsConstructor
    @Data
    static class Spoon {

        private Diner owner;

        synchronized void use() {
            System.out.printf("%s has eaten!", owner.name);
        }
    }

    static class Diner {

        private final String name;
        private boolean isHungry;

        Diner(String name) {
            this.name = name;
            this.isHungry = true;
        }

        void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                if (spoon.owner != this) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                Random random = new Random();
//                if (spouse.isHungry) {
                if (spouse.isHungry && random.nextInt(10) < 9) {
                    System.out.println(name + ": 亲爱的" + spouse.name + "你先吃吧");
                    spoon.setOwner(spouse);
                    continue;
                }

                spoon.use();
                isHungry = false;
                System.out.println(name + ": 我吃完了");
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("牛郎");
        Diner wife = new Diner("织女");

        Spoon spoon = new Spoon(husband);

        new Thread(() -> husband.eatWith(spoon, wife)).start();
        new Thread(() -> wife.eatWith(spoon, husband)).start();
    }
}
