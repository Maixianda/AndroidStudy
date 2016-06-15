package com.maixianda.copy.study.support_library_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/6/15.
 * 说明:
 * 创建人:         maixianda
 * 创建时间:       2016/6/15 9:39
 */
public class AgendaFragment extends Fragment {
    private LinearLayout mRoot;
    private EditText mEditText;
    private TextInputLayout mTextInputLayout;
    private EditText mTextInputEditText;
    private Button mBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = (LinearLayout) inflater.inflate(R.layout.agenda_fragment,container,false);
        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindCtl();
        initView();
    }

    private void initView() {
        mTextInputEditText = mTextInputLayout.getEditText();
        mTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>4)
                {
                    mTextInputLayout.setError("不能输入超过4个字符");
                    mTextInputLayout.setErrorEnabled(true);
                }
                else
                {
                    mTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"确认提交?",2000).setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                }).show();
            }
        });
    }

    private void bindCtl() {
        mEditText = (EditText) mRoot.findViewById(R.id.et);
        mTextInputLayout = (TextInputLayout)mRoot.findViewById(R.id.til);
        mTextInputEditText = (EditText) mRoot.findViewById(R.id.tiet);
        mBtn = (Button) mRoot.findViewById(R.id.btn);
    }
}
