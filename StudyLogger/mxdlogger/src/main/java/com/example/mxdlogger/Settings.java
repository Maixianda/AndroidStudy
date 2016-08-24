package com.example.mxdlogger;

/**
 * Created by Administrator on 2016/8/24.
 * 说明:           日志设置类
 * 创建人:         maixianda
 * 创建时间:       2016/8/24 15:38
 */
public class Settings {
    // TODO: 2016/8/24 15:39 先完善 d() 函数

    //region 成员变量
    /**
     * Determines to how logs will be printed
     * 根据日志级别,设置日志打印的样式
     */
    private LogLevel logLevel = LogLevel.FULL;

    public Settings logLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }
    //endregion 成员变量


}
