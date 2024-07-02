package com.example.youtube.SqliteDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USER_ID = "user_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_USER_ID + " TEXT PRIMARY KEY)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    @SuppressLint("Range")
    public String getFirstUserId() {
        SQLiteDatabase db = this.getReadableDatabase();
        String userId = null;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_USER_ID},
                null, null, null, null, null, "1"); // Limit the query to 1 row
        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID));
            cursor.close();
        }
        return userId;
    }
}
