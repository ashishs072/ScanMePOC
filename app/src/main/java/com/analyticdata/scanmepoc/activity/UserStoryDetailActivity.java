package com.analyticdata.scanmepoc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.squareup.picasso.Picasso;

/**
 * Created by M1030099 on 11/21/2017.
 */

public class UserStoryDetailActivity extends Activity implements View.OnClickListener {
    private TextView mTitle;
    private TextView mDate;
    private TextView mDescription;
    private ImageView mUserStoryImage;
    private RelativeLayout mRelative;
    private TextView mUserName;
    private String UserID;
    private ImageView addBtn,cameraBtn,starBtn,shareBtn;
    private Button mComment;
    private Button mCencel;
    private EditText mCommentContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_story_detail);
        mTitle= (TextView) findViewById(R.id.heading);
        mRelative= (RelativeLayout) findViewById(R.id.customToolbar);
        mUserName= (TextView) mRelative.findViewById(R.id.userNamedetail);
        addBtn= (ImageView) mRelative.findViewById(R.id.btnadd);
        addBtn.setVisibility(View.VISIBLE);
        addBtn.setOnClickListener(this);
        shareBtn= (ImageView) findViewById(R.id.share);
        shareBtn.setOnClickListener(this);
        mComment= (Button) findViewById(R.id.comment);
        mCencel= (Button) findViewById(R.id.cencel);
        mCommentContent= (EditText) findViewById(R.id.userComment);
        mComment.setTransformationMethod(null);
        mCencel.setTransformationMethod(null);
        mComment.setOnClickListener(this);
        mCencel.setOnClickListener(this);
        cameraBtn= (ImageView) mRelative.findViewById(R.id.btnCamera);
        cameraBtn.setVisibility(View.INVISIBLE);
        starBtn= (ImageView) mRelative.findViewById(R.id.btnLeaderboard);
        starBtn.setVisibility(View.INVISIBLE);
        mUserName.setText("User Story");
        //mDate= (TextView) findViewById(R.id.createDate);
        mDescription= (TextView) findViewById(R.id.descriptionDetail);
        mUserStoryImage= (ImageView) findViewById(R.id.cameraCapture);
        Bundle bundle=getIntent().getExtras();
        mTitle.setText(bundle.getString("Title"));
        UserID=bundle.getString("UserName");
        //mDate.setText(bundle.getString("Date"));
        mDescription.setText(bundle.getString("Description"));
        String ImagePath=bundle.getString("ImagePath");

        Picasso.with(UserStoryDetailActivity.this).load("http://disposeandrecycleservice.azurewebsites.net"+ImagePath).resize(1000,500).into(mUserStoryImage);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.btnadd:
                Intent userIntent=new Intent(UserStoryDetailActivity.this,AddUserStoryActivity.class);
                userIntent.putExtra("UserName",UserID);
                startActivity(userIntent);
                break;
            case R.id.share:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                //# for image use "image/*"
                intent.setType("text/plain");
                //intent.putExtra(Intent.EXTRA_TEXT, URL_TO_SHARE);
                startActivity(Intent.createChooser(intent, "Share"));
                break;

            case R.id.comment:
                String content=mCommentContent.getText().toString();
                break;
            case R.id.cencel:
                mCommentContent.setText("");
                break;

        }
        /*Intent userIntent=new Intent(UserStoryDetailActivity.this,AddUserStoryActivity.class);
        userIntent.putExtra("UserName",UserID);
        startActivity(userIntent);*/
    }
}
