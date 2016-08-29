package com.example.mxdlogger;

import android.util.Log;

/**
 * Created by Administrator on 2016/8/29.
 * 说明:           日志适配器LogAdapter的具体实现
 * 创建人:         maixianda
 * 创建时间:       2016/8/29 9:47
 */
public class AndroidLogAdapter implements LogAdapter {
    @Override
    public void d(String tag, String message) {
        Log.d(tag,message);
    }

    @Override
    public void e(String tag, String message) {
        Log.e(tag,message);
    }

    @Override
    public void w(String tag, String message) {
        Log.w(tag,message);
    }

    @Override
    public void i(String tag, String message) {
        Log.i(tag,message);
    }

    @Override
    public void v(String tag, String message) {
        Log.v(tag,message);
    }

    @Override
    public void wtf(String tag, String message) {
        Log.wtf(tag,message);
    }
}
