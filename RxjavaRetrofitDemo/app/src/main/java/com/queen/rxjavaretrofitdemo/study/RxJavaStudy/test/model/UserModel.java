package com.queen.rxjavaretrofitdemo.study.RxJavaStudy.test.model;

import android.os.SystemClock;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/5/30.
 */
public class UserModel {
    // TODO: 2016/6/1 9:59 UserModel生成User订阅者(里面进行网络连接访问) ,并让观察者订阅
    public Observable<User> getUser()
    {
            return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                // TODO: 2016/6/1 10:19 这里获取数据并调用订阅者的接口
                SystemClock.sleep(2000);

                final User user = new User("111");
                if (user==null)
                {
                    subscriber.onError(new Exception("User == null"));
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
