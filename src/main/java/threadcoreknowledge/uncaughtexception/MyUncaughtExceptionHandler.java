package threadcoreknowledge.uncaughtexception;

import lombok.AllArgsConstructor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 我的未处理异常处理器
 *
 * @author yangxin
 * 2019/09/25 15:21
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@AllArgsConstructor
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private String name;

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常，终止啦" + t.getName());
//        logger.log(Level.WARNING, "线程异常，终止啦" + t.getName(), e);
        System.out.println(name + "捕获了异常" + t.getName() + "异常");
//        System.out.println(name + "捕获了异常" + t.getName() + "异常" + e);
    }
}
