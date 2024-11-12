package com.duongtn.adminapp.model;

public class ClassInstance {  // Renamed class to ClassInstance
    private int id;                     // unique ID for the class instance
    private int courseId;               // ID of the associated course
    private String date;                // Date of the class instance (e.g. 17/10/2023)
    private String teacher;             // Teacher's name
    private String additionalComments;  // Optional field for additional comments

    // Constructor
    public ClassInstance(int courseId, String date, String teacher, String additionalComments) {
        this.courseId = courseId;
        this.date = date;
        this.teacher = teacher;
        this.additionalComments = additionalComments;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }

    // Constants for database table and columns
    public static final String TABLE_NAME = "class_instances";
    public static final String COLUMN_ID = "instance_id";
    public static final String COLUMN_COURSE_ID = "course_id";  // Foreign key from the courses table
    public static final String COLUMN_DATE = "instance_date";   // Date of the class
    public static final String COLUMN_TEACHER = "teacher_name"; // Name of the teacher for this instance
    public static final String COLUMN_ADDITIONAL_COMMENTS = "additional_comments"; // Optional comments

    // SQL to create the class instances table
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_COURSE_ID + " INTEGER,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_TEACHER + " TEXT,"
                    + COLUMN_ADDITIONAL_COMMENTS + " TEXT,"
                    + "FOREIGN KEY(" + COLUMN_COURSE_ID + ") REFERENCES " + Course.TABLE_NAME + "(" + Course.COLUMN_ID + ")"
                    + ")";
}
