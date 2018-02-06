package com.analyticdata.scanmepoc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.activity.CelebrityStoryDetailActivity;
import com.analyticdata.scanmepoc.networking.pojo.CelebData;
import com.analyticdata.scanmepoc.networking.pojo.CelebrityStoryDetails;

import com.analyticdata.scanmepoc.util.UIUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by M1030099 on 11/16/2017.
 */

public class VolunteerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<CelebrityStoryDetails> mList;
    private LayoutInflater layoutInflater;
    private CelebData data;
    OnItemClickPagerListener mItemClickListener;
    public VolunteerAdapter(Context mContext, ArrayList<CelebrityStoryDetails> list)
    {
        this.mContext=mContext;
        this.mList=list;
        this.layoutInflater=LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        data=new CelebData();
        View view = layoutInflater.inflate(R.layout.volunteer_item, container, false);
        ImageView volunteerImage= (ImageView) view.findViewById(R.id.imagevolunteer);
        TextView volunteerHeading= (TextView) view.findViewById(R.id.volunteerHeading);
        TextView volunteerDes= (TextView) view.findViewById(R.id.volunteerDescription);
        String Heading= UIUtils.convertintoCamelCase(mList.get(position).getTitle());
        volunteerHeading.setText(Heading);
        data.setmDate(mList.get(position).getCreatedDate());
        data.setmDescription(mList.get(position).getDescription());
        data.setmImagePath(mList.get(position).getImageLink());
        data.setmTitle(mList.get(position).getTitle());
        data.setmLocation(mList.get(position).getmName());
        Picasso.with(mContext).load("http://disposeandrecycleservice.azurewebsites.net"+mList.get(position).getImageLink()).resize(1000,500).into(volunteerImage);
        //volunteerDes.setText(mList.get(position).getmDescription());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v, position, mList);
            }
        });


        container.addView(view);
        return view;
    }

    private void gotoDetailPage(Context mContext,CelebData data) {
        Intent detailPage=new Intent(mContext, CelebrityStoryDetailActivity.class);
        detailPage.putExtra("Data",data);



    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    public interface OnItemClickPagerListener {
        public void onItemClick(View view, int position, ArrayList<CelebrityStoryDetails> CelebpgStoryDataSet);
    }

    public void setOnItemClickPagerListener(OnItemClickPagerListener mItemClickListener) {
        this.mItemClickListener =  mItemClickListener;
    }
}
