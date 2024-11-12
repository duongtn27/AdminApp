package com.duongtn.adminapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.mindrot.jbcrypt.BCrypt;

public class UserHelper {
    private final SQLiteDatabase db;

    public UserHelper(Context context) {
        db = DatabaseHelper.getInstance(context).getWritableDatabase();
    }

    public boolean validateUser(String email, String password) {
        Cursor cursor = db.rawQuery("SELECT user_password FROM users WHERE user_email = ?", new String[]{email});

        if (cursor.moveToFirst()) {
            String storedHashedPassword = cursor.getString(0);
            cursor.close();

            return BCrypt.checkpw(password, storedHashedPassword);
        }

        cursor.close();
        return false;
    }


}
