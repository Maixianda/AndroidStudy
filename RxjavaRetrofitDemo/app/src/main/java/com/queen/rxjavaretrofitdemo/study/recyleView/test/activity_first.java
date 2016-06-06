package com.queen.rxjavaretrofitdemo.study.recyleView.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.queen.rxjavaretrofitdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 */
public class activity_first extends Activity {
    private RecyclerView mRecyclerView;
    private List<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_first);

        iniData();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter adapter = new HomeAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
    }

    private void iniData() {
        mData = new ArrayList<String>();
        for (int i = '0';i<='z';i++)
            mData.add((char)i+"");
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHoder>
    {
        @Override
        public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHoder viewHoder = new MyViewHoder(LayoutInflater.from(activity_first.this).inflate(R.layout.item_home,parent,false));
            return viewHoder;
        }

        @Override
        public void onBindViewHolder(MyViewHoder holder, int position) {
            holder.textView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MyViewHoder extends RecyclerView.ViewHolder {
            TextView textView;
            public MyViewHoder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.id_num);
            }
        }
    }
}
