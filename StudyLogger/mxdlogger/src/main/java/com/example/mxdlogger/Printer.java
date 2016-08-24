package com.example.mxdlogger;

import java.util.function.ToDoubleBiFunction;

/**
 * Created by Administrator on 2016/8/24.
 * 说明:           logger的日志接口
 * 创建人:         maixianda
 * 创建时间:       2016/8/24 15:06
 */
public interface Printer {
    // TODO: 2016/8/24 15:35 先完善d()函数
    void d(String message,Object... args);
    void d(Object object);
}
