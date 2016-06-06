package com.queen.rxjavaretrofitdemo.study.retrofit.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.queen.rxjavaretrofitdemo.R;
import com.queen.rxjavaretrofitdemo.study.retrofit.test.ApiInterface.gitApi;
import com.queen.rxjavaretrofitdemo.study.retrofit.test.model.gitModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/6/2.
 */
public class activity_retrofit extends Activity {
    private static final String TAG = "activity_retrofit";
    private Button mBtn;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mBtn = (Button) findViewById(R.id.btn_update);
        mTextView = (TextView) findViewById(R.id.tv_show);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit= new Retrofit.Builder()
                        .baseUrl("https://api.github.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                gitApi service = retrofit.create(gitApi.class);
                Call<gitModel> model = service.getFeed("Guolei1130");
                model.enqueue(new Callback<gitModel>() {
                    @Override
                    public void onResponse(Call<gitModel> call, Response<gitModel> response) {
                        Log.d(TAG, "onResponse: "+response.body().getLogin() );
                        mTextView.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<gitModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        mTextView.setText(t.getMessage());
                    }
                });
            }
        });
    }
}
