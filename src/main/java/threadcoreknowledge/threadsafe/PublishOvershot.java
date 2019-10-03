package threadcoreknowledge.threadsafe;

import java.util.HashMap;
import java.util.Map;

/**
 * 发布逸出
 *
 * @author yangxin
 * 2019/10/03
 */
public class PublishOvershot {
    private Map<String, String> states;

    public PublishOvershot() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) {
        PublishOvershot publishOvershot = new PublishOvershot();
        Map<String, String> states = publishOvershot.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }
}
