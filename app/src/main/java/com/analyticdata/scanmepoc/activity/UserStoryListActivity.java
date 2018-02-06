package com.analyticdata.scanmepoc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.adapter.UserStoryListAdapter;
import com.analyticdata.scanmepoc.networking.pojo.StoryDetails;
import com.analyticdata.scanmepoc.adapter.UserStoryListAdapter.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by M1030099 on 11/21/2017.
 */

public class UserStoryListActivity extends Activity {
    private RecyclerView mRecycleList;
    private UserStoryListAdapter mUserAdapter;
    private ArrayList<StoryDetails> mList;
    private RelativeLayout mRelative;
    private TextView mUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        mRelative= (RelativeLayout) findViewById(R.id.customToolbar);
        mUserName= (TextView) mRelative.findViewById(R.id.userNamedetail);
        mUserName.setText("My Stories");
        mRecycleList= (RecyclerView) findViewById(R.id.recycleList);
        mList= (ArrayList<StoryDetails>) getIntent().getSerializableExtra("StoryData");
        mUserAdapter=new UserStoryListAdapter(UserStoryListActivity.this,mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecycleList.setLayoutManager(mLayoutManager);
        mRecycleList.setAdapter(mUserAdapter);
        mUserAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ArrayList<StoryDetails> UserStoryDataSet) {
                Intent intent=new Intent(UserStoryListActivity.this,UserStoryDetailActivity.class);
                intent.putExtra("Title",UserStoryDataSet.get(position).getTitle());
                intent.putExtra("Description",UserStoryDataSet.get(position).getDescription());
                intent.putExtra("Date",UserStoryDataSet.get(position).getCreatedDate());
                intent.putExtra("ImagePath",UserStoryDataSet.get(position).getImageLink());
                intent.putExtra("UserName",UserStoryDataSet.get(position).getUserID());
                startActivity(intent);

            }
        });




    }
}
