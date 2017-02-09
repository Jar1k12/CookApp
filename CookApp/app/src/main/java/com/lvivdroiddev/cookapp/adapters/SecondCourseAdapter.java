package com.lvivdroiddev.cookapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lvivdroiddev.cookapp.R;
import com.lvivdroiddev.cookapp.models.SecondCourseModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SecondCourseAdapter extends RecyclerView.Adapter<SecondCourseAdapter.ViewHolder> {

    private List<SecondCourseModel> courseList;
    static Context context;

    public SecondCourseAdapter(List<SecondCourseModel> courseList) {
        this.courseList = courseList;
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView nameOfCourse;
        ImageView courseView;
        CardView cardView;

        String url = "";


        ViewHolder(View itemView) {
            super(itemView);

            nameOfCourse = (TextView) itemView.findViewById(R.id.second_nameOfCourse);
            courseView = (ImageView) itemView.findViewById(R.id.second_courseView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            context = itemView.getContext();

            cardView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            Intent i;
            switch (v.getId()) {
                case R.id.card_view:
                    // i = new Intent(v.getContext(), FirstCourseActivity.class);
                    //  v.getContext().startActivity(i);
                    break;
            }

        }
    }


    @Override
    public SecondCourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SecondCourseAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.second_course_item, parent, false));
    }


    @Override
    public void onBindViewHolder(SecondCourseAdapter.ViewHolder holder, int position) {

        SecondCourseModel courseModel = courseList.get(position);

        holder.nameOfCourse.setText(courseModel.nameOfCourseStr + " ");
        holder.url = courseModel.imageCourseStr;


        Picasso.with(context)
                .load(holder.url)
                .placeholder(R.drawable.cast_mini_controller_progress_drawable)
                .error(R.drawable.cast_mini_controller_progress_drawable)
                .into(holder.courseView);


    }


    @Override
    public int getItemCount() {
        return courseList.size();

    }

}
