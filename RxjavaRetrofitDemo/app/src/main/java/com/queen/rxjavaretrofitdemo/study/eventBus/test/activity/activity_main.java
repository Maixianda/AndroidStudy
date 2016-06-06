package com.queen.rxjavaretrofitdemo.study.eventBus.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.queen.rxjavaretrofitdemo.R;
import com.queen.rxjavaretrofitdemo.study.eventBus.test.event.firstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016/6/6.
 */
public class activity_main extends Activity{
    private Button mBtn;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_main);

        //region 注册eventbus
        EventBus.getDefault().register(this);
        //endregion 注册eventbus

        mBtn = (Button) findViewById(R.id.btn_try);
        mTextView = (TextView) findViewById(R.id.tv);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),secondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainThread(firstEvent firstEvent)
    {
        mTextView.setText(firstEvent.getMsg());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
