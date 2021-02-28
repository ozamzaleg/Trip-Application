package com.example.trip_application.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;


import com.example.trip_application.R;
import com.example.trip_application.objects.Comment;
import com.example.trip_application.utils.Constant;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Fragment_Add_Comment extends Fragment {
  private TextInputLayout add_comment_EDT_name;
    private TextInputLayout add_comment_EDT_comment;
    private MaterialButton add_comment_BTN_addComment;
    private RatingBar add_comment_RTB_rating;
    private float numberOfStar=0f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_comment, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void initViews() {
        add_comment_BTN_addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
            }
        });

        add_comment_RTB_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
               numberOfStar=rating;
            }
        });
    }

    private void  addComment(){
        Comment comment=new Comment()
                .setCid(UUID.randomUUID().toString())
                .setUserName(add_comment_EDT_name.getEditText().getText().toString())
                .setComment(add_comment_EDT_comment.getEditText().getText().toString())
                .setDate(System.currentTimeMillis())
                .setStars(numberOfStar);
        saveCommentToDB(comment);
    }

    private void saveCommentToDB(Comment comment) {
        String pid=getArguments().getString(Constant.EXTRA_PLACE_KEY);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constant.COMMENTS_DB);
        myRef.child(pid).push().setValue(comment);
    }

    private void findViews(View view) {
        add_comment_EDT_name=view.findViewById(R.id.add_comment_EDT_name);
        add_comment_EDT_comment=view.findViewById(R.id.add_comment_EDT_comment);
        add_comment_BTN_addComment=view.findViewById(R.id.add_comment_BTN_addComment);
        add_comment_RTB_rating=view.findViewById(R.id.add_comment_RTB_rating);
    }
}