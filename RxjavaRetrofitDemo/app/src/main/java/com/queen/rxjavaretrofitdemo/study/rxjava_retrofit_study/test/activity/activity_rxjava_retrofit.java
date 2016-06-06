package com.queen.rxjavaretrofitdemo.study.rxjava_retrofit_study.test.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.queen.rxjavaretrofitdemo.R;
import com.queen.rxjavaretrofitdemo.study.rxjava_retrofit_study.test.APIService.ApiService;
import com.queen.rxjavaretrofitdemo.study.rxjava_retrofit_study.test.response.GetIpInfoResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/2.
 */
public class activity_rxjava_retrofit extends Activity {
    private Button mBtn;
    private TextView mTextView;
    private ProgressDialog mProgressDialog;

    private static final String ENDPOINT = "http://ip.taobao.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mBtn = (Button) findViewById(R.id.btn_update);
        mTextView = (TextView) findViewById(R.id.tv_show);
        mProgressDialog = new ProgressDialog(this);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                ApiService apiService = retrofit.create(ApiService.class);

                apiService.getIpInfo("63.223.108.42")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<GetIpInfoResponse>() {
                            @Override
                            public void onCompleted() {
                                mProgressDialog.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                mProgressDialog.dismiss();
                                Toast.makeText(activity_rxjava_retrofit.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(GetIpInfoResponse getIpInfoResponse) {
                                mTextView.setText(getIpInfoResponse.data.country);
                            }
                        });
            }
        });
    }
}
