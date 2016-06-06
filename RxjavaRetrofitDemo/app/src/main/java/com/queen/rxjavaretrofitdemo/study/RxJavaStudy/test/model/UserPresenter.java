package com.queen.rxjavaretrofitdemo.study.RxJavaStudy.test.model;

import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.test.Interface.UserView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/5/30.
 */
public class UserPresenter {
    private UserView mUserView;
    private UserModel mUserModel;

    public UserPresenter(UserView mUserView) {
        this.mUserView = mUserView;
        mUserModel = new UserModel();
    }

    // TODO: 2016/6/1 9:57 UserPresenter显示等待对话框,设置订阅者和观察者的线程以及生成订阅者
    public void getUser()
    {
        mUserView.showProgressDialog();

        // TODO: 2016/6/1 14:35 订阅者处于子线程,观察者处于UI线程
        mUserModel.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {

            @Override
            public void onCompleted() {
                mUserView.hideProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                mUserView.showError(e.getMessage());
                mUserView.hideProgressDialog();
            }

            @Override
            public void onNext(User user) {
                mUserView.updateView(user);
            }
        });
    }
}
