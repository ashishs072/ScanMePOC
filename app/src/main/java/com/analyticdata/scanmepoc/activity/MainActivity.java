package com.analyticdata.scanmepoc.activity;

import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.networking.RequestHandler;
import com.analyticdata.scanmepoc.networking.request.ImageDetailRequest;
import com.analyticdata.scanmepoc.networking.request.ImageDetailsObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {
    private Button mCaptureImage,mbtnUpload;
    private ImageView mSetImage;
    private String TAG="MainActivity";
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;

    Uri imageUri;
    private Bitmap photo;
    private GoogleApiClient mGoogleclient;
    private double currentLatitude;
    private double currentLongitude;
    private LocationRequest mLocation;
    private Location location;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 1880;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkCameraPermission();
        buildGoogleclientConnection();

        mCaptureImage= (Button) findViewById(R.id.btnCapture);
        mSetImage= (ImageView) findViewById(R.id.setImage);
        mbtnUpload= (Button) findViewById(R.id.btnupload);
        mCaptureImage.setOnClickListener(this);
        mbtnUpload.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnCapture:
                captureImage();
                break;
            case R.id.btnupload:
                uploadImage(photo,MainActivity.this);
        }

    }

    private synchronized void buildGoogleclientConnection() {
        if (mGoogleclient == null) {
            mGoogleclient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
    }


    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
        Log.d(TAG,"Latitude===Camera "+currentLatitude);
        Log.d(TAG,"Longnitude===Camera "+currentLongitude);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

               // imageUri=data.getData();

                  photo = (Bitmap) data.getExtras().get("data");
                    //bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                mSetImage.setImageBitmap(photo);
            }
            }
    }
    private void uploadImage(Bitmap imagebitmap,Context context) {
        Log.d(TAG,"Latitude===Upload "+currentLatitude);
        Log.d(TAG,"Longnitude===Upload "+currentLongitude);
        ImageDetailsObject data=new ImageDetailsObject();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        data.setId("1");
        data.setmImagePath(imageString);
        data.setLongnitudePoint(String.valueOf(currentLongitude));
        data.setLatitiudePoint(String.valueOf(currentLatitude));
        RequestHandler.getInstance(MainActivity.this).handleRequest(new ImageDetailRequest(new SuccessListener(),
                new ErrorListener(), data) );

    }
    private class SuccessListener implements Response.Listener {

        @Override
        public void onResponse(Object response) {
            Log.d(TAG,"Response is coming Successfully");

        }
    }
    private class ErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG,"ResponseError is coming Successfully");
        }
    }

    public static String getJsonString(Object object) {
        ObjectWriter ow = new ObjectMapper().writer()
                .withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         /*   if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
*/
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleclient);
        if(location==null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleclient, mLocation, this);
        }else {
            // latlan = new LatLng(location.getLatitude(), location.getLongitude());
            currentLatitude=location.getLatitude();
            currentLongitude=location.getLongitude();
           /* Log.d(TAG,"Latitude=== "+currentLatitude);
            Log.d(TAG,"Longnitude=== "+currentLongitude);*/
            //mLongnitude.setText(String.valueOf(currentLongitude));
            //mLatitude.setText(String.valueOf(currentLatitude));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        currentLatitude=location.getLatitude();
        currentLongitude=location.getLongitude();
        Log.d(TAG,"Latitude=== "+currentLatitude);
        Log.d(TAG,"Longnitude=== "+currentLongitude);
    }
    protected void onStart() {
        mGoogleclient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleclient.disconnect();
        super.onStop();
    }
    public boolean checkCameraPermission() {
        int camera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        int loc = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
        }
        if (loc != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
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
    }




