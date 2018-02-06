package com.analyticdata.scanmepoc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;

/**
 * Created by M1030099 on 11/24/2017.
 */

public class PointHistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_history);
        String Name=getIntent().getStringExtra("UserName");
        TextView userName= (TextView) findViewById(R.id.leaderNameinList);
        userName.setText(Name);
    }
}
