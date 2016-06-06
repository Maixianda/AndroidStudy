package com.queen.rxjavaretrofitdemo.subscribers;

/**
 * Created by liukun on 16/3/10.
 */

/**
 * 下一页的监听器
 * @param <T> 下一页的类型
 */
public interface SubscriberOnNextListener<T> {
    /**
     * 显示下一页
     * @param t 下一页的数据
     */
    void onNext(T t);
}
