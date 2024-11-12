package com.duongtn.adminapp.model;

public class User {
    // Constants for Database
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_NAME = "user_name";
    public static final String COLUMN_EMAIL = "user_email";
    public static final String COLUMN_PASSWORD = "user_password";
    public static final String COLUMN_PHONE = "user_phone";
    public static final String COLUMN_ADDRESS = "user_address";
    public static final String COLUMN_ROLE = "user_role";

    // Required fields
    private int id;                    // unique ID for the user
    private String name;               // name of the user
    private String email;              // email of the user
    private String password;           // password of the user
    private String phone;              // phone number of the user
    private String address;            // address of the user
    private String role;               // role of the user

    public User() {
    }

    // Constructor
    public User(String name, String email, String password, String phone, String address, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_EMAIL + " TEXT, "
                    + COLUMN_PASSWORD + " TEXT, "
                    + COLUMN_PHONE + " TEXT, "
                    + COLUMN_ADDRESS + " TEXT, "
                    + COLUMN_ROLE + " TEXT"
                    + ")";
}
