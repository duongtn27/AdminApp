package com.duongtn.adminapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duongtn.adminapp.R;
import com.duongtn.adminapp.adapter.CourseAdapter;
import com.duongtn.adminapp.database.CourseHelper;
import com.duongtn.adminapp.model.Course;

import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCourses;
    private CourseAdapter courseAdapter;
    private CourseHelper courseHelper;
    private Button btnCreateCourse, btnResetDatabase;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawerLayout);
        View openDrawerButton = findViewById(R.id.openDrawerButton);

        openDrawerButton.setOnClickListener(v -> drawerLayout.openDrawer(findViewById(R.id.navRecyclerView)));

        courseHelper = new CourseHelper(this);

        // Initialize RecyclerView
        recyclerViewCourses = findViewById(R.id.recyclerViewCourses);
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(this));

        // Load courses into RecyclerView
        loadCourses();

        // Button to create a new course
        btnCreateCourse = findViewById(R.id.btnCreateCourse);
        btnCreateCourse.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboardActivity.this, CourseEditorActivity.class);
            startActivity(intent);
        });

        // Button to reset database
        btnResetDatabase = findViewById(R.id.btnResetDatabase);
        btnResetDatabase.setOnClickListener(view -> {
            courseHelper.deleteAllCourses(); // Clear all courses
            loadCourses(); // Refresh the RecyclerView
            Toast.makeText(this, "Database reset successfully", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCourses();
    }

    // Load courses from the database
    private void loadCourses() {
        List<Course> courseList = courseHelper.getAllCourses();

        // Initialize CourseAdapter if not already initialized
        if (courseAdapter == null) {
            courseAdapter = new CourseAdapter(courseList, course -> {
                // Handle course click: navigate to CourseEditorActivity for editing
                Intent intent = new Intent(AdminDashboardActivity.this, CourseEditorActivity.class);
                intent.putExtra("COURSE_ID", course.getId());
                startActivity(intent);
            });
            recyclerViewCourses.setAdapter(courseAdapter);
        } else {
            courseAdapter.updateCourseList(courseList);
        }
    }
}
