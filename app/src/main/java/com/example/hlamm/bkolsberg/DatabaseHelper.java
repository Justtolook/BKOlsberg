package com.example.hlamm.bkolsberg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BKOapp.db";
    private static final String TABLE_NAME = "Bildungsgang";
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
        db.execSQL("CREATE TABLE Bildungsgang(ID_Bildungsgang INTEGER PRIMARY KEY AUTOINCREMENT, Bezeichnung TEXT,Kuerzel Text,Beschreibung Text ,URL Text,Dauer INTEGER)");
        db.execSQL("CREATE TABLE benoetigt (ID_Bildungsgang INTEGER,ID_Abschluss INTEGER,ID_Zusatzqualifikation INTEGER)");
        db.execSQL("CREATE TABLE erhaelt(ID_Zusatzqualifikation INETEGER, ID_Bildungsgang INTEGER)");
        db.execSQL("CREATE TABLE erreicht(ID_Bildungsgang INTEGER,ID_Abschluss INTEGER)");
        db.execSQL("CREATE TABLE Interessen(ID_Interessen INTEGER PRIMARY KEY AUTOINCREMENT, Beschreibung TEXT)");
        db.execSQL("CREATE TABLE nuetzlichFuer(ID_Interessen INTEGER,ID_Bildungsgang INTEGER)");
        db.execSQL("CREATE TABLE Zusatzqualifikation(ID_Zusatzqualifikation INTEGER PRIMARY KEY, Bezeichnung TEXT )");
        db.execSQL("CREATE TABLE Updat(ID_updat INTEGER PRIMARY KEY AUTOINCREMENT, Wert INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int actualVersion, int newVersion) {
        Log.d("Datenbank: ","Lösch die Tabellen");
        db.execSQL("DROP TABLE IF EXISTS Abschluss");
        db.execSQL("DROP TABLE IF EXISTS Bildungsgang");
        db.execSQL("DROP TABLE IF EXISTS benoetigt");
        db.execSQL("DROP TABLE IF EXISTS erhaelt");
        db.execSQL("DROP TABLE IF EXISTS erreicht");
        db.execSQL("DROP TABLE IF EXISTS Interessen");
        db.execSQL("DROP TABLE IF EXISTS nuetzlichFuer");
        db.execSQL("DROP TABLE IF EXISTS Zusatzqualifikation");
        db.execSQL("DROP TABLE IF EXISTS Bildungsgang");
        db.execSQL("DROP TABLE IF EXISTS updat");
        onCreate(db);
        insert_all();
    }


    /**
     * Creates Bildungsgang Objects based from the data of the database and returns them in an Array
     * @return ArrayList of Bildungsgang
     */
    public ArrayList<Bildungsgang> getBildungsgaenge() {
        ArrayList<Bildungsgang> bildungsgaenge = new ArrayList<>();
        String query = "SELECT * FROM Bildungsgang";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Bildungsgang bildungsgang = null;
        if(cursor.moveToFirst()) {
            do {
                //TODO: andere Merkmale erhalten
                bildungsgang = new Bildungsgang();
                bildungsgang.setId(Integer.parseInt(cursor.getString(0)));
                bildungsgang.setBezeichnung(cursor.getString(1));
                bildungsgang.setDauer(Float.parseFloat(cursor.getString(2)));
                bildungsgaenge.add(bildungsgang);
                Log.d("getBildungsgaeng()", bildungsgang.toString());
            } while(cursor.moveToNext());
        }

        Log.d("getBildungsgaenge()", bildungsgaenge.toString());
        return bildungsgaenge;
    }

    /**
     * Creates Interesse Objects based from the data of the database and returns them in an Array
     * @return ArrayList of Interessen
     */
    public ArrayList<Interesse> getInteressen() {
        ArrayList<Interesse> interessen = new ArrayList<>();
        String query = "SELECT * FROM Interessen";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Interesse interesse = null;
        if(cursor.moveToFirst()) {
            do {
                interesse = new Interesse();
                interesse.setId(Integer.parseInt(cursor.getString(0)));
                interesse.setBezeichnung(cursor.getString(1));
                interessen.add(interesse);
                Log.d("getInteressen()", interesse.toString());
            } while(cursor.moveToNext());
        }
        return interessen;
    }

    public void insert_all()
    {
        insert_Abschluss();
        insert_benoetigt();
        insert_Bildungsgang();
        insert_erhaelt();
        insert_Interessen();
        insert_nuetzlichFuer();
        insert_Zusatzqualifikation();
        insert_erreicht();
        insert_Updat();
    }

    public void insert_Abschluss()
    {
        url="https://bkoapp.hlammert/java-scripts/SelectAbschluss.php";
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
        url="https://bkoapp.hlammert.at/java-scripts/SelectBildungsgang.php";
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
                String kuerzel=jo.getString("Kuerzel");
                String beschreibung=jo.getString("Beschreibung");
                String URL=jo.getString("URL");
                String dauer=jo.getString("Dauer");

                contentValues = new ContentValues();
                contentValues.put("Bezeichnung",bezeichnung);
                contentValues.put("Kuerzel",kuerzel);
                contentValues.put("Beschreibung",beschreibung);
                contentValues.put("URL",URL);
                contentValues.put("Dauer",dauer);
                this.getWritableDatabase().insertOrThrow("Bildungsgang","",contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insert_erhaelt()
    {
        url="https://bkoapp.hlammert/java-scripts/SelectErhaelt.php";
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
        url="https://bkoapp.hlammert.at/java-scripts/SelectBenoetigt.php";
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
        url="https://bkoapp.hlammert.at/java-scripts/SelectErreicht.php";
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
        url="https://bkoapp.hlammert.at/java-scripts/SelectInteressen.php";
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
        url="https://bkoapp.hlammert.at/java-scripts/SelectNueztlich.php";
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
        url="https://bkoapp.hlammert.at/java-scripts/SelectZusatzqualifikation.php";
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

    public void insert_Updat()
    {
        url="https://bkoapp.hlammert.at/java-scripts/updat.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            int newVersion=0;
            for(int i=0;i<ja.length();i++)
            {
                jo = ja.getJSONObject(i);
                newVersion = jo.getInt("Wert");                     //neuer Wert aus MySQL
            }
                contentValues = new ContentValues();
                contentValues.put("Wert",newVersion);
                this.getWritableDatabase().insertOrThrow("Updat","",contentValues);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void update_exists()
    {
        int actualVersion=getUpdat();
        url="https://bkoapp.hlammert.at/java-scripts/updat.php";
        d=new Downloader(url);
        data=d.downloadData();

        try
        {
            ja=new JSONArray(data);
            JSONObject jo=null;
            int newVersion=0;
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                newVersion=jo.getInt("Wert");                     //neuer Wert aus MySQL
            }
                if (!(actualVersion == newVersion)) {
                    Log.d("test",actualVersion+" "+newVersion);
                    onUpgrade(db, actualVersion, newVersion);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getUpdat()
    {
        int result=0;
        String query="Select * FROM Updat";
        //try
        //{
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            result=cursor.getInt(1);
        }
        cursor.close();
        //}
        //catch(Exception e)
        //{

        //}
        return result;
    }

    public Cursor getBildungsgang()
    {
        String query="Select * FROM Bildungsgang";
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public ArrayList<Abschluss> getAbschlussErhalt(int id)
    {
        ArrayList<Abschluss> Abschluss= new ArrayList<Abschluss>();
        String query=   "Select a.ID_Abschluss, a.Bezeichnung " +
                "FROM Abschluss a, erreicht e, Bildungsgang b " +
                "WHERE e.ID_Abschluss=a.ID_Abschluss AND e.ID_Bildungsgang=b.ID_Bildungsgang AND b.ID_Bildungsgang="+id;
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            Abschluss a = new Abschluss(cursor.getInt(0),cursor.getString(1));
            Abschluss.add(a);
        }
        cursor.close();
        return Abschluss;
    }

    public ArrayList<Abschluss> getAbschlussNoetig(int id)
    {
        ArrayList<Abschluss> Abschluss= new ArrayList<Abschluss>();
        String query=   "Select a.ID_Abschluss, a.Bezeichnung " +
                "FROM Abschluss  a, benoetigt  be, Bildungsgang  b " +
                "WHERE be.ID_Abschluss=a.ID_Abschluss AND b.ID_Bildungsgang=be.ID_Bildungsgang AND b.ID_Bildungsgang="+id;
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            Abschluss a = new Abschluss(cursor.getInt(0),cursor.getString(1));
            Abschluss.add(a);
        }
        cursor.close();
        return Abschluss;
    }

    public ArrayList<Zusatzqualifikation> getZusatzqualifikationNoetig(int id)
    {
        ArrayList<Zusatzqualifikation> Zusatzqualifikation= new ArrayList<Zusatzqualifikation>();
        String query=   "Select z.ID_Zusatzqualifikation, z.Bezeichnung " +
                "FROM Zusatzqualifikation z, benoetigt be, Bildungsgang b " +
                "WHERE be.ID_Zusatzqualifikation=z.ID_Zusatzqualifikation AND be.ID_Bildungsgang=b.ID_Bildungsgang AND b.ID_Bildungsgang="+id;
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            Zusatzqualifikation z= new Zusatzqualifikation(cursor.getInt(0),cursor.getString(1));
            Zusatzqualifikation.add(z);
        }
        cursor.close();
        return Zusatzqualifikation;
    }

    public ArrayList<Zusatzqualifikation> getZusatzqualifikationErhalt(int id)
    {
        ArrayList<Zusatzqualifikation> Zusatzqualifikation= new ArrayList<Zusatzqualifikation>();
        String query=   "Select z.ID_Zusatzqualifikation, z.Bezeichnung " +
                "FROM Zusatzqualifikation z, erhaelt e, Bildungsgang b " +
                "WHERE e.ID_Zusatzqualifikation=z.ID_Zusatzqualifikation AND e.ID_Bildungsgang=b.ID_Bildungsgang AND b.ID_Bildungsgang="+id;
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            Zusatzqualifikation z= new Zusatzqualifikation(cursor.getInt(0),cursor.getString(1));
            Zusatzqualifikation.add(z);
        }
        cursor.close();
        return Zusatzqualifikation;
    }

    public ArrayList<Interesse> getInteressen(int id)
    {
        ArrayList<Interesse> interessen= new ArrayList<Interesse>();
        String query=
                "Select i.ID_Interessen, i.Beschreibung " +
                        "FROM Interessen i, nuetzlichfuer n, Bildungsgang b " +
                        "WHERE i.ID_Interessen=n.ID_Interessen AND b.ID_Bildungsgang=n.ID_Bildungsgang AND b.ID_Bildungsgang="+id;
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            Interesse i= new Interesse(cursor.getInt(0),cursor.getString(1));
            interessen.add(i);
        }
        cursor.close();
        return interessen;
    }
}