package com.example.trip_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.trip_application.objects.MyPlacesData;
import com.example.trip_application.objects.MyTripAdapter;

public class Activity_List_Places extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        MyPlacesData[] myPlacesData = new MyPlacesData[]{
                new MyPlacesData("Tylet","2019 film",R.drawable.tylet),
                new MyPlacesData("Venom","2018 film",R.drawable.carmel),

        };

        MyTripAdapter myMovieAdapter = new MyTripAdapter(myPlacesData,Activity_List_Places.this);
        recyclerView.setAdapter(myMovieAdapter);





    }
}