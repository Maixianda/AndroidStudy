package com.example.mxdlogger;

/**
 * Created by Administrator on 2016/8/29.
 * 说明:           日志适配器
 * 创建人:         maixianda
 * 创建时间:       2016/8/29 9:36
 */
public interface LogAdapter {
    void d(String tag,String message);
    void e(String tag,String message);
    void w(String tag,String message);
    void i(String tag,String message);
    void v(String tag,String message);
    void wtf(String tag,String message);
}
