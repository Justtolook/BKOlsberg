
package com.example.hlamm.bkolsberg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseReader extends SQLiteOpenHelper {

    SQLiteDatabase db;
    String address="test";
    private static final String DATABASE_NAME = "BKOapp.db";

    public DatabaseReader(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        db =this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}




