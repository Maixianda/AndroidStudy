package com.queen.rxjavaretrofitdemo.study.RxJavaStudy.copy.Interface;

import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.copy.model.User;

/**
 * Created by Administrator on 2016/6/1.
 */
public interface UserView {
    public void showProgressDialog();
    public void hideprogressDialog();
    public void showError(String strMsg);
    public void updateView(User user);
}
