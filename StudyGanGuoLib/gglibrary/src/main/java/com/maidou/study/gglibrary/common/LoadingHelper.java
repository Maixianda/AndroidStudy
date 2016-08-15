package com.maidou.study.gglibrary.common;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Administrator on 2016/8/13.
 * 显示 material 类型的 加载对话框
 */
public class LoadingHelper {
    protected static MaterialDialog mAterialDialog = null;

    /**
     * 最简单的显示加载对话框
     * 不可取消加载
     * 圆形旋转
     *
     * @param context 要显示的对话框的上下文,一般为Activity
     * @param msg     要显示的信息
     * @return MaterialDialog的实例
     */
    public static MaterialDialog show(Context context, String msg) {
        return show(context, msg, null);
    }

    /**
     * 显示加载对话框
     *
     * @param context  要显示的对话框的上下文,一般为Activity
     * @param msg      要显示的信息
     * @param listener 取消显示的监听器
     * @return MaterialDialog的实例
     */
    private static MaterialDialog show(Context context, String msg, DialogInterface.OnCancelListener listener) {
        get(context, msg, listener)
                .show();
        return mAterialDialog;
    }

    /**
     * 显示加载对话框
     *
     * @param context  要显示的对话框的上下文,一般为Activity
     * @param msg      要显示的信息
     * @param listener 取消显示的监听器
     * @return MaterialDialog的实例
     */
    public static MaterialDialog get(Context context, String msg, DialogInterface.OnCancelListener listener) {
        if (null != mAterialDialog) {
            hide();
        }
        return mAterialDialog = new MaterialDialog.Builder(context)
                .progress(true, 0)
                .content(msg)
                .cancelable(listener != null)
                .cancelListener(listener).build();
    }

    /**
     * 隐藏加载对话框
     */
    public static void hide() {
        if (null == mAterialDialog) {
            return;
        }
        if (mAterialDialog.isShowing()) {
            mAterialDialog.dismiss();
        }
        mAterialDialog = null;
    }
}
