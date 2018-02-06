package com.analyticdata.scanmepoc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.activity.PGStoryDetailsActivity;
import com.analyticdata.scanmepoc.networking.pojo.UserDetails;
import com.analyticdata.scanmepoc.networking.pojo.UserLeaderBoardDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by M1030099 on 11/21/2017.
 */

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<UserLeaderBoardDetail> mList;
    private int count=1;

    public LeaderBoardAdapter(Context context, ArrayList<UserLeaderBoardDetail> list)
    {
        this.mContext=context;
        this.mList=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_leaderboard_list,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        holder.mUserName.setText(mList.get(position).getmFirstName()+" "+mList.get(position).getmLastName());
        String imagepath=mList.get(position).getmProfileImage();
        Picasso.with(mContext).load("http://disposeandrecycleservice.azurewebsites.net"+imagepath).resize(100,100).into(holder.mUserImage);
        holder.mUserRank.setText(String.valueOf(count++));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mUserImage;
        TextView mUserName;
        TextView mUserRank;
        public ViewHolder(View itemView) {
            super(itemView);
            mUserImage= (ImageView) itemView.findViewById(R.id.userImage);
            mUserName= (TextView) itemView.findViewById(R.id.profileName);
            mUserRank= (TextView) itemView.findViewById(R.id.rankNumber);
        }
    }
}
