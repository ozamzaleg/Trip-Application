package com.example.trip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Activity_Search_Places extends AppCompatActivity {
    private MaterialButton search_BTN;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_places);
        findViews();
        initViews();
        Spinner spinner=findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text=parent.getItemAtPosition(position).toString();

                Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerArea=findViewById(R.id.spinnerArea);
        ArrayAdapter<CharSequence> adapterArea = ArrayAdapter.createFromResource(this,
                R.array.Area, android.R.layout.simple_spinner_item);
        adapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(adapterArea);
        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text=parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initViews() {
        search_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPlace();

            }
        });
    }

    private void searchPlace() {
        Intent intent=new Intent(this,Activity_List_Places.class);
        startActivity(intent);
        finish();
    }

    private void findViews() {
        search_BTN=findViewById(R.id.search_BTN);
    }
}