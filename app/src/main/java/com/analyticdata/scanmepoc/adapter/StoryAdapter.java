package com.analyticdata.scanmepoc.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.networking.pojo.StoryDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by M1030099 on 11/16/2017.
 */

public class StoryAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<StoryDetails> list;
    private LayoutInflater layoutInflater;
    private OnItemClickUserPagerListener mItemClickListener;

    public StoryAdapter(Context mContext, ArrayList<StoryDetails> list)
    {
        this.mContext=mContext;
        this.list=list;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = layoutInflater.inflate(R.layout.story_item, container, false);
        ImageView storyImage= (ImageView) view.findViewById(R.id.imageStory);
        TextView storyHeading= (TextView) view.findViewById(R.id.descriptionStory);
        TextView storyDes= (TextView) view.findViewById(R.id.storyHeading);
        storyHeading.setText(list.get(position).getTitle());
        storyDes.setText(list.get(position).getDescription());
        Picasso.with(mContext).load("http://disposeandrecycleservice.azurewebsites.net"+list.get(position).getmThumnail()).resize(400,300).into(storyImage);
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v, position, list);
            }
        });
        return view;
    }
    public interface OnItemClickUserPagerListener {
        public void onItemClick(View view, int position, ArrayList<StoryDetails> StoryDataSet);
    }

    public void setOnItemClickUserPagerListener(OnItemClickUserPagerListener mItemClickListener) {
        this.mItemClickListener =  mItemClickListener;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
