package com.analyticdata.scanmepoc.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.networking.RequestHandler;
import com.analyticdata.scanmepoc.networking.pojo.AddUserStoryDetail;
import com.analyticdata.scanmepoc.networking.request.AddUserStoryDetailrequest;
import com.analyticdata.scanmepoc.networking.response.CameraResultResponse;
import com.analyticdata.scanmepoc.util.UIUtils;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by M1030099 on 11/23/2017.
 */

public class AddUserStoryActivity extends Activity implements View.OnClickListener {

    private ImageView galleryImage;
    private EditText mTitleName;
    private EditText mCreditStory;
    private EditText mStoryContent;
    private Button mPublish;
    private byte[] imageBytes;
    private String UserName;
    private Uri selectedImage;
    private ProgressBar mProgress;
    private  String imageString;
    private AddUserStoryDetail addUserStories;
    private static final int GALLERY_REQUEST=10;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_user_story);
        galleryImage= (ImageView) findViewById(R.id.cameraCapture);
        galleryImage.setOnClickListener(this);
        UserName=getIntent().getStringExtra("UserName");
        mTitleName= (EditText) findViewById(R.id.titleStory);
        mCreditStory= (EditText) findViewById(R.id.creditStory);
        mProgress= (ProgressBar) findViewById(R.id.progress);
        mStoryContent= (EditText) findViewById(R.id.contentStory);
        mPublish= (Button) findViewById(R.id.publish);
        mPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.cameraCapture:
                goToGallery();
                break;
            case R.id.publish:
                sendDataToServer();
        }
        
    }

    private void sendDataToServer() {
        if(mTitleName.getText().toString().equals("")||mCreditStory.getText().toString().equals("")||mStoryContent.getText().toString().equals(""))
        {
            Toast.makeText(this,"Please enter the value in all mandatory field",Toast.LENGTH_SHORT).show();
        }else {
            mProgress.setVisibility(View.VISIBLE);
            //String imagePath=getPath(selectedImage);
            addUserStories = new AddUserStoryDetail();
            addUserStories.setmID("2");
            addUserStories.setmUserName(UserName);
            addUserStories.setmTitle(mTitleName.getText().toString());
            addUserStories.setmDescription(mStoryContent.getText().toString());
            addUserStories.setmImagePath(imageString);
            addUserStories.setmThumNailImagePath(" ");
            addUserStories.setmCreatedDate("23-Nov-2017");
            RequestHandler.getInstance(this).handleRequest(new AddUserStoryDetailrequest(new SuccessListener(), new ErrorListner(), addUserStories));

        /*String userTitle=mTitleName.getText().toString();
        String userDescription=mStoryContent.getText().toString();*/
        }
    }

    private void goToGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST:
                     selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                        galleryImage.setImageBitmap(bitmap);
                        /*Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 400,
                                500, true);*/
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);
                        imageBytes = baos.toByteArray();
                        imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
    private class SuccessListener implements Response.Listener {

        @Override
        public void onResponse(Object response) {
            mProgress.setVisibility(View.GONE);
            //Toast.makeText(AddUserStoryActivity.this,"Congratulation! You have earned 10 points.",Toast.LENGTH_SHORT).show();
            showToastMessage();
            goToHome();

        }
    }
    private class ErrorListner implements Response.ErrorListener {


        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }
    private void goToHome()
    {
        Intent goHome=new Intent(AddUserStoryActivity.this,HomeActivity.class);
        startActivity(goHome);
    }
    private void showToastMessage()
    {
        Toast toast = new Toast(this);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_coupan, null);
       // view.setImageResource(R.drawable.coupan_msg);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}
