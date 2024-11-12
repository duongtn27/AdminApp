package com.duongtn.adminapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.duongtn.adminapp.R;
import com.duongtn.adminapp.database.CourseHelper;
import com.duongtn.adminapp.model.Course;

public class CourseEditorActivity extends AppCompatActivity {

    private EditText editCourseName, editInstructorName, editCapacity, editDuration, editPrice, editCourseTime, editDescription, editAdditionalNotes;
    private Spinner editDayOfWeek, editClassType;
    private Button btnSaveCourse, btnDeleteCourse;
    private CourseHelper courseHelper;
    private int courseId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_editor);

        // Initialize CourseHelper
        courseHelper = new CourseHelper(this);

        // Initialize views
        editCourseName = findViewById(R.id.editCourseName);
        editInstructorName = findViewById(R.id.editInstructorName);
        editCapacity = findViewById(R.id.editCapacity);
        editDuration = findViewById(R.id.editDuration);
        editPrice = findViewById(R.id.editPrice);
        editCourseTime = findViewById(R.id.editCourseTime);
        editDescription = findViewById(R.id.editDescription);
        editAdditionalNotes = findViewById(R.id.editAdditionalNotes);
        editDayOfWeek = findViewById(R.id.editDayOfWeek);
        editClassType = findViewById(R.id.editClassType);
        btnSaveCourse = findViewById(R.id.btnSaveCourse);
        btnDeleteCourse = findViewById(R.id.btnDeleteCourse);

        // Populate Spinners
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this,
                R.array.days_of_week, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editDayOfWeek.setAdapter(dayAdapter);

        ArrayAdapter<CharSequence> classTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.class_types, android.R.layout.simple_spinner_item);
        classTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editClassType.setAdapter(classTypeAdapter);

        // Load course if editing
        courseId = getIntent().getIntExtra("COURSE_ID", -1);
        if (courseId != -1) {
            Course course = courseHelper.getCourse(courseId);
            if (course != null) {
                editCourseName.setText(course.getName());
                editInstructorName.setText(course.getInstructorName());
                editCapacity.setText(String.valueOf(course.getCapacity()));
                editDuration.setText(String.valueOf(course.getDuration()));
                editPrice.setText(String.valueOf(course.getPrice()));
                editCourseTime.setText(course.getTime());
                editDescription.setText(course.getDescription());
                editAdditionalNotes.setText(course.getAdditionalNotes());
                // Set Spinner selections
                editDayOfWeek.setSelection(((ArrayAdapter) editDayOfWeek.getAdapter()).getPosition(course.getDay()));
                editClassType.setSelection(((ArrayAdapter) editClassType.getAdapter()).getPosition(course.getType()));
                btnDeleteCourse.setVisibility(View.VISIBLE);
            }
        }

        // Save course on button click
        btnSaveCourse.setOnClickListener(view -> {
            if (validateFields()) {
                Course course = new Course();
                course.setName(editCourseName.getText().toString());
                course.setInstructorName(editInstructorName.getText().toString());
                course.setCapacity(Integer.parseInt(editCapacity.getText().toString()));
                course.setDuration(Integer.parseInt(editDuration.getText().toString()));
                course.setPrice(Double.parseDouble(editPrice.getText().toString()));
                course.setTime(editCourseTime.getText().toString());
                course.setDescription(editDescription.getText().toString());
                course.setAdditionalNotes(editAdditionalNotes.getText().toString());
                course.setDay(editDayOfWeek.getSelectedItem().toString());
                course.setType(editClassType.getSelectedItem().toString());

                if (courseId == -1) {
                    // Adding a new course
                    long newCourseId = courseHelper.insertCourse(course);
                    if (newCourseId != -1) {
                        Toast.makeText(this, "Course added successfully", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                    } else {
                        Toast.makeText(this, "Failed to add course", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Updating an existing course
                    course.setId(courseId);
                    int rowsAffected = courseHelper.updateCourse(course);
                    if (rowsAffected > 0) {
                        Toast.makeText(this, "Course updated successfully", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);  // Send result back to AdminDashboardActivity
                    } else {
                        Toast.makeText(this, "Failed to update course", Toast.LENGTH_SHORT).show();
                    }
                }

                finish();
            }
        });


        // Delete course on button click
        btnDeleteCourse.setOnClickListener(view -> {
            if (courseId != -1) {
                boolean isDeleted = courseHelper.deleteCourse(courseId);
                if (isDeleted) {
                    Toast.makeText(this, "Course deleted successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to delete course", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateFields() {
        if (editCourseName.getText().toString().isEmpty()) {
            editCourseName.setError("Course name required");
            return false;
        }
        if (editInstructorName.getText().toString().isEmpty()) {
            editInstructorName.setError("Instructor name required");
            return false;
        }
        if (editCapacity.getText().toString().isEmpty()) {
            editCapacity.setError("Capacity required");
            return false;
        }
        if (editDuration.getText().toString().isEmpty()) {
            editDuration.setError("Duration required");
            return false;
        }
        if (editPrice.getText().toString().isEmpty()) {
            editPrice.setError("Price required");
            return false;
        }
        if (editCourseTime.getText().toString().isEmpty()) {
            editCourseTime.setError("Time required");
            return false;
        }
        if (editDayOfWeek.getSelectedItem() == null) {
            Toast.makeText(this, "Please select a day of the week", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editClassType.getSelectedItem() == null) {
            Toast.makeText(this, "Please select a class type", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
