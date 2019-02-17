
package com.example.hlamm.bkolsberg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseReader extends SQLiteOpenHelper {

    SQLiteDatabase db;
    String address="test";
    private static final String DATABASE_NAME = "BKOapp.db";

    public DatabaseReader(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getBildungsgang()
    {
        String query="Select * FROM Bildungsgang";
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public String getAbschluss()
    {
        String result ="";
        String query="Select * FROM Abschluss";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            int result_0=cursor.getInt(0);
            String result_1=cursor.getString(1);
            String result_2=cursor.getString(2);
            result+=String.valueOf(result_0)+" "+result_1+" "+result_2+System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        Log.d("test",result);
        return result;
    }

    public String getZusatzqualifikation()
    {
        String result ="";
        String query="Select * FROM Zusatzqualifikation";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            int result_0=cursor.getInt(0);
            String result_1=cursor.getString(1);
            result+=String.valueOf(result_0)+" "+result_1+System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        Log.d("test",result);
        return result;
    }

    public String getInteressen()
    {
        String result ="";
        String query="Select * FROM Interessen";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            int result_0=cursor.getInt(0);
            String result_1=cursor.getString(1);
            result+=String.valueOf(result_0)+" "+result_1+System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        Log.d("test",result);
        return result;
    }

    public int getUpdat()
    {
        int result=0;
        String query="Select * FROM Updat";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            result=cursor.getInt(1);
        }
        cursor.close();
        db.close();
        Log.d("test",Integer.toString(result));
        return result;
    }
}




