package com.example.hlamm.bkolsberg;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BKOapp.db";
    private static final String TABLE_NAME = "Abschluss";
    JSONArray ja;
    String url=null;
    String data=null;
    SQLiteDatabase db;
    Downloader d;
    ContentValues contentValues;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Abschluss(ID_Abschluss INTEGER PRIMARY KEY AUTOINCREMENT,Bezeichnung TEXT,Bildungsstufe INTEGER)");
        db.execSQL("CREATE TABLE Bildungsgang(ID_Bildungsgang INTEGER PRIMARY KEY AUTOINCREMENT, Bezeichnung TEXT, Dauer INTEGER)");
        db.execSQL("CREATE TABLE benoetigt (ID_Bildungsgang INTEGER,ID_Abschluss INTEGER,ID_Zusatzqualifikation INTEGER)");
        db.execSQL("CREATE TABLE erhaelt(ID_Zusatzqualifikation INETEGER, ID_Bildungsgang INTEGER)");
        db.execSQL("CREATE TABLE erreicht(ID_Bildungsgang INTEGER,ID_Abschluss INTEGER)");
        db.execSQL("CREATE TABLE Interessen(ID_Interessen INTEGER PRIMARY KEY AUTOINCREMENT, Beschreibung TEXT)");
        db.execSQL("CREATE TABLE nuetzlichFuer(ID_Interessen INTEGER,ID_Bildungsgang INTEGER)");
        db.execSQL("CREATE TABLE Zusatzqualifikation(ID_Zusatzqualifikation INTEGER PRIMARY KEY, Bezeichnung TEXT )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public void insert_Abschluss()
    {
        url="https://bkoapp.cyka-bly.at/java-scripts/SelectAbschluss.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String bezeichnung=jo.getString("Bezeichnung");
                String bildungsstufe=jo.getString("Bildungsstufe");

                contentValues = new ContentValues();
                contentValues.put("Bezeichnung",bezeichnung);
                contentValues.put("Bildungsstufe",bildungsstufe);
                this.getWritableDatabase().insertOrThrow("Abschluss","",contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insert_Bildungsgang()
    {
        url="https://bkoapp.cyka-bly.at/java-scripts/SelectBildungsgang.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String bezeichnung=jo.getString("Bezeichnung");
                String dauer=jo.getString("Dauer");
                contentValues = new ContentValues();
                contentValues.put("Bezeichnung",bezeichnung);
                contentValues.put("Dauer",dauer);
                this.getWritableDatabase().insertOrThrow("Bildungsgang","",contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insert_erhaelt()
    {
        url="https://bkoapp.cyka-bly.at/java-scripts/SelectErhaelt.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String ID_Bildungsgang=jo.getString("ID_Bildungsgang");
                String ID_Zusatzqualifikation=jo.getString("ID_Zusatzqualifikation");
                contentValues = new ContentValues();
                contentValues.put("ID_Bildungsgang",ID_Bildungsgang);
                contentValues.put("ID_Zusatzqualifikation",ID_Zusatzqualifikation);
                this.getWritableDatabase().insertOrThrow("erhaelt","",contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insert_benoetigt()
    {
        url="https://bkoapp.cyka-bly.at/java-scripts/SelectBenoetigt.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String ID_Bildungsgang=jo.getString("ID_Bildungsgang");
                String ID_Zusatzqualifikation=jo.getString("ID_Zusatzqualifikation");
                String ID_Abschluss=jo.getString("ID_Abschluss");
                contentValues = new ContentValues();
                contentValues.put("ID_Bildungsgang",ID_Bildungsgang);
                contentValues.put("ID_Abschluss",ID_Abschluss);
                contentValues.put("ID_Zusatzqualifikation",ID_Zusatzqualifikation);
                this.getWritableDatabase().insertOrThrow("benoetigt","",contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insert_erreicht()
    {
        url="https://bkoapp.cyka-bly.at/java-scripts/SelectErreicht.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String ID_Bildungsgang=jo.getString("ID_Bildungsgang");
                String ID_Abschluss=jo.getString("ID_Abschluss");
                contentValues = new ContentValues();
                contentValues.put("ID_Bildungsgang",ID_Bildungsgang);
                contentValues.put("ID_Abschluss",ID_Abschluss);
                this.getWritableDatabase().insertOrThrow("erreicht","",contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insert_Interessen()
    {
        url="https://bkoapp.cyka-bly.at/java-scripts/SelectInteressen.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String beschreibung=jo.getString("Beschreibung");
                contentValues = new ContentValues();
                contentValues.put("Beschreibung",beschreibung);
                this.getWritableDatabase().insertOrThrow("Interessen","",contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insert_nuetzlichFuer()
    {
        url="https://bkoapp.cyka-bly.at/java-scripts/SelectNueztlich.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String ID_Bildungsgang=jo.getString("ID_Bildungsgang");
                String ID_Interessen=jo.getString("ID_Interessen");
                contentValues = new ContentValues();
                contentValues.put("ID_Bildungsgang",ID_Bildungsgang);
                contentValues.put("ID_Interessen",ID_Interessen);
                this.getWritableDatabase().insertOrThrow("nuetzlichFuer","",contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insert_Zusatzqualifikation()
    {
        url="https://bkoapp.cyka-bly.at/java-scripts/SelectZusatzqualifikation.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String bezeichnung=jo.getString("Bezeichnung");
                contentValues = new ContentValues();
                contentValues.put("Bezeichnung",bezeichnung);
                this.getWritableDatabase().insertOrThrow("Zusatzqualifikation","",contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
