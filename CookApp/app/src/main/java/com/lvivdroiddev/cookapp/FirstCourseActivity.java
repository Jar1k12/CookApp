package com.lvivdroiddev.cookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lvivdroiddev.cookapp.models.FirstCourseModel;

import java.util.List;

public class FirstCourseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private List<FirstCourseModel> courseList;

    TextView riceptOfCourse;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_course);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent i = getIntent();
        String nameOfCourse = i.getStringExtra("1");
        int position = i.getIntExtra("pos", 1);
        upload(position);
        toolbar.setTitle(nameOfCourse);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstCourseActivity.this.finish();
            }
        });


        database = FirebaseDatabase.getInstance();

        reference = database.getReference("FirstCourse");

        riceptOfCourse = (TextView) findViewById(R.id.riceptOfCourse);



    }

    public void upload(int position){

        FirstCourseModel courseModel = courseList.get(position);

        riceptOfCourse.setText(courseModel.riceptOfCourseStr + "");
    }


}
