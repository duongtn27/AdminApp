package com.duongtn.adminapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.duongtn.adminapp.model.ClassInstance;

import java.util.ArrayList;
import java.util.List;

public class ClassInstanceHelper {
    private final SQLiteDatabase db;

    // Constructor
    public ClassInstanceHelper(Context context) {
        db = DatabaseHelper.getInstance(context).getWritableDatabase();
    }

    // Insert a new class instance
    public long insertClassInstance(ClassInstance classInstance) {
        ContentValues values = new ContentValues();
        values.put(ClassInstance.COLUMN_COURSE_ID, classInstance.getCourseId());
        values.put(ClassInstance.COLUMN_DATE, classInstance.getDate());
        values.put(ClassInstance.COLUMN_TEACHER, classInstance.getTeacher());
        values.put(ClassInstance.COLUMN_ADDITIONAL_COMMENTS, classInstance.getAdditionalComments());

        return db.insert(ClassInstance.TABLE_NAME, null, values);
    }

    // Get all class instances for a specific course
    public List<ClassInstance> getClassInstancesForCourse(int courseId) {
        List<ClassInstance> classInstances = new ArrayList<>();
        Cursor cursor = db.query(
                ClassInstance.TABLE_NAME,
                null,
                ClassInstance.COLUMN_COURSE_ID + " = ?",
                new String[]{String.valueOf(courseId)},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ClassInstance classInstance = new ClassInstance(
                        cursor.getInt(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_COURSE_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_TEACHER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_ADDITIONAL_COMMENTS))
                );
                classInstance.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_ID)));  // Set ID after retrieval
                classInstances.add(classInstance);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return classInstances;
    }

    // Get a single class instance by its ID
    public ClassInstance getClassInstance(long id) {
        Cursor cursor = db.query(
                ClassInstance.TABLE_NAME,
                null,
                ClassInstance.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            ClassInstance classInstance = new ClassInstance(
                    cursor.getInt(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_COURSE_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_TEACHER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_ADDITIONAL_COMMENTS))
            );
            classInstance.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ClassInstance.COLUMN_ID)));
            cursor.close();
            return classInstance;
        }

        assert cursor != null;
        cursor.close();

        return null;
    }

    // Update a class instance
    public int updateClassInstance(ClassInstance classInstance) {
        ContentValues values = new ContentValues();
        values.put(ClassInstance.COLUMN_DATE, classInstance.getDate());
        values.put(ClassInstance.COLUMN_TEACHER, classInstance.getTeacher());
        values.put(ClassInstance.COLUMN_ADDITIONAL_COMMENTS, classInstance.getAdditionalComments());

        return db.update(
                ClassInstance.TABLE_NAME,
                values,
                ClassInstance.COLUMN_ID + " = ?",
                new String[]{String.valueOf(classInstance.getId())}
        );
    }

    // Delete a class instance
    public boolean deleteClassInstance(int instanceId) {
        int rowsAffected = db.delete(
                ClassInstance.TABLE_NAME,
                ClassInstance.COLUMN_ID + " = ?",
                new String[]{String.valueOf(instanceId)}
        );
        return rowsAffected > 0;
    }

    // Delete all instances for a specific course
    public void deleteClassInstancesForCourse(int courseId) {
        db.delete(
                ClassInstance.TABLE_NAME,
                ClassInstance.COLUMN_COURSE_ID + " = ?",
                new String[]{String.valueOf(courseId)}
        );
    }

    // Delete all class instances
    public void deleteAllClassInstances() {
        db.delete(ClassInstance.TABLE_NAME, null, null);
    }
}
