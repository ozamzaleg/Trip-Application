package com.example.trip_application.fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trip_application.R;

import com.example.trip_application.activities.Activity_Description;
import com.example.trip_application.adapters.MyPlacesAdapter;
import com.example.trip_application.enums.Area;
import com.example.trip_application.enums.Type;
import com.example.trip_application.objects.Comment;
import com.example.trip_application.objects.Place;
import com.example.trip_application.utils.Constant;

import com.google.firebase.auth.PlayGamesAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;


public class Fragment_List extends Fragment {
    private RecyclerView list_places_LST_places;
    private Area area;
    private Type type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        initView();
        readPlaces();
        return view;
    }
private void initView() {
    area = Area.valueOf(getArguments().getString(Constant.EXTRA_AREA));
    type = Type.valueOf(getArguments().getString(Constant.EXTRA_TYPE));
}

    private void findViews(View view) {
        list_places_LST_places = view.findViewById(R.id.list_places_LST_places);
    }

    private void openDescription(Place place) {
        Intent intent=new Intent(getContext(), Activity_Description.class);
        intent.putExtra(Constant.EXTRA_PLACE_DATA,new Gson().toJson(place));
        startActivity(intent);
    }

    private void readPlaces(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constant.PLACES_DB);
        myRef.child(area.toString()).child(type.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Place> places =new ArrayList<>();
                for (DataSnapshot placeSnapshot:dataSnapshot.getChildren()) {
                    places.add(placeSnapshot.getValue(Place.class));
                }
                readStars(places);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getContext(),"error reading places from data base",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void readStars(ArrayList<Place> places) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constant.COMMENTS_DB);
        for (Place place:places) {
            myRef.child(place.getPid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<Comment> comments=new ArrayList<>();
                    float sum=0f;
                    for (DataSnapshot commentSnapshot : dataSnapshot.getChildren()) {
                        Comment comment=commentSnapshot.getValue(Comment.class);
                        comments.add(comment);
                        sum+=comment.getStars();
                    }
                    if(comments.size()!=0) {
                        place.setStars(sum / comments.size());
                    }
                    place.setNumberOfComments(comments.size());
                    displayPlaces(places);
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(getContext(), "error reading places from data base", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void displayPlaces(ArrayList<Place> places) {
        list_places_LST_places.setLayoutManager(new LinearLayoutManager(getContext()));
        MyPlacesAdapter adapter_place = new MyPlacesAdapter(getContext(), places);
        adapter_place.setClickListener(new MyPlacesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                openDescription(places.get(position));
            }

            @Override
            public void onItemDelete(View view, int position) {
                deleteItem(places.get(position));
            }
        });
        list_places_LST_places.setAdapter(adapter_place);
    }

    private void deleteItem(Place place) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constant.PLACES_DB);
        myRef.child(place.getArea().toString()).child(place.getType().toString()).child(place.getPid()).removeValue();
        myRef=database.getReference(Constant.COMMENTS_DB);
        myRef.child(place.getPid()).removeValue();

    }
}