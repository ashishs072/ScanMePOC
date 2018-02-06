package com.analyticdata.scanmepoc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.squareup.picasso.Picasso;

/**
 * Created by M1030099 on 11/20/2017.
 */

public class CelebrityStoryDetailActivity extends Activity implements View.OnClickListener {
    private TextView mTitle;
    private TextView mDate;
    private TextView mDescription;
    private ImageView mCelebStoryImage;
    private RelativeLayout mRelative;
    private TextView mUserName;
    private Button mComment;
    private Button mCencel;
    private EditText mCommentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celeb_detail);
        mTitle= (TextView) findViewById(R.id.heading);
        mRelative= (RelativeLayout) findViewById(R.id.customToolbar);
        mUserName= (TextView) mRelative.findViewById(R.id.userNamedetail);
        mUserName.setText("VolunteerStory");
        mDate= (TextView) findViewById(R.id.createDate);
        mDescription= (TextView) findViewById(R.id.descriptionDetail);
        mCelebStoryImage= (ImageView) findViewById(R.id.cameraCapture);
        mComment= (Button) findViewById(R.id.comment);
        mCencel= (Button) findViewById(R.id.cencel);
        mCommentContent= (EditText) findViewById(R.id.userComment);
        mComment.setTransformationMethod(null);
        mCencel.setTransformationMethod(null);
        mComment.setOnClickListener(this);
        mCencel.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        mTitle.setText(bundle.getString("CelebStoryTitle"));
        mDate.setText(bundle.getString("CelebStoryDate"));
        mDescription.setText(bundle.getString("CelebStoryDes"));
        String ImagePath=bundle.getString("CelebStorImageLink");
        Picasso.with(CelebrityStoryDetailActivity.this).load("http://disposeandrecycleservice.azurewebsites.net"+ImagePath).resize(1000,500).into(mCelebStoryImage);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.comment:
                String content=mCommentContent.getText().toString();
                break;
            case R.id.cencel:
                mCommentContent.setText("");
                break;

        }
    }
}
