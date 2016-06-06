package com.queen.rxjavaretrofitdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.queen.rxjavaretrofitdemo.R;
import com.queen.rxjavaretrofitdemo.subscribers.ProgressSubscriber;
import com.queen.rxjavaretrofitdemo.subscribers.SubscriberOnNextListener;
import com.queen.rxjavaretrofitdemo.entity.Subject;
import com.queen.rxjavaretrofitdemo.http.HttpMethods;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;

    /**
     * 下一页监听器,是一个观察者对象
     */
    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //这里设置下一页监听器,其中下一页的类型是一个list<Subject>
        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            /**
             * 显示下一页,主要就是把textview的内容设置为list<Subject>的字符串格式
             * @param subjects
             */
            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {
        getMovie();
    }

    //进行网络请求
    private void getMovie(){
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext, MainActivity.this), 0, 10);
    }
}
