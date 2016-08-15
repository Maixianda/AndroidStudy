package com.maidou.study.gglibrary.common;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/13.
 * 显示toast
 */
public class ToastHelper {
    protected static Toast mToast;

    public static void show(final Context context,String msg)
    {
        if (TextUtils.isEmpty(msg))
        {
            msg = "";
        }

        mToast = Toast.makeText(context,msg,Toast.LENGTH_LONG);
        // TODO: 2016/8/13 写下Systems类
        //mToast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0, systems);
        mToast.show();
    }
}
