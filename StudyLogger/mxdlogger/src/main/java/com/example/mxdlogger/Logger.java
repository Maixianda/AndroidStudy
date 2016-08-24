package com.example.mxdlogger;

/**
 * Created by Administrator on 2016/8/24.
 * 说明:           日志类,与android的log一样使用
 * 创建人:         maixianda
 * 创建时间:       2016/8/24 14:59
 */
public class Logger {

    private static Printer printer = new LoggerPrinter();

    public static void d(Object object)
    {
        printer.d(object);
    }
}
