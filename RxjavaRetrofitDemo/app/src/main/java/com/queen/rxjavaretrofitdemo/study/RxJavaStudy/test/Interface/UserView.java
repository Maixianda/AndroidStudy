package com.queen.rxjavaretrofitdemo.study.RxJavaStudy.test.Interface;

import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.test.model.User;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface UserView {
    void updateView(User user);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);
}
