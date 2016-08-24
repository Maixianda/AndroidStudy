package com.example.mxdlogger;

/**
 * Created by Administrator on 2016/8/24.
 * 说明:           logger的具体日志打印类
 * 创建人:         maixianda
 * 创建时间:       2016/8/24 15:09
 */
public class LoggerPrinter implements Printer {

    //region 日志级别
    private static final int DEBUG = 3;
    private static final int ERROR = 6;
    private static final int ASSERT = 7;
    private static final int INFO = 4;
    private static final int VERBOSE = 2;
    private static final int WARN = 5;
    //endregion 日志级别


    //region 成员变量
    /**
     * It is used to determine log settings such as method count, thread info visibility
     * 用于确定日志的设置,例如设置方法数量,线程信息是否可见等
     */
    private final Settings settings = new Settings();

    /**
     * 为每个线程本地化一个tag
     */
    private final ThreadLocal<String> localTag = new ThreadLocal<>();
    /**
     * tag is used for the Log, the name is a little different
     * in order to differentiate the logs easily with the filter
     * 用于日志的tag,这个变量名有点不同是为了与筛选器结合后更容易区分日志
     */
    private String tag;

    //endregion 成员变量
    @Override
    public void d(String message, Object... args) {
        log(DEBUG,null,message,args);
    }

    /**
     * 这个方法用于避免日志混乱(加锁避免)
     * @param debug 日志级别
     * @param throwable 异常
     * @param message 要打印的日志
     * @param args
     */
    private void log(int debug, Throwable throwable, String message, Object... args) {
        if (settings.getLogLevel() == LogLevel.NONE)
        {
            return;
        }
        String tag = getTag();
        String msg = createMessage(message,args);
        log(debug,tag,message,throwable);
    }

    /**
     * 创建消息
     * @param message 要格式化的格式模板,跟printf类似
     * @param args 要打印的扩充日志
     * @return 要打印的日志
     */
    private String createMessage(String message, Object[] args) {
        return (args == null || args.length == 0 ? message:String.format(message,args));
    }

    /**
     * 根据本地或者全局设置返回对应的tag
     * @return
     */
    // TODO: 2016/8/24 16:09 不是很懂,需要下断点看看
    private String getTag() {
        String tag = localTag.get();
        if (tag!=null)
        {
            localTag.remove();
            return tag;
        }
        return this.tag;
    }

    @Override
    public void d(Object object) {
        // TODO: 2016/8/24 15:34 完善上一个 d() 函数
    }
}
