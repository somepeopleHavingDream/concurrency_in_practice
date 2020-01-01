package threadcoreknowledge.threadsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 构造函数中新建线程
 *
 * @author yangxin
 * 2019/10/03 17:12
 */
public class NewThreadInInitFunction {
    private Map<String, String> states;

    private NewThreadInInitFunction() {
        new Thread(() -> {
            states = new HashMap<>();
            states.put("1", "周一");
            states.put("2", "周二");
            states.put("3", "周三");
            states.put("4", "周四");
        }).start();
    }

    private Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        NewThreadInInitFunction newThreadInInitFunction = new NewThreadInInitFunction();
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println(newThreadInInitFunction.getStates().get("1"));
    }
}
