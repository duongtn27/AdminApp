package com.duongtn.adminapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.duongtn.adminapp.R;
import com.duongtn.adminapp.database.ClassInstanceHelper;
import com.duongtn.adminapp.model.ClassInstance;

public class ClassInstanceEditorActivity extends Activity {
    private EditText editTextDate, editTextTeacher, editTextAdditionalComments;
    private Button buttonSave, buttonUpdate, buttonDelete;
    private int classInstanceId;
    private int courseId;

    private ClassInstanceHelper classInstanceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_instance_editor);

        // Initialize views
        editTextDate = findViewById(R.id.editTextDate);
        editTextTeacher = findViewById(R.id.editTextTeacher);
        editTextAdditionalComments = findViewById(R.id.editTextAdditionalComments);
        buttonSave = findViewById(R.id.buttonSave);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        classInstanceHelper = new ClassInstanceHelper(this);

        // Get the intent data
        Intent intent = getIntent();
        courseId = intent.getIntExtra("courseId", -1);
        classInstanceId = intent.getIntExtra("classInstanceId", -1);

        if (classInstanceId != -1) {
            // If classInstanceId is provided, we're editing an existing class instance
            loadClassInstanceData(classInstanceId);
        } else {
            // Otherwise, we are adding a new class instance
            buttonSave.setVisibility(View.VISIBLE);
            buttonUpdate.setVisibility(View.GONE);
            buttonDelete.setVisibility(View.GONE);
        }

        // Set onClick listeners
        buttonSave.setOnClickListener(v -> saveClassInstance());
        buttonUpdate.setOnClickListener(v -> updateClassInstance());
        buttonDelete.setOnClickListener(v -> deleteClassInstance());
    }

    private void loadClassInstanceData(int instanceId) {
        // Load data from the database
        ClassInstance classInstance = classInstanceHelper.getClassInstance(instanceId);

        if (classInstance != null) {
            // Set existing data to EditText fields
            editTextDate.setText(classInstance.getDate());
            editTextTeacher.setText(classInstance.getTeacher());
            editTextAdditionalComments.setText(classInstance.getAdditionalComments());

            // Hide save button and show update/delete buttons
            buttonSave.setVisibility(View.GONE);
            buttonUpdate.setVisibility(View.VISIBLE);
            buttonDelete.setVisibility(View.VISIBLE);
        }
    }

    private void saveClassInstance() {
        String date = editTextDate.getText().toString();
        String teacher = editTextTeacher.getText().toString();
        String additionalComments = editTextAdditionalComments.getText().toString();

        if (TextUtils.isEmpty(date) || TextUtils.isEmpty(teacher)) {
            Toast.makeText(this, "Date and Teacher are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save the new class instance
        ClassInstance classInstance = new ClassInstance(courseId, date, teacher, additionalComments);
        long result = classInstanceHelper.insertClassInstance(classInstance);

        if (result != -1) {
            Toast.makeText(this, "Class Instance Saved", Toast.LENGTH_SHORT).show();
            finish(); // Close activity after saving
        } else {
            Toast.makeText(this, "Failed to Save Class Instance", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateClassInstance() {
        String date = editTextDate.getText().toString();
        String teacher = editTextTeacher.getText().toString();
        String additionalComments = editTextAdditionalComments.getText().toString();

        if (TextUtils.isEmpty(date) || TextUtils.isEmpty(teacher)) {
            Toast.makeText(this, "Date and Teacher are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update the existing class instance
        ClassInstance classInstance = new ClassInstance(courseId, date, teacher, additionalComments);
        classInstance.setId(classInstanceId); // Set the ID to update the correct record

        int result = classInstanceHelper.updateClassInstance(classInstance);

        if (result > 0) {
            Toast.makeText(this, "Class Instance Updated", Toast.LENGTH_SHORT).show();
            finish(); // Close activity after updating
        } else {
            Toast.makeText(this, "Failed to Update Class Instance", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteClassInstance() {
        boolean result = classInstanceHelper.deleteClassInstance(classInstanceId);

        if (result) {
            Toast.makeText(this, "Class Instance Deleted", Toast.LENGTH_SHORT).show();
            finish(); // Close activity after deleting
        } else {
            Toast.makeText(this, "Failed to Delete Class Instance", Toast.LENGTH_SHORT).show();
        }
    }
}
