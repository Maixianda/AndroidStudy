package com.queen.rxjavaretrofitdemo.study.retrofit.test.ApiInterface;

import com.queen.rxjavaretrofitdemo.study.retrofit.test.model.gitModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/6/2.
 */
public interface gitApi {
    @GET("/users/{user}")
    Call<gitModel>  getFeed(@Path("user") String user);
}
