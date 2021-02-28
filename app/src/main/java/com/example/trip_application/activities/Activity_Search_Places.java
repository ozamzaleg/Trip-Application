package com.example.trip_application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.trip_application.R;
import com.example.trip_application.enums.Area;
import com.example.trip_application.enums.Type;
import com.example.trip_application.utils.Constant;
import com.google.android.material.button.MaterialButton;

public class Activity_Search_Places extends Activity_Base {
    private MaterialButton search_places_BTN_search;
    private ImageView search_places_IMG_park;
    private ImageView search_places_IMG_trip;
    private ImageView search_places_IMG_beaches;
    private ImageView search_places_IMG_north;
    private ImageView search_places_IMG_sorth;
    private ImageView search_places_IMG_jerusalem;
    private ImageView search_places_IMG_center;
    private MaterialButton search_places_BTN_addPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_places);
        findViews();
        initViews();
    }

    private void initViews() {
        search_places_BTN_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(area!=null&&type!=null) {
                    searchPlace();
                }else{
                    makeToast("you need to choose area and type");
                }

            }
        });
        search_places_IMG_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(search_places_IMG_park);
             type=Type.park;

            }
        });
        search_places_IMG_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(search_places_IMG_trip);
                type=Type.trip;
          }
        });
        search_places_IMG_beaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(search_places_IMG_beaches);
                type=Type.beaches;
            }
        });
        search_places_IMG_north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(search_places_IMG_north);
                area=Area.north;
            }
        });
        search_places_IMG_sorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(search_places_IMG_sorth);
                area=Area.south;
            }
        });
        search_places_IMG_jerusalem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(search_places_IMG_jerusalem);
                area=Area.jerusalem;
            }
        });
        search_places_IMG_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(search_places_IMG_center);
                area=Area.center;
            }
        });
        search_places_BTN_addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPlace();
            }
        });
    }

    private void searchPlace() {
        Intent intent=new Intent(this,Activity_List_Places.class);
        if(area!=null&&type!=null) {
            intent.putExtra(Constant.EXTRA_AREA, area.toString());
            intent.putExtra(Constant.EXTRA_TYPE, type.toString());
        }
            startActivity(intent);
    }

    private void findViews() {
        search_places_BTN_search=findViewById(R.id.search_places_BTN_search);
        search_places_IMG_park =findViewById(R.id.search_places_IMG_park);
        search_places_IMG_trip=findViewById(R.id.search_places_IMG_trip);
        search_places_IMG_beaches=findViewById(R.id.search_places_IMG_beaches);
        search_places_IMG_north=findViewById(R.id.search_places_IMG_north);
        search_places_IMG_sorth=findViewById(R.id.search_places_IMG_sorth);
        search_places_IMG_jerusalem=findViewById(R.id.search_places_IMG_jerusalem);
        search_places_IMG_center=findViewById(R.id.search_places_IMG_center);
        search_places_BTN_addPlace = findViewById(R.id.search_places_BTN_addPlace);
    }
    private void openAddPlace() {
        Intent intent=new Intent(this, Activity_Add_Place.class);
        startActivity(intent);
    }

}