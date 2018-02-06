package com.analyticdata.scanmepoc.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.adapter.PGInitativeAdapter;
import com.analyticdata.scanmepoc.adapter.PGInitativeAdapter.OnItemClickListener;
import com.analyticdata.scanmepoc.adapter.VolunteerAdapter.OnItemClickPagerListener;
import com.analyticdata.scanmepoc.adapter.StoryAdapter.OnItemClickUserPagerListener;
import com.analyticdata.scanmepoc.adapter.StoryAdapter;
import com.analyticdata.scanmepoc.adapter.VolunteerAdapter;
import com.analyticdata.scanmepoc.networking.RequestHandler;
import com.analyticdata.scanmepoc.networking.pojo.CelebrityStoryDetails;
import com.analyticdata.scanmepoc.networking.pojo.PGStoryDetails;
import com.analyticdata.scanmepoc.networking.pojo.StoryDetails;
import com.analyticdata.scanmepoc.networking.pojo.UserDetails;
import com.analyticdata.scanmepoc.networking.request.ImageDetailRequest;
import com.analyticdata.scanmepoc.networking.request.ImageDetailsObject;
import com.analyticdata.scanmepoc.networking.request.LeaderBoardDetailRequest;
import com.analyticdata.scanmepoc.networking.response.CameraResultDetailResponse;
import com.analyticdata.scanmepoc.networking.response.CameraResultResponse;
import com.analyticdata.scanmepoc.networking.response.LoginDetailResponse;
import com.analyticdata.scanmepoc.networking.response.UserLeaderBoardResponse;
import com.analyticdata.scanmepoc.util.UIUtils;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.support.v7.app.ActionBarDrawerToggle;

