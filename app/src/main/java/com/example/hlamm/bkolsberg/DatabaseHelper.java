package com.example.hlamm.bkolsberg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import org.json.JSONArray;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "BKOapp.db";
    private static final String TABLE_NAME = "Abschluss";
    private static final String COl_1 = "ID";
    private static final String COl_2 = "name";
    SQLiteDatabase db;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Abschluss(ID_Abschluss INTEGER PRIMARY KEY AUTOINCREMENT,Bezeichnung TEXT,Bildungsstufe INTEGER)");
        db.execSQL("CREATE TABLE Bildungsgang(ID_Bildungsgang INTEGER PRIMARY KEY AUTOINCREMENT, Bezeichnung TEXT, Dauer INTEGER)");
        db.execSQL("CREATE TABLE benoetigt (ID_Bildungsgang INTEGER,ID_Abschluss INTEGER,ID_Zusatzqualifikation INTEGER)");
        db.execSQL("CREATE TABLE erhaelt(ID_Zusatzqualifikation INETEGER, ID_Bildungsgang INTEGER)");
        db.execSQL("CREATE TABLE erreicht(ID_Bildungsgang INTEGER,ID_Abschluss INTEGER)");
        db.execSQL("CREATE TABLE Interssen(ID_Interessen INTEGER PRIMARY KEY AUTOINCREMENT, Beschreibung TEXT)");
        db.execSQL("CREATE TABLE nuetzlichFuer(ID_Interessen INTEGER,ID_Bildungsgang INTEGER)");
        db.execSQL("CREATE TABLE Zusatzqualifikation(ID_Zusatzqualifikation INTEGER PRIMARY KEY, Bezeichnung TEXT )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void hinzufuegen(String befehl)
    {
        //JSONArray ja
        db=this.getWritableDatabase();
        db.execSQL("INSERT INTO Abschluss(Bezeichnung,Bildungsstufe) VALUES(test,test)");
    }
}
