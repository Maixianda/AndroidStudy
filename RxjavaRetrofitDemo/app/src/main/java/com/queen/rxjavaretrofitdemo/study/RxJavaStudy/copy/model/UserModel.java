package com.queen.rxjavaretrofitdemo.study.RxJavaStudy.copy.model;

import android.os.SystemClock;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/1.
 */
public class UserModel {
    public Observable<User> getUser()
    {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                SystemClock.sleep(2000);
                User user = new User("maidou");
                if (user==null)
                {
                    subscriber.onError(new Exception("user == null"));
                }
                else
                {
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
