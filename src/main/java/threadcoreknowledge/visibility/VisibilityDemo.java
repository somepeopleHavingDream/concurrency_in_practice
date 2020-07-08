package threadcoreknowledge.visibility;

/**
 * 可见性问题
 *
 * @author yangxin
 * 2020/07/08 17:10
 */
public class VisibilityDemo implements Runnable {

    private static boolean isRunning = true;

    @SuppressWarnings({"WhileLoopSpinsOnField", "StatementWithEmptyBody"})
    @Override
    public void run() {
        while (isRunning) {}
        System.out.println("线程被停止了……");
    }

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) throws InterruptedException {
        new Thread(new VisibilityDemo()).start();
        Thread.sleep(100);
        isRunning = false;
        System.out.println("我已经修改了标志变量，isRunning = " + isRunning);
    }
}
