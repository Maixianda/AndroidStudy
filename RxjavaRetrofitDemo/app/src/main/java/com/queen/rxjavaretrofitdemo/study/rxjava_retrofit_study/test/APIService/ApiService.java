package com.queen.rxjavaretrofitdemo.study.rxjava_retrofit_study.test.APIService;

import com.queen.rxjavaretrofitdemo.study.rxjava_retrofit_study.test.response.GetIpInfoResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/2.
 */
public interface ApiService {
    @GET("service/getIpInfo.php")
    Observable<GetIpInfoResponse> getIpInfo(@Query("ip")String ip);
}
