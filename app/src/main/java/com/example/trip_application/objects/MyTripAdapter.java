package com.example.trip_application.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.trip_application.Activity_List_Places;
import com.example.trip_application.R;

public class MyTripAdapter extends RecyclerView.Adapter<MyTripAdapter.ViewHolder> {

    MyPlacesData[] myTripData;
    Context context;

    public MyTripAdapter(MyPlacesData[] myPlacesData, Activity_List_Places activity) {
        this.myTripData = myPlacesData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.trip_item_list ,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyPlacesData myPlaceDataList = myTripData[position];
        holder.textViewName.setText(myPlaceDataList.getPlaceName());
        holder.textViewDate.setText(myPlaceDataList.getPlaceDate());
        holder.movieImage.setImageResource(myPlaceDataList.getPlaceImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, myPlaceDataList.getPlaceName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myTripData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewDate = itemView.findViewById(R.id.textdate);

        }
    }

}