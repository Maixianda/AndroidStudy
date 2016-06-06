package com.queen.rxjavaretrofitdemo.study.RxJavaStudy.copy.model;


import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.copy.Interface.UserView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/6/1.
 */
public class UserPresenter {
    private UserView mUserView;
    private UserModel mUserModel;

    public UserPresenter(UserView mUserView) {
        this.mUserView = mUserView;
        mUserModel = new UserModel();
    }


    public void getUser()
    {
       mUserView.showProgressDialog();

        mUserModel.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
                mUserView.hideprogressDialog();
            }

            @Override
            public void onError(Throwable e) {
                mUserView.showError(e.getMessage());
                mUserView.hideprogressDialog();
            }

            @Override
            public void onNext(User user) {
                mUserView.updateView(user);
            }
        });
    }
}
