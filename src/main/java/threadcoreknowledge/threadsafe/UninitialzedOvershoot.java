package threadcoreknowledge.threadsafe;

import lombok.ToString;

import java.util.concurrent.TimeUnit;

/**
 * 初始化未完毕，就this赋值
 *
 * @author yangxin
 * 2019/10/03 16:45
 */
public class UninitialzedOvershoot {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        TimeUnit.MILLISECONDS.sleep(105);
        if (point != null) {
            System.out.println(point);
        }
    }
}

@ToString
class Point {
    private final int x, y;

    Point(int x, int y) throws InterruptedException {
        this.x = x;
        UninitialzedOvershoot.point = this;
        TimeUnit.MICROSECONDS.sleep(1);
        this.y = y;
    }
}

class PointMaker extends Thread {
    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
