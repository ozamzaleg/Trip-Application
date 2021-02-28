package com.example.trip_application.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.example.trip_application.R;
import com.example.trip_application.fragments.Fragment_Add_Comment;
import com.example.trip_application.fragments.Fragment_Comments_List;
import com.example.trip_application.utils.Constant;


public class Activity_Comment extends AppCompatActivity {
private String pid;
    private Fragment_Add_Comment fragment_add_comment ;
    private Fragment_Comments_List fragment_comments_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
    }
private void initView(){
        pid=getIntent().getStringExtra(Constant.EXTRA_PLACE_KEY);
         Bundle bundle=new Bundle();
         bundle.putString(Constant.EXTRA_PLACE_KEY,pid);
        fragment_add_comment = new Fragment_Add_Comment();
        fragment_add_comment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.comment_LAY_addComment, fragment_add_comment).commit();
        fragment_comments_list = new Fragment_Comments_List();
        fragment_comments_list.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.comment_LAY_list, fragment_comments_list).commit();
        }
}