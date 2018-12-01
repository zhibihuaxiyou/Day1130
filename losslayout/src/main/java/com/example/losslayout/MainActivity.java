package com.example.losslayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView mSearch;
    private LossLayoutView mTvLossviewHistory;
    private LossLayoutView mTvLossviewHot;
    private ImageView mDeleteHot;
    private ImageView mDeleteHis;
    private EditText mEditView;
    private DBDao dbDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dbDao = new DBDao(this);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEditView.getText().toString();
                TextView tv = new TextView(MainActivity.this);
                tv.setText(str);
                tv.setBackgroundResource(R.drawable.edit_bg);
                dbDao.add(str);
                mTvLossviewHistory.addView(tv);
                initText();
            }
        });
        initData();
        mDeleteHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dbDao.delete();
               mTvLossviewHistory.removeAllViews();
            }
        });
        mDeleteHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvLossviewHot.removeAllViews();
            }
        });
    }

    private void initText() {
        List<String> dbList = dbDao.query();
        TextView tv = new TextView(MainActivity.this);
        tv.setText(dbList.get(dbList.size()-1));
        tv.setBackgroundResource(R.drawable.edit_bg);
        mTvLossviewHot.addView(tv);
    }

    private void initData() {
        List<String> dbList = dbDao.query();
        for (int i = 0; i < dbList.size(); i++) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(dbList.get(i));
            tv.setBackgroundResource(R.drawable.edit_bg);
            mTvLossviewHot.addView(tv);
        }
    }
    private void initView() {
        mEditView = findViewById(R.id.editview);
        mSearch = findViewById(R.id.search);
        mTvLossviewHistory = findViewById(R.id.tv_lossview_history);
        mTvLossviewHot = findViewById(R.id.tv_lossview_hot);
        mDeleteHot = findViewById(R.id.delete_hot);
        mDeleteHis = findViewById(R.id.delete_his);
    }
}
