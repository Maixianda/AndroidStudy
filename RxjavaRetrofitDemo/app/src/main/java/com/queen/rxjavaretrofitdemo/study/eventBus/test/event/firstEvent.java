package com.queen.rxjavaretrofitdemo.study.eventBus.test.event;

import android.app.Activity;

/**
 * Created by Administrator on 2016/6/6.
 */
public class firstEvent {
    private String msg;

    public firstEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }
}
