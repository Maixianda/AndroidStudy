package com.example.mxdlogger;

import android.util.LogPrinter;

import java.lang.reflect.Array;
import java.util.Arrays;

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

    //region 画图
    /**
     * Drawing toolbox
     */
    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;
    private static final String DEFAULT_TAG = "mxd";
    private static final int MIN_STACK_OFFSET = 3;
    private static final int CHUNK_SIZE = 4000;
    //endregion 画图
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
     * 为每个线程本地化MethodCount
     */
    private final ThreadLocal<Integer> localMethodCount = new ThreadLocal<>();
    /**
     * tag is used for the Log, the name is a little different
     * in order to differentiate the logs easily with the filter
     * 用于日志的tag,这个变量名有点不同是为了与筛选器结合后更容易区分日志
     */
    private String tag;

    //endregion 成员变量

    public LoggerPrinter() {
        init(DEFAULT_TAG);
    }

    @Override
    public void d(String message, Object... args) {
        log(DEBUG, null, message, args);
    }

    @Override
    public void d(Object object) {
        String message;
        if (object.getClass().isArray()) {
            message = Arrays.deepToString((Object[]) object);
        } else {
            message = object.toString();
        }
        log(DEBUG, null, message);
    }

    @Override
    public Settings init(String tag) {
        if (null == tag) {
            throw new NullPointerException("tag 为null");
        }
        if (tag.trim().length() == 0) {
            throw new IllegalStateException("tag为空");
        }
        this.tag = tag;
        return settings;
    }

    /**
     * 这个方法用于避免日志混乱(加锁避免)
     *
     * @param debug     日志级别
     * @param throwable 异常
     * @param message   要打印的日志
     * @param args
     */
    private void log(int debug, Throwable throwable, String message, Object... args) {
        if (settings.getLogLevel() == LogLevel.NONE) {
            return;
        }
        String tag = getTag();
        String msg = createMessage(message, args);
        log(debug, tag, message, throwable);
    }

    private void log(int priority, String tag, String message, Throwable throwable) {
        if (settings.getLogLevel() == LogLevel.NONE) {
            return;
        }
        if (null != throwable && message != null) {
            message += " : " + Helper.getStackTraceString(throwable);
        }
        if (null != throwable && null == message) {
            message = Helper.getStackTraceString(throwable);
        }
        if (null == message) {
            message = "没有消息或者异常被设置";
        }
        int methodCount = getMethodCount();
        if (Helper.isEmpty(message)) {
            message = "字符串为空或者0长度";
        }
        logTopBoreder(priority, tag);
        logHeaderContent(priority, tag, methodCount);

        //使用系统默认的字符集获取message的bytes(Android中默认是UTF-8)
        byte[] bytes = message.getBytes();
        int length = bytes.length;
        if (length<=CHUNK_SIZE)
        {
            if (methodCount > 0)
            {
                logDivider(priority,tag);
            }
        }
    }

    private void logDivider(int priority, String tag) {
        logChunk(priority,tag,MIDDLE_BORDER);
    }

    /**
     * 日志头部内容
     *
     * @param priority    日志级别
     * @param tag         日志tag
     * @param methodCount 方法数量
     */
    @SuppressWarnings("StringBufferReplaceableByString")
    private void logHeaderContent(int priority, String tag, int methodCount) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        if (settings.isShowThreadInfo()) {
            logChunk(priority,
                    tag,
                    HORIZONTAL_DOUBLE_LINE + " Thread: " + Thread.currentThread().getName());
        }
        String level = "";

        int stackOffset = getStackOffset(trace) + settings.getMethodCount();

        //这个栈对应的方法数量或许会超过栈.整理下计数
        //corresponding method count with the current stack may exceeds the stack trace. Trims the count
        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex >= trace.length)
            {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            builder.append(" ‖ ")
                    .append(level)
                    .append(getSimpleClassName(trace[stackIndex].getClassName()))
                    .append(".")
                    .append(trace[stackIndex].getMethodName())
                    .append("")
                    .append(" (")
                    .append(trace[stackIndex].getFileName())
                    .append(":")
                    .append(trace[stackIndex].getLineNumber())
                    .append(") ");
            level += "    ";
            logChunk(priority,tag,builder.toString());
        }
    }

    /**
     * 根据给的完整类名获取去掉后缀的类名(去掉小数点以及小数点后的字符串)
     * @param className 完整类名
     * @return 类名
     */
    private String getSimpleClassName(String className) {
        int lastIndex = className.lastIndexOf(".");
        return className.substring(lastIndex+1);
    }

    /**
     * Determines the starting index of the stack trace, after method calls made by this class.
     * 在类的方法调用后,确定栈回溯的开始位置
     *
     * @param trace 栈回溯对象
     * @return 栈回溯偏移
     */
    private int getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(LoggerPrinter.class.getName()) && !name.equals(Logger.class.getName())) {
                return --i;
            }
        }
        return -1;
    }

    private void logTopBoreder(int priority, String tag) {
        logChunk(priority, tag, TOP_BORDER);
    }

    private void logChunk(int priority, String tag, String chunk) {
        String finalTag = formatTag(tag);
        switch (priority) {
            case ERROR:
                settings.getLogAdapter().e(finalTag, chunk);
                break;
            case DEBUG:
                settings.getLogAdapter().d(finalTag, chunk);
                break;
            case ASSERT:
                settings.getLogAdapter().wtf(finalTag, chunk);
                break;
            case INFO:
                settings.getLogAdapter().i(finalTag, chunk);
                break;
            case VERBOSE:
                settings.getLogAdapter().v(finalTag, chunk);
                break;
            case WARN:
                settings.getLogAdapter().w(finalTag, chunk);
        }
    }

    private String formatTag(String tag) {
        if (!Helper.isEmpty(tag) && !Helper.equals(this.tag, tag)) {
            return this.tag + "-" + tag;
        }
        return this.tag;
    }

    private int getMethodCount() {
        Integer count = localMethodCount.get();
        int result = settings.getMethodCount();
        if (null != count) {
            localMethodCount.remove();
            result = count;
        }
        if (result < 0) {
            throw new IllegalStateException("方法数不能是负数");
        }
        return result;
    }

    /**
     * 创建消息
     *
     * @param message 要格式化的格式模板,跟printf类似
     * @param args    要打印的扩充日志
     * @return 要打印的日志
     */
    private String createMessage(String message, Object[] args) {
        return (args == null || args.length == 0 ? message : String.format(message, args));
    }

    /**
     * 根据本地或者全局设置返回对应的tag
     *
     * @return
     */
    // TODO: 2016/8/24 16:09 不是很懂,需要下断点看看
    private String getTag() {
        String tag = localTag.get();
        if (tag != null) {
            localTag.remove();
            return tag;
        }
        return this.tag;
    }


}
