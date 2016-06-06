package com.queen.rxjavaretrofitdemo.study.RxJavaStudy.copy.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.queen.rxjavaretrofitdemo.R;
import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.copy.Interface.UserView;
import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.copy.model.User;
import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.copy.model.UserPresenter;

/**
 * Created by Administrator on 2016/6/1.
 */
public class activity_copy extends Activity implements UserView {
    private ProgressDialog mProgressDialog;
    private TextView mTextView;
    private UserPresenter mUserPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mProgressDialog = new ProgressDialog(this);
        mTextView = (TextView) findViewById(R.id.tv_show);
        mUserPresenter = new UserPresenter(this);
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity_copy.this, BuildConfig.isStage, Toast.LENGTH_SHORT).show();
                getUser();
            }
        });
    }

    private void getUser() {
        mUserPresenter.getUser();
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideprogressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String strMsg) {
        Toast.makeText(activity_copy.this, strMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(User user) {
        if (user!=null)
        mTextView.setText(user.mName);
    }
}
