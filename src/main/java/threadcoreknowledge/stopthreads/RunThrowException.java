package threadcoreknowledge.stopthreads;

/**
 * run无法抛出checked Exception，只能用try/catch
 *
 * @author yangxin
 * 2019/09/18 14:10
 */
public class RunThrowException {
    public void aVoid() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
