package threadcoreknowledge.visibility;

/**
 * 可见性问题、排序问题（未考虑）
 *
 * volatile关键字的作用
 * 1：volatile变量在读和写时会添加lock指令保证缓存同步。
 * 2：volatile读会从内存中去同步所有的共享变量，并且保证不会有任何指令越过volatile读重排序到它前面
 * ，即volatile读后面的操作，都是使用的内存中最新的值；
 * volatile写保证在写入时会将工作空间中的所有变量都同步到主内存中，而且所有指令都无法越过volatile写重排序到它后面，
 * 因此它保证了，volatile写之前的所有操作最终都会同步到主内存中。
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
