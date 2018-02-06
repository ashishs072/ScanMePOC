package com.analyticdata.scanmepoc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.activity.UserStoryDetailActivity;
import com.analyticdata.scanmepoc.networking.pojo.StoryDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by M1030099 on 11/21/2017.
 */

public class UserStoryListAdapter extends RecyclerView.Adapter<UserStoryListAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<StoryDetails> list;
    OnItemClickListener mItemClickListener;

    public UserStoryListAdapter(Context context, ArrayList<StoryDetails> list)
    {
        this.mContext=context;
        this.list=list;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public UserStoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_list_item,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserStoryListAdapter.ViewHolder holder, int position) {
               holder.mTitle.setText(list.get(position).getTitle());
               holder.mDescription.setText(list.get(position).getDescription());
        String Imagepath=list.get(position).getmThumnail();
        Picasso.with(mContext).load("http://disposeandrecycleservice.azurewebsites.net"+Imagepath).resize(400,300).into(holder.mImage);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mDescription;
        public TextView mTitle;
        public ImageView mImage;
        public ViewHolder(View itemView) {
            super(itemView);
            mDescription= (TextView) itemView.findViewById(R.id.storyHeading);
            mImage= (ImageView) itemView.findViewById(R.id.imageStory);
            mTitle= (TextView) itemView.findViewById(R.id.descriptionStory);
            mImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getPosition(), list);
        }
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position, ArrayList<StoryDetails> UserStoryDataSet);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
