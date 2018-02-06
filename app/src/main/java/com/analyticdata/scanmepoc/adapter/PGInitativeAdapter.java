package com.analyticdata.scanmepoc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.analyticdata.scanmepoc.R;
import com.analyticdata.scanmepoc.networking.pojo.PGStoryDetails;
import com.analyticdata.scanmepoc.util.UIUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by M1030099 on 11/16/2017.
 */

public class PGInitativeAdapter extends RecyclerView.Adapter<PGInitativeAdapter.ViewHolder>  {
    private Context mContext;
    private ArrayList<PGStoryDetails> mList;
    OnItemClickListener mItemClickListener;
    public PGInitativeAdapter(Context context, ArrayList<PGStoryDetails> list)
    {
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public PGInitativeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pg_initative_list,
                parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(PGInitativeAdapter.ViewHolder holder, int position) {
        Picasso.with(mContext).load("http://disposeandrecycleservice.azurewebsites.net"+mList.get(position).getImagelink()).resize(1000,500).into(holder.mImage);
        String Title= UIUtils.convertintoCamelCase(mList.get(position).getmTitle());
        holder.mDescription.setText(Title);
        holder.mDate.setText(mList.get(position).getmCreatedDate());


    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position, ArrayList<PGStoryDetails> pgStoryDataSet);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView mDescription;
        public TextView mDate;
        public ImageView mImage;
        public ViewHolder(View itemView) {
            super(itemView);
            mDescription= (TextView) itemView.findViewById(R.id.heading);
            mImage= (ImageView) itemView.findViewById(R.id.initativeImage);
            mDate= (TextView) itemView.findViewById(R.id.createDate);
            mImage.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getPosition(), mList);
        }
    }


}
