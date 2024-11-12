package com.duongtn.adminapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.duongtn.adminapp.model.ClassInstance;
import com.duongtn.adminapp.model.User;
import com.duongtn.adminapp.model.Course;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.SetOptions;

import org.mindrot.jbcrypt.BCrypt;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "yoga_coursework";

    private static DatabaseHelper instance;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_TABLE);
        db.execSQL(Course.CREATE_TABLE);
        db.execSQL(ClassInstance.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Course.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ClassInstance.TABLE_NAME);
        onCreate(db);
    }

    // Seed admin user to SQLite
    public void seedAdmin(Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(User.TABLE_NAME, new String[]{User.COLUMN_EMAIL}, User.COLUMN_EMAIL + "=?",
                new String[]{"admin@example.com"}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return;
        }

        // Seed admin user into SQLite

        String hashedPassword = BCrypt.hashpw("admin123", BCrypt.gensalt());

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, "Admin");
        values.put(User.COLUMN_EMAIL, "admin@example.com");
        values.put(User.COLUMN_PASSWORD, hashedPassword);
        values.put(User.COLUMN_PHONE, "1234567890");
        values.put(User.COLUMN_ADDRESS, "Admin Address");
        values.put(User.COLUMN_ROLE, "admin");

        long result = db.insert(User.TABLE_NAME, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed to seed admin user", Toast.LENGTH_SHORT).show();
        }

        assert cursor != null;
        cursor.close();
    }

}
