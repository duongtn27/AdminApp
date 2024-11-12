package com.duongtn.adminapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.duongtn.adminapp.model.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseHelper {
    private final SQLiteDatabase db;

    public CourseHelper(Context context) {
        db = DatabaseHelper.getInstance(context).getWritableDatabase();
    }

    // Insert a new course
    public long insertCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put(Course.COLUMN_NAME, course.getName());
        values.put(Course.COLUMN_DAY, course.getDay());
        values.put(Course.COLUMN_TIME, course.getTime());
        values.put(Course.COLUMN_CAPACITY, course.getCapacity());
        values.put(Course.COLUMN_DURATION, course.getDuration());
        values.put(Course.COLUMN_PRICE, course.getPrice());
        values.put(Course.COLUMN_TYPE, course.getType());
        values.put(Course.COLUMN_DESCRIPTION, course.getDescription());
        values.put(Course.COLUMN_ADDITIONAL_NOTES, course.getAdditionalNotes());
        values.put(Course.COLUMN_INSTRUCTOR_NAME, course.getInstructorName());

        return db.insert(Course.TABLE_NAME, null, values);
    }

    // Get a single course by ID
    public Course getCourse(long id) {
        Cursor cursor = db.query(
                Course.TABLE_NAME,
                null,
                Course.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor !=null)
            cursor.moveToFirst();

        assert cursor != null;
        Course course = new Course();
        course.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Course.COLUMN_ID)));
        course.setName(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_NAME)));
        course.setDay(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_DAY)));
        course.setTime(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_TIME)));
        course.setCapacity(cursor.getInt(cursor.getColumnIndexOrThrow(Course.COLUMN_CAPACITY)));
        course.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(Course.COLUMN_DURATION)));
        course.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(Course.COLUMN_PRICE)));
        course.setType(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_TYPE)));
        course.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_DESCRIPTION)));
        course.setAdditionalNotes(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_ADDITIONAL_NOTES)));
        course.setInstructorName(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_INSTRUCTOR_NAME)));
        cursor.close();
        return course;

    }

    // Get all courses
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        Cursor cursor = db.query(
                Course.TABLE_NAME,
                null,
                null,
                null,
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Course.COLUMN_ID)));
                course.setName(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_NAME)));
                course.setDay(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_DAY)));
                course.setTime(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_TIME)));
                course.setCapacity(cursor.getInt(cursor.getColumnIndexOrThrow(Course.COLUMN_CAPACITY)));
                course.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(Course.COLUMN_DURATION)));
                course.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(Course.COLUMN_PRICE)));
                course.setType(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_TYPE)));
                course.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_DESCRIPTION)));
                course.setAdditionalNotes(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_ADDITIONAL_NOTES)));
                course.setInstructorName(cursor.getString(cursor.getColumnIndexOrThrow(Course.COLUMN_INSTRUCTOR_NAME)));
                courses.add(course);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return courses;
    }

    // Update a course
    public int updateCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put(Course.COLUMN_NAME, course.getName());
        values.put(Course.COLUMN_DAY, course.getDay());
        values.put(Course.COLUMN_TIME, course.getTime());
        values.put(Course.COLUMN_CAPACITY, course.getCapacity());
        values.put(Course.COLUMN_DURATION, course.getDuration());
        values.put(Course.COLUMN_PRICE, course.getPrice());
        values.put(Course.COLUMN_TYPE, course.getType());
        values.put(Course.COLUMN_DESCRIPTION, course.getDescription());
        values.put(Course.COLUMN_ADDITIONAL_NOTES, course.getAdditionalNotes());
        values.put(Course.COLUMN_INSTRUCTOR_NAME, course.getInstructorName());

        return db.update(
                Course.TABLE_NAME,
                values,
                Course.COLUMN_ID + " = ?",
                new String[]{String.valueOf(course.getId())});
    }

    // Delete a course
    public boolean deleteCourse(long id) {
        int rowsDeleted = db.delete(
                Course.TABLE_NAME,
                Course.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        return rowsDeleted > 0;
    }

    // Delete all courses
    public void deleteAllCourses() {
        db.delete(Course.TABLE_NAME, null, null);
    }
}
