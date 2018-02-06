package com.analyticdata.scanmepoc.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.networking.response.CameraResultDetailResponse;
import com.analyticdata.scanmepoc.networking.response.CameraResultResponse;

/**
 * Created by M1030099 on 11/20/2017.
 */

public class CameraActivityDetails extends Activity {
private ImageView captureImage;
    TextView mTitle,mCategory,mDescription,mSourceLink,mCountryName;
    TextView mAgencyName,mAgencyWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_result);
        mTitle= (TextView) findViewById(R.id.heading);
        mCategory= (TextView) findViewById(R.id.aboutHeading);
        mDescription= (TextView) findViewById(R.id.captureDescription);
        mSourceLink= (TextView) findViewById(R.id.sourcelink);
        captureImage= (ImageView) findViewById(R.id.cameraCapture);
        mCountryName= (TextView) findViewById(R.id.countryName);
        mAgencyName= (TextView) findViewById(R.id.nameAgency);
        mAgencyWebsite= (TextView) findViewById(R.id.AgencyWebsite);


        byte[] byteArray = getIntent().getByteArrayExtra("image");
        CameraResultDetailResponse result= (CameraResultDetailResponse) getIntent().getSerializableExtra("CameraResponseDetial");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        captureImage.setImageBitmap(bmp);
        captureImage.setMinimumHeight(500);
        captureImage.setMinimumWidth(1000);
        mCategory.setText(result.getmCameraDetail().getmCategory());
        mDescription.setText(result.getmCameraDetail().getMmethodId().getMethodDetail().get(0).toString());
        mSourceLink.setText(result.getmCameraDetail().getmSource());
        mCountryName.setText(getIntent().getStringExtra("UserCountryName"));
        if(result.getmCameraDetail().getmCategory().equals("others"))
        {
            mAgencyName.setText("");
            mAgencyWebsite.setText("");
        }else
        {
            mAgencyName.setText(result.getmAgencyDetailList().getmAgencyDetails().get(0).getmName());
            mAgencyWebsite.setText(result.getmAgencyDetailList().getmAgencyDetails().get(0).getmWebsite());
        }



    }
}
