package com.example.trip_application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.example.trip_application.R;
import com.example.trip_application.adapters.SlidePagerAdapter;
import com.example.trip_application.fragments.Fragment_List;
import com.example.trip_application.fragments.Fragment_Map;


import java.util.ArrayList;
import java.util.List;

public class Activity_List_Places extends AppCompatActivity {

    private ViewPager list_places_LAY_viewPager;
    private PagerAdapter pagerAdapter;
    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);
        initView();
    }

    private void initView() {
        List<Fragment> list = new ArrayList<>();
        fragment_list = new Fragment_List();
        fragment_map = new Fragment_Map();
        fragment_list.setArguments(getIntent().getExtras());
        fragment_map.setArguments(getIntent().getExtras());
        list.add(fragment_list);
        list.add(fragment_map);
        list_places_LAY_viewPager = findViewById(R.id.list_places_LAY_viewPager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        list_places_LAY_viewPager.setAdapter(pagerAdapter);
    }
}
