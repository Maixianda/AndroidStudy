package com.queen.rxjavaretrofitdemo.study.RxJavaStudy.test.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.queen.rxjavaretrofitdemo.R;
import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.test.Interface.UserView;
import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.test.model.User;
import com.queen.rxjavaretrofitdemo.study.RxJavaStudy.test.model.UserPresenter;

/**
 * Created by Administrator on 2016/5/30.
 */
public class activity_test extends Activity implements UserView {
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private TextView mTvShow;
    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mContext = this;

        mUserPresenter = new UserPresenter(this);

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("正在加载，请稍后..");

        mTvShow = (TextView) findViewById(R.id.tv_show);
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserPresenter.getUser();
            }
        });
    }

    @Override
    public void updateView(final User user) {
        Log.d("111", "updateView: 111"+user.m_strName);
        if (user!=null)
        {
//            mTvShow.post(new Runnable() {
//                @Override
//                public void run() {
//                    mTvShow.setText(user.m_strName);
//                }
//            });
            mTvShow.setText(user.m_strName);
        }
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
