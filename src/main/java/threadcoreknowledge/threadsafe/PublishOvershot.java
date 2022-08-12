package threadcoreknowledge.threadsafe;

import java.util.HashMap;
import java.util.Map;

/**
 * 发布逸出
 *
 * @author yangxin
 * 2019/10/03
 */
@SuppressWarnings({"AlibabaRemoveCommentedCode", "CommentedOutCode", "unused"})
public class PublishOvershot {
    private final Map<String, String> STATES;

    private PublishOvershot() {
        STATES = new HashMap<>();
        STATES.put("1", "周一");
        STATES.put("2", "周二");
        STATES.put("3", "周三");
        STATES.put("4", "周四");
    }

    private Map<String, String> getStates() {
        return STATES;
    }

    private Map<String, String> getStatesImproved() {
        return new HashMap<>(STATES);
    }

    public static void main(String[] args) {
        PublishOvershot publishOvershot = new PublishOvershot();
        Map<String, String> states = publishOvershot.getStates();
//        System.out.println(states.get("1"));
//        states.remove("1");
//        System.out.println(states.get("1"));
        System.out.println(publishOvershot.getStatesImproved().get("1"));
        publishOvershot.getStatesImproved().remove("1");
        System.out.println(publishOvershot.getStatesImproved().get("1"));

    }
}
