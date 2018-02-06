package com.analyticdata.scanmepoc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.adapter.LeaderBoardAdapter;
import com.analyticdata.scanmepoc.networking.pojo.UserDetails;
import com.analyticdata.scanmepoc.networking.pojo.UserLeaderBoardDetail;
import com.analyticdata.scanmepoc.networking.response.UserLeaderBoardResponse;

import java.util.ArrayList;

/**
 * Created by M1030099 on 11/21/2017.
 */

public class LeaderBoardActivity extends Activity implements View.OnClickListener {
    private TextView mUserName;
    private TextView muserRank;
    private TextView mUserloyality;
    private TextView mUserCountry;
    private UserDetails mUserDetail;
    private UserLeaderBoardResponse response;
    private LeaderBoardAdapter mLeaderAdapter;
    private RecyclerView mLeaderList;
    private RelativeLayout mRelative ;
    private TextView userPoint;
    private ArrayList<UserLeaderBoardDetail> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        mUserDetail= (UserDetails) getIntent().getSerializableExtra("UserDetail");
        response= (UserLeaderBoardResponse) getIntent().getSerializableExtra("LeaderBoardDetail");
        mRelative= (RelativeLayout) findViewById(R.id.customToolbarLeader);
        userPoint= (TextView) mRelative.findViewById(R.id.userNamedetail);
        mUserName= (TextView) findViewById(R.id.leaderNameinList);
        mLeaderList= (RecyclerView) findViewById(R.id.leaderList);
        mUserCountry= (TextView) findViewById(R.id.leaderCountry);
        mUserloyality= (TextView) findViewById(R.id.loyalityNumber);
        mUserName.setText(mUserDetail.getFirstName());
        mUserCountry.setText(mUserDetail.getLocality());
        mList=response.getmLeaderResponse().getmLeaderList().getmLeaderBoardList();
        mUserloyality.setText(mUserDetail.getmLoyalityPoint());
        userPoint.setText(mUserDetail.getmLoyalityPoint());
        userPoint.setOnClickListener(this);
        mLeaderList.setFocusable(false);
        mLeaderAdapter=new LeaderBoardAdapter(LeaderBoardActivity.this,mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLeaderList.setLayoutManager(mLayoutManager);
        mLeaderList.setAdapter(mLeaderAdapter);



    }

    @Override
    public void onClick(View v) {
        Intent newIntent=new Intent(LeaderBoardActivity.this,PointHistoryActivity.class);
        newIntent.putExtra("UserName",mUserDetail.getFirstName());
        startActivity(newIntent);
    }
}
