package com.example.trip_application.activities;



import android.content.Intent;

import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.trip_application.R;
import com.example.trip_application.enums.Area;
import com.example.trip_application.enums.Type;
import com.example.trip_application.objects.Place;
import com.example.trip_application.utils.Constant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Activity_Add_Place extends Activity_Base  {
    private TextInputLayout add_place_EDT_description;
    private TextInputLayout add_place_EDT_cost;
    private TextInputLayout add_place_EDT_address;
    private MaterialButton add_place_BTN_addPlace;
    private ImageView add_place_IMG_park;
    private ImageView add_place_IMG_beaches;
    private ImageView add_place_IMG_trip;
    private ImageView add_place_IMG_sorth;
    private ImageView add_place_IMG_north;
    private ImageView add_place_IMG_center;
    private ImageView add_place_IMG_jerusalem;
    private TextInputLayout add_place_EDT_name;
    private Area area;
    private Type type;
    private Uri pictureUrl;
    private ImageView add_place_IMG_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        findViews();
        initViews();
    }

    private void findViews() {
        add_place_EDT_description=findViewById(R.id.add_place_EDT_description);
        add_place_EDT_cost=findViewById(R.id.add_place_EDT_cost);
        add_place_EDT_address=findViewById(R.id.add_place_EDT_address);
        add_place_BTN_addPlace=findViewById(R.id.add_place_BTN_addPlace);
        add_place_IMG_park=findViewById(R.id.add_place_IMG_park);
        add_place_IMG_beaches=findViewById(R.id.add_place_IMG_beaches);
        add_place_IMG_trip=findViewById(R.id.add_place_IMG_trip);
        add_place_IMG_sorth=findViewById(R.id.add_place_IMG_sorth);
        add_place_IMG_north=findViewById(R.id.add_place_IMG_north);
        add_place_IMG_center=findViewById(R.id.add_place_IMG_center);
        add_place_IMG_jerusalem=findViewById(R.id.add_place_IMG_jerusalem);
        add_place_EDT_name=findViewById(R.id.add_place_EDT_name);
        add_place_IMG_picture=findViewById(R.id.add_place_IMG_picture);
    }

    private void initViews() {
        add_place_BTN_addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(area!=null&&type!=null) {
                    setPlace();
                }else{
                makeToast("you need to choose area and type");
            }
            }
        });
        add_place_IMG_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(add_place_IMG_park);
                type=Type.park;
            }
        });
        add_place_IMG_beaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(add_place_IMG_beaches);
                type=Type.beaches;
            }
        });
        add_place_IMG_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewType(add_place_IMG_trip);
                type=Type.trip;
            }
        });
        add_place_IMG_sorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(add_place_IMG_sorth);
                area=Area.south;
            }
        });
        add_place_IMG_north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(add_place_IMG_north);
                area=Area.north;
            }
        });
        add_place_IMG_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(add_place_IMG_center);
                area=Area.center;
            }
        });
        add_place_IMG_jerusalem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewArea(add_place_IMG_jerusalem);
                area=Area.jerusalem;
            }
        });
        add_place_IMG_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private void setPlace() {
        double cost=0.0;
        try {
            cost = Double.parseDouble(add_place_EDT_cost.getEditText().getText().toString());
        }catch (Exception e){
        }
        Place place = new Place()
                .setPid(UUID.randomUUID().toString())
                .setArea(area)
                .setType(type)
                .setName(add_place_EDT_name.getEditText().getText().toString())
                .setDescription(add_place_EDT_description.getEditText().getText().toString())
                .setCost(cost)
                .setAddress(add_place_EDT_address.getEditText().getText().toString());
        setAddress(place);
        saveImageToDB(place);
    }

    private void openSearchPlaces() {
        Intent intent=new Intent(this, Activity_Search_Places.class);
        startActivity(intent);
        finish();
    }
    private void  setAddress(Place place){
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        try {
            address = coder.getFromLocationName(place.getAddress(),5);
            Address location=null;
            if (address!=null) {
                location = address.get(0);
                place.setLatitude(location.getLatitude());
                place.setLongitude(location.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

private void uploadImage(){
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), Constant.PICK_IMAGE);
}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.PICK_IMAGE&&data!=null&&data.getData()!=null) {
            pictureUrl=data.getData();
            Glide.with(this)
                    .load(pictureUrl)
                    .into(add_place_IMG_picture);
        }
    }
    private void saveImageToDB(Place place){
        if(pictureUrl==null) {
            savePlaceToDB(place);
            return;
        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            StorageReference ref = storageReference.child("IMAGES/" + place.getPid());

        ref.putFile(pictureUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                readPictureUrl(place);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                makeToast("failed to save");
                openSearchPlaces();
            }
        });
    }
    private void readPictureUrl(Place place){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference ref = storageReference.child("IMAGES/" + place.getPid());
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                place.setPictureUrl(uri.toString());
                savePlaceToDB(place);
            }
        });
    }
    private void savePlaceToDB(Place place){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constant.PLACES_DB);
        myRef.child(area.toString()).child(type.toString()).child(place.getPid()).setValue(place).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                openSearchPlaces();
            }
        });
    }

}