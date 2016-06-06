package com.queen.rxjavaretrofitdemo.study.eventBus.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.queen.rxjavaretrofitdemo.R;
import com.queen.rxjavaretrofitdemo.study.eventBus.test.event.firstEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/6/6.
 */
public class secondActivity extends Activity {
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_second);
        mBtn = (Button) findViewById(R.id.btn_first_event);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new firstEvent("第一个event被触发"));
            }
        });
    }
}
