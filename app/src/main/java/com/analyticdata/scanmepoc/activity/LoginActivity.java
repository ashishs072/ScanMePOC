package com.analyticdata.scanmepoc.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.networking.RequestHandler;
import com.analyticdata.scanmepoc.networking.request.LoginDetailObject;
import com.analyticdata.scanmepoc.networking.request.LoginDetailRequest;
import com.analyticdata.scanmepoc.networking.response.LoginDetailResponse;
import com.analyticdata.scanmepoc.util.UIUtils;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by M1030099 on 11/13/2017.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText mailId,mPassword;
    private Button mSubmit;
    private String TAG="LoginActivity";
    private SharedPreferences mShared;
    private SharedPreferences.Editor edit;
    ProgressBar mProgress;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkPermission();
        mShared=getSharedPreferences("UserDetail",MODE_PRIVATE);
        mProgress= (ProgressBar) findViewById(R.id.progress);
        mailId= (EditText) findViewById(R.id.username);
        mPassword= (EditText) findViewById(R.id.password);
        mailId.setText("somid1@gmail.com");
        mPassword.setText("Somanath123");
        mSubmit= (Button) findViewById(R.id.login);
        mSubmit.setTransformationMethod(null);
        mSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(!UIUtils.isNetworkConnected(LoginActivity.this))
        {
            Toast.makeText(this,"Please check your network connection",Toast.LENGTH_SHORT).show();
        } else
        {
            if(mailId.getText().toString().equals("")||(!UIUtils.isValidEmail(mailId.getText().toString())))
            {
                Toast.makeText(this,"Please enter the valid emailId",Toast.LENGTH_SHORT).show();
            }else if(mPassword.getText().toString().equals("")){
                Toast.makeText(this,"Please enter the password",Toast.LENGTH_SHORT).show();
            }
                else{
                mProgress.setVisibility(View.VISIBLE);
                serviceCall();
           }
        }

    }

    private void serviceCall()
    {
        LoginDetailObject data=new LoginDetailObject();
        data.setUserName(mailId.getText().toString());
        data.setmPassword(mPassword.getText().toString());
        RequestHandler.getInstance(LoginActivity.this).handleRequest(new LoginDetailRequest(new SuccessListener(),
                new ErrorListener(), data));
    }
    private class SuccessListener implements Response.Listener {

        @Override
        public void onResponse(Object response) {
            LoginDetailResponse loginDetails=UIUtils.getInstance().getPojoObject(String.valueOf(response),LoginDetailResponse.class);
            Log.d(TAG,"Response is coming Successfully");
            goToHomePage(loginDetails);


        }
    }
    private class ErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG,"ResponseError is coming Successfully");
        }
    }
    public boolean checkPermission() {
        int camera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        int loc = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int externalExcess = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readexternalExcess = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
        }
        if (loc != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (externalExcess != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (readexternalExcess != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(this,listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0
                            && grantResults[i] == PackageManager.PERMISSION_GRANTED) {


                    }

                }
            }
        }


    }
    private void goToHomePage(LoginDetailResponse loginDetails)
    {
        mProgress.setVisibility(View.GONE);
        Intent homePage=new Intent(LoginActivity.this,HomeActivity.class);
        homePage.putExtra("UserName",mailId.getText().toString());
        homePage.putExtra("LoginDetail",loginDetails);
        startActivity(homePage);
    }
}
