package com.maidou.study.gglibrary.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

import com.maidou.study.gglibrary.bean.Globals;

/**
 * Created by Administrator on 2016/8/13.
 * 系统/app工具
 * 不能实例化
 */
public class Systems {
    private Systems()
    {
        throw new  Error(Globals.ERROR_MSG_UTILS_CONSTRUCTOR);
    }

    /**
     * 获取屏幕高度(像素)
     * @param context
     * @return 屏幕高度像素
     */
    public static int getScreenHeight(Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
    /**
     * 获取屏幕宽度(像素)
     * @param context
     * @return 屏幕宽度像素
     */
    public static int getScreenWidth(Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
}
