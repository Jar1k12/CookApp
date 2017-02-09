package com.lvivdroiddev.cookapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lvivdroiddev.cookapp.R;
import com.lvivdroiddev.cookapp.adapters.SecondCourseAdapter;
import com.lvivdroiddev.cookapp.models.SecondCourseModel;

import java.util.ArrayList;
import java.util.List;


public class SecondCourseFragment extends Fragment {


    private RecyclerView rView;
    private SecondCourseAdapter mAdapter;
    private List<SecondCourseModel> result;

    private LinearLayoutManager manager;

    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second_course, container, false);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("SecondCourse");

        result = new ArrayList<>();

        rView = (RecyclerView) v.findViewById(R.id.second_courseList);

        rView.setHasFixedSize(true);

        //manager.setOrientation(LinearLayoutManager.VERTICAL);

        rView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new SecondCourseAdapter(result);

        rView.setAdapter(mAdapter);

        updateList();

        return v;
    }


    private void updateList() {

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                result.add(dataSnapshot.getValue(SecondCourseModel.class));

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                SecondCourseModel courseModel = dataSnapshot.getValue(SecondCourseModel.class);

                int index = getItemIndex(courseModel);

                result.set(index, courseModel);

                mAdapter.notifyItemChanged(index);


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                SecondCourseModel courseModel = dataSnapshot.getValue(SecondCourseModel.class);

                int index = getItemIndex(courseModel);

                result.remove(index);

                mAdapter.notifyItemRemoved(index);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @NonNull
    private int getItemIndex(SecondCourseModel courseModel) {

        int index = -1;
        for (int i = 0; i < result.size(); i++) {

            if (result.get(i).key.equals(courseModel.key)) {

                index = 1;
                break;

            }

        }

        return index;
    }


}
