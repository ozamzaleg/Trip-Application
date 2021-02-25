/*package com.example.trip_application.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Adapter_Post extends RecyclerView.Adapter<Adapter_Post.MyViewHolder> {

    private ArrayList<Post> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    Adapter_Post(Context context, ArrayList<Post> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_post, parent, false);
        return new MyViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("pttt", "Binding " + position);
        Post post = mData.get(position);
        holder.post_LBL_author.setText(position + "  " + post.getAuthor());
        holder.post_LBL_content.setText(post.getContent());
        holder.post_LBL_likes.setText(post.getLikes() + " Likes");

        if (post.getLikes() < 100) {
            holder.post_LBL_likes.setTextColor(Color.RED);
        } else {
            holder.post_LBL_likes.setTextColor(Color.BLACK);
        }

        Glide
                .with(mInflater.getContext())
                .load(post.getUserImageUrl())
                .centerCrop()
                .into(holder.post_IMG_user);

        Glide
                .with(mInflater.getContext())
                .load(post.getImageUrl())
                .into(holder.post_IMG_image);


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    Post getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onReportClick(int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView post_IMG_user;
        ImageView post_IMG_image;
        TextView post_LBL_author;
        TextView post_LBL_date;
        TextView post_LBL_content;
        TextView post_LBL_likes;
        MaterialButton post_BTN_report;

        MyViewHolder(View itemView) {
            super(itemView);
            post_IMG_user = itemView.findViewById(R.id.post_IMG_user);
            post_IMG_image = itemView.findViewById(R.id.post_IMG_image);
            post_LBL_author = itemView.findViewById(R.id.post_LBL_author);
            post_LBL_date = itemView.findViewById(R.id.post_LBL_date);
            post_LBL_content = itemView.findViewById(R.id.post_LBL_content);
            post_LBL_likes = itemView.findViewById(R.id.post_LBL_likes);
            post_BTN_report = itemView.findViewById(R.id.post_BTN_report);

            post_BTN_report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onReportClick(getAdapterPosition());
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }
}

/*
post_LBL_likes
post_LBL_content
post_LBL_date
post_LBL_author
 *
public class Adapter_Place {

}
/*/