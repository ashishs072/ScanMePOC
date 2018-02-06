package com.analyticdata.scanmepoc.activity;

import android.app.Activity;
import android.content.Intent;
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

public class PGStoryDetailsActivity extends Activity implements View.OnClickListener {

    private TextView mTitle;
    private TextView mDate;
    private TextView mDescription;
    private ImageView mStoryImage,shareBtn;
    private RelativeLayout mRelative;
    private TextView mUserName;
    private Button mComment;
    private Button mCencel;
    private EditText mCommentContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_story_details);
        mRelative= (RelativeLayout) findViewById(R.id.customToolbar);
        mUserName= (TextView) mRelative.findViewById(R.id.userNamedetail);
        mUserName.setText("Capital Clean");
        mTitle= (TextView) findViewById(R.id.heading);
        mDate= (TextView) findViewById(R.id.createDate);
        mDescription= (TextView) findViewById(R.id.descriptionDetail);
        mStoryImage= (ImageView) findViewById(R.id.cameraCapture);
        mComment= (Button) findViewById(R.id.comment);
        mCencel= (Button) findViewById(R.id.cencel);
        mCommentContent= (EditText) findViewById(R.id.userComment);
        mComment.setTransformationMethod(null);
        mCencel.setTransformationMethod(null);
        mComment.setOnClickListener(this);
        mCencel.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        shareBtn= (ImageView) findViewById(R.id.share);
        shareBtn.setOnClickListener(this);
        mTitle.setText(bundle.getString("PGStoryTitle"));
        mDate.setText(bundle.getString("PGStoryDate"));
        mDescription.setText(bundle.getString("PGStoryDes"));
        String ImagePath=bundle.getString("PGStorImageLink");
        Picasso.with(PGStoryDetailsActivity.this).load("http://disposeandrecycleservice.azurewebsites.net"+ImagePath).resize(1000,600).into(mStoryImage);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
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

    }
}
