package com.example.trip_application.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trip_application.enums.Area;
import com.example.trip_application.enums.Type;
import com.example.trip_application.utils.Constant;

public class Activity_Base extends AppCompatActivity {
    protected ImageView clickImageViewArea;
    protected ImageView clickImageViewType;
    protected Area area;
    protected Type type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    protected  void updateViewArea(ImageView imageView){
        if(clickImageViewArea!=null) {
            clickImageViewArea.setBackgroundColor(Color.WHITE);
        }
        imageView.setBackgroundColor(Color.rgb(Constant.COLOR_NUMBER,Constant.COLOR_NUMBER,Constant.COLOR_NUMBER));
        clickImageViewArea=imageView;
    }
    protected void updateViewType(ImageView imageView){
        if(clickImageViewType!=null) {
            clickImageViewType.setBackgroundColor(Color.WHITE);
        }
        imageView.setBackgroundColor(Color.rgb(Constant.COLOR_NUMBER,Constant.COLOR_NUMBER,Constant.COLOR_NUMBER));
        clickImageViewType=imageView;
    }
    protected void makeToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


}