package com.duongtn.adminapp.model;

public class Course {
    // Constants for Database
    public static final String TABLE_NAME = "courses";
    public static final String COLUMN_ID = "course_id";
    public static final String COLUMN_NAME = "course_name";
    public static final String COLUMN_DAY = "course_day";
    public static final String COLUMN_TIME = "course_time";
    public static final String COLUMN_CAPACITY = "course_capacity";
    public static final String COLUMN_DURATION = "course_duration";
    public static final String COLUMN_PRICE = "course_price";
    public static final String COLUMN_TYPE = "course_type";
    public static final String COLUMN_DESCRIPTION = "course_description";
    public static final String COLUMN_ADDITIONAL_NOTES = "course_additional_notes";
    public static final String COLUMN_INSTRUCTOR_NAME = "course_instructor_name";

    // Required fields
    private int id;                    // unique ID for the course
    private String name;               // name of the course
    private String day;                // e.g., Monday, Tuesday
    private String time;               // e.g., 10:00, 11:00
    private int capacity;              // maximum number of attendees
    private int duration;              // duration in minutes, e.g., 60
    private double price;              // price per class, e.g., 10.0 for £10
    private String type;               // e.g., Flow Yoga, Aerial Yoga, Family Yoga

    // Optional fields
    private String description;        // description of the course

    // Additional field
    private String additionalNotes;    // additional notes
    private String instructorName;     // name of the instructor

    public Course() {
    }

    // Constructor
    public Course(String name, String day, String time, int capacity, int duration, double price, String type, String description, String instructorName) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.capacity = capacity;
        this.duration = duration;
        this.price = price;
        this.type = type;
        this.description = description;
        this.instructorName = instructorName;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    // Create the table
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_DAY + " TEXT,"
                    + COLUMN_TIME + " TEXT,"
                    + COLUMN_CAPACITY + " INTEGER,"
                    + COLUMN_DURATION + " INTEGER,"
                    + COLUMN_PRICE + " REAL,"
                    + COLUMN_TYPE + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_ADDITIONAL_NOTES + " TEXT,"
                    + COLUMN_INSTRUCTOR_NAME + " TEXT"
                    + ")";
}
