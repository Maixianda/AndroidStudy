package com.example.mxdlogger;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/8/26.
 * 说明:
 * 创建人:         maixianda
 * 创建时间:       2016/8/26 10:36
 */
public class Helper {
    /**
     * 从"android.util.Log.getStackTraceString()" 复制过来的,为了避免在单元测试中使用android的栈
     *
     * @param tr
     * @return 栈回溯的字符串
     */
    static String getStackTraceString(Throwable tr) {
        if (null == tr) {
            return "";
        }

        Throwable t = tr;
        while (null!=t)
        {
            if (t instanceof UnknownHostException)
            {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    /**
     *  判断字符串是否为空或者0长度
     * @param message 要判断的字符串
     * @return 如果字符串是null或者没有长度,返回true,否则返回false
     */
    public static boolean isEmpty(String message) {
        return message !=null && message.length()==0;
    }

    public static boolean equals(String tag, String tag1) {
        return tag.equals(tag1);
    }
}
