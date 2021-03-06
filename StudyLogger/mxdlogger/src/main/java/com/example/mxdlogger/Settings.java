package com.example.mxdlogger;

import android.util.Log;

/**
 * Created by Administrator on 2016/8/24.
 * 说明:           日志设置类
 * 创建人:         maixianda
 * 创建时间:       2016/8/24 15:38
 */
public class Settings {
    // TODO: 2016/8/24 15:39 先完善 d() 函数

    //region 成员变量
    private int methodCount = 2;
    /**
     * Determines to how logs will be printed
     * 根据日志级别,设置日志打印的样式
     */
    private LogLevel logLevel = LogLevel.FULL;
    private LogAdapter logAdapter;
    private boolean showThreadInfo = true;

    public Settings hideThreadInfo()
    {
        showThreadInfo = false;
        return this;
    }
    public Settings logLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public LogAdapter getLogAdapter() {
        if (null == logAdapter)
        {
            logAdapter = new AndroidLogAdapter();
        }
        return logAdapter;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }
    //endregion 成员变量


}
