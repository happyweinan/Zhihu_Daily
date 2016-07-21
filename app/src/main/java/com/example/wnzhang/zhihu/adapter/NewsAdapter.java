package com.example.wnzhang.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wnzhang.zhihu.NewsDetailActivity;
import com.example.wnzhang.zhihu.R;
import com.example.wnzhang.zhihu.bean.StoriesEntity;

import java.util.ArrayList;

/**
 * Created by wnzhang on 16-7-15.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private ArrayList<StoriesEntity> mList;
    private Context mContext;

    public NewsAdapter(ArrayList<StoriesEntity> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(mContext).inflate(
                R.layout.news_item, parent, false);
        return new MyViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final StoriesEntity entity = mList.get(position);
        holder.titleTv.setText(entity.getTitle());
        if (null == entity.getImages()) {
            holder.titleIv.setVisibility(View.GONE);
        } else {
            holder.titleIv.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(entity.getImages().get(0)).into(holder.titleIv);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("id", entity.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        ImageView titleIv;

        public MyViewHolder(View view) {
            super(view);
            titleTv = (TextView) view.findViewById(R.id.title_tv);
            titleIv = (ImageView) view.findViewById(R.id.title_iv);
        }
    }
}
