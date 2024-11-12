package com.duongtn.adminapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duongtn.adminapp.R;
import com.duongtn.adminapp.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courseList;
    private final OnCourseClickListener listener;

    // Define an interface for click events
    public interface OnCourseClickListener {
        void onCourseClick(Course course);
    }

    public CourseAdapter(List<Course> courseList, OnCourseClickListener listener) {
        this.courseList = courseList;
        this.listener = listener;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        public TextView name, instructorName;

        public CourseViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.courseName);
            instructorName = view.findViewById(R.id.courseInstructor);

            // Handle click events
            view.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onCourseClick(courseList.get(position));
                }
            });
        }
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.name.setText(course.getName());
        holder.instructorName.setText(course.getInstructorName());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    // Method to update the course list
    public void updateCourseList(List<Course> newCourseList) {
        this.courseList = newCourseList;
        notifyDataSetChanged();
    }
}