/**
 * Created by M1030099 on 11/17/2017.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private String TAG = "HomeActivity";
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private Bitmap photo,resizedBitmap;
    private GoogleApiClient mGoogleclient;
    private double currentLatitude;
    private double currentLongitude;
    private LocationRequest mLocation;
    Bitmap originalBitmap;
    private File image;
    private RelativeLayout mRelative;
    private TextView mUserName;

    private Location location;
    private String imageFileName;
    private DrawerLayout mDrawerlayout;
    private Uri outputFileUri;
    private Uri imageUri;
    private String authority="com.analyticdata.scanmepoc.fileprovider";
    private String UserID;
    private ViewPager mVolunteerPager;
    private ViewPager mStoryPager;
    private RecyclerView mPGListView;
    private ImageView mCameraBtn;
    private ImageView mLeaderBoardBtn;
    private UserDetails mUserDetail;
    private ImageView addUserStory;
    private NavigationView navigationView;
    private URI mImageUri;
    private ScrollView mScroll;
    private Toolbar mToolbar;
    private byte[] imageBytes;
    private PGInitativeAdapter pgAdapter;
    private StoryAdapter storyBoardAdapter;
    private ProgressBar mProgress;
    private View view;
    private TextView headerName;
    private TextView headerCountryName;
    private VolunteerAdapter volunteerBorAadapter;
    ArrayList<StoryDetails> storyList;
    ArrayList<PGStoryDetails> volunteerList;
    private String pictureImagePath = "";
    ArrayList<CelebrityStoryDetails> celebrityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        buildGoogleclientConnection();
        //view= (RelativeLayout) findViewById(R.id.customToolbar);
        Toolbar newToolbar= (Toolbar)findViewById(R.id.toolBar);
        mDrawerlayout=(DrawerLayout) findViewById(R.id.drawerlayout);

        setSupportActionBar(newToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mDrawerlayout,newToolbar,R.string.open_drawer,R.string.open_drawer);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        view= navigationView.inflateHeaderView(R.layout.activity_nav_header);
        headerCountryName= (TextView) view.findViewById(R.id.leaderCountry);
        headerName=(TextView)view.findViewById(R.id.leaderNameinList);
        /* mUserName= (TextView) mRelative.findViewById(R.id.userNamedetail);
        mUserName.setText("How2Dispose");*/
        mVolunteerPager = (ViewPager) findViewById(R.id.volunteerPager);
        mStoryPager = (ViewPager) findViewById(R.id.storyPager);
        addUserStory= (ImageView) findViewById(R.id.addStory);
        mProgress= (ProgressBar) findViewById(R.id.progress);
        addUserStory.setOnClickListener(this);
        mPGListView = (RecyclerView) findViewById(R.id.pgListView);
       // mScroll = (ScrollView) findViewById(R.id.scrollLayout);
        mPGListView.setFocusable(false);
        mPGListView.setNestedScrollingEnabled(false);
        mCameraBtn = (ImageView) findViewById(R.id.btnCamera);
        mCameraBtn.setOnClickListener(this);
        mLeaderBoardBtn= (ImageView) findViewById(R.id.btnLeaderboard);
        mLeaderBoardBtn.setOnClickListener(this);
        Intent loginData = getIntent();
        LoginDetailResponse loginResponse = (LoginDetailResponse) loginData.getSerializableExtra("LoginDetail");
        UserID=loginData.getStringExtra("UserName");
        storyList = loginResponse.getStoryDetail();
        mUserDetail=loginResponse.getmUserDetail();
        headerName.setText(mUserDetail.getFirstName());
        headerCountryName.setText(mUserDetail.getLocality());
        storyBoardAdapter = new StoryAdapter(HomeActivity.this, storyList);
        mStoryPager.setAdapter(storyBoardAdapter);
        celebrityList = loginResponse.getCelebritystoryDetail().getmCelebrityPgDetalList();
        volunteerList = loginResponse.getPgDetails().getmPgDetalList();
        volunteerBorAadapter = new VolunteerAdapter(HomeActivity.this, celebrityList);
        mVolunteerPager.setAdapter(volunteerBorAadapter);
        pgAdapter = new PGInitativeAdapter(HomeActivity.this, volunteerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mPGListView.setLayoutManager(mLayoutManager);
        mPGListView.setAdapter(pgAdapter);
        volunteerBorAadapter.setOnItemClickPagerListener(new OnItemClickPagerListener() {
            @Override
            public void onItemClick(View view, int position, ArrayList<CelebrityStoryDetails> CelebpgStoryDataSet) {

                Intent celebstoryDetail=new Intent(HomeActivity.this,CelebrityStoryDetailActivity.class);
                celebstoryDetail.putExtra("CelebStoryDate",CelebpgStoryDataSet.get(position).getCreatedDate());
                celebstoryDetail.putExtra("CelebStoryDes",CelebpgStoryDataSet.get(position).getDescription());
                celebstoryDetail.putExtra("CelebStorImageLink",CelebpgStoryDataSet.get(position).getImageLink());
                celebstoryDetail.putExtra("CelebStoryTitle",CelebpgStoryDataSet.get(position).getTitle());
                celebstoryDetail.putExtra("CelebStoryCountryName",CelebpgStoryDataSet.get(position).getmName());
                startActivity(celebstoryDetail);
            }
        });
        pgAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ArrayList<PGStoryDetails> pgStoryDataSet) {

                Intent pgDetails =new Intent(HomeActivity.this,PGStoryDetailsActivity.class);
                pgDetails.putExtra("PGStorImageLink",pgStoryDataSet.get(position).getImagelink());
                pgDetails.putExtra("PGStoryDes",pgStoryDataSet.get(position).getmDescription());
                pgDetails.putExtra("PGStoryDate",pgStoryDataSet.get(position).getmCreatedDate());
                pgDetails.putExtra("PGStoryTitle",pgStoryDataSet.get(position).getmTitle());
                startActivity(pgDetails);
            }
        });

        storyBoardAdapter.setOnItemClickUserPagerListener(new OnItemClickUserPagerListener() {
            @Override
            public void onItemClick(View view, int position, ArrayList<StoryDetails> StoryDataSet) {
            Intent intent=new Intent(HomeActivity.this,UserStoryListActivity.class);
                intent.putExtra("StoryData",StoryDataSet);
                startActivity(intent);
            }
        });

    }

    private synchronized void buildGoogleclientConnection() {
        if (mGoogleclient == null) {
            mGoogleclient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnCamera:
                //captureFullImage();
               captureImage();
                break;
            case R.id.btnLeaderboard:
                gotoLeadeBoard();
                break;
            case R.id.addStory:
                goToAddUserStory();
                break;

        }

    }

    private void goToAddUserStory() {
        Intent userIntent=new Intent(HomeActivity.this,AddUserStoryActivity.class);
        userIntent.putExtra("UserName",UserID);
        startActivity(userIntent);
    }

    private void gotoLeadeBoard() {
        RequestHandler.getInstance(HomeActivity.this).handleRequest(new LeaderBoardDetailRequest(new LeaderSuccessListener(),
                new  LeaderErrorListener()));
    }

    private void captureImage() {

        ContentValues values=new ContentValues();
        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Bitmap thumbnail=null;
                try {
                    thumbnail= MediaStore.Images.Media.getBitmap(
                            getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Bitmap scaledBitmap = Bitmap.createScaledBitmap(thumbnail, 400,
                        500, true);
                int height=scaledBitmap.getHeight();
                int width=scaledBitmap.getWidth();
                Log.d(TAG, "Height " +height );
                Log.d(TAG, "Width" +width);
                String imageURL=getRealPathFromURI(imageUri);
                Log.d(TAG, "ImageURL " +imageURL );
               /* File path = new File(getFilesDir(), "picture");
                if (!path.exists()) {
                    path.mkdirs();
                }

                File imageFile = new File(path,imageFileName);
                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                uploadImage(bitmap);

*/

                //photo = (Bitmap) data.getExtras().get("data");
                uploadImage(scaledBitmap);
            }
        }
    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private void uploadImage(Bitmap imagebitmap) {
        mProgress.setVisibility(View.VISIBLE);
        Log.d(TAG, "Latitude===Upload " + location.getLongitude());
        Log.d(TAG, "Longnitude===Upload " + location.getLatitude());
        ImageDetailsObject data = new ImageDetailsObject();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imagebitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        data.setId("1");
        data.setmImagePath(imageString);
        data.setmUserName(UserID);
        data.setLongnitudePoint(String.valueOf(location.getLongitude()));
        data.setLatitiudePoint(String.valueOf(location.getLatitude()));
        RequestHandler.getInstance(HomeActivity.this).handleRequest(new ImageDetailRequest(new SuccessListener(),
               new  ErrorListener(), data));

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    private class SuccessListener implements Response.Listener {

        @Override
        public void onResponse(Object response) {
            if(response.equals(null)||response.equals(""))
            {
                Toast.makeText(HomeActivity.this,"Unable to Identify the Image",Toast.LENGTH_SHORT).show();
            }else{
                CameraResultDetailResponse imageDetail= UIUtils.getInstance().getPojoObject(String.valueOf(response),CameraResultDetailResponse.class);
                Log.d(TAG,"Response is coming Successfully");
                gotoCameraResult(imageDetail);
            }



        }
    }

    private class LeaderSuccessListener implements Response.Listener {

        @Override
        public void onResponse(Object response) {
            if(response.equals("")||response.equals(null))
            {
                Toast.makeText(HomeActivity.this,"LeaderBoard is Empty",Toast.LENGTH_SHORT).show();
            }else {
                UserLeaderBoardResponse leaderResponse = UIUtils.getInstance().getPojoObject(String.valueOf(response), UserLeaderBoardResponse.class);
                Intent intent = new Intent(HomeActivity.this, LeaderBoardActivity.class);
                intent.putExtra("LeaderBoardDetail", leaderResponse);
                intent.putExtra("UserDetail", mUserDetail);
                startActivity(intent);
            }

        }
    }
    private class LeaderErrorListener implements Response.ErrorListener {


        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }
    private void gotoCameraResult(CameraResultDetailResponse imageDetail) {
        mProgress.setVisibility(View.GONE);
        Intent cameraResult=new Intent(HomeActivity.this,CameraActivityDetails.class);
        cameraResult.putExtra("image",imageBytes);
        cameraResult.putExtra("CameraResponseDetial",imageDetail);
        cameraResult.putExtra("UserCountryName",mUserDetail.getLocality());
        startActivity(cameraResult);

    }

    private class ErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG,"ResponseError is coming Successfully");
        }
    }
    protected void onStart() {
        mGoogleclient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleclient.disconnect();
        super.onStop();
    }

    private void captureFullImage()
    {
        ContextWrapper cw = new ContextWrapper(this);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File path = new File(getFilesDir(), "picture");
        imageFileName = timeStamp + ".jpg";
        image = new File(path, imageFileName);

          if (Build.VERSION.SDK_INT > 21) { //use this if Lollipop_Mr1 (API 22) or above
              outputFileUri= FileProvider.getUriForFile(this,getPackageName()+".fileprovider", image);
        } else {
            outputFileUri = Uri.fromFile(image);
        }
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }
}
