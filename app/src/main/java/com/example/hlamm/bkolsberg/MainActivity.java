package com.example.hlamm.bkolsberg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

i
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    static boolean bildungsgaengeCreated = false;
    static ArrayList<Bildungsgang> bildungsgaenge = new ArrayList();
    static final String SHARED_PREFS_FAV = "sharedPrefsFavorites";
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        myDb= new DatabaseHelper(this);
        myDb.insert_Abschluss();
        myDb.insert_benoetigt();
        myDb.insert_Bildungsgang();
        myDb.insert_erhaelt();
        myDb.insert_Interessen();
        myDb.insert_nuetzlichFuer();
        myDb.insert_Zusatzqualifikation();
        myDb.insert_erreicht();


        if(!bildungsgaengeCreated) {
            createBildungsgangObjects();
            bildungsgaengeCreated = true;
            loadDataFavorite();
        }
        updateData();

    }

    public void btn_abfrage(View view) {
        Intent intent = new Intent (this, DisplayAbfrageActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Help button */
    public void btn_help(View view) {
        Intent intent = new Intent (this, DisplayHelpActivity.class);
        startActivity(intent);
    }

    public void btn_favorites(View view) {
        Intent intent = new Intent (this, DisplayFavoritesActivity.class);
        startActivity(intent);
    }

    public void btn_alleBildungsgaenge(View view) {
        Intent intent = new Intent(this, DisplayAlleBildungsgaenge.class);
        startActivity(intent);
    }

    public void createBildungsgangObjects() {
        bildungsgaenge.add(new Bildungsgang(0, "ITA", 3));
        bildungsgaenge.add(new Bildungsgang(1, "PTA", 3));
        bildungsgaenge.add(new Bildungsgang(2, "PhyTA", 3));
        bildungsgaenge.add(new Bildungsgang(3, "BTA", 3));
        bildungsgaenge.add(new Bildungsgang(4, "CTA", 3));
    }


    /**
     * TODO: Update data straight after tapping on the star-button
     *      current Status: Updates when returning back to MainActivity
     * This function saves the as favorite marked Bildungsgaenge in a SharedPreferences-File
     */
    public void updateData() {
        int i;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FAV, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(i = 0; i < bildungsgaenge.size(); i++) {
            editor.putBoolean(String.valueOf(bildungsgaenge.get(i).getId()), bildungsgaenge.get(i).isFavorit());
        }
        editor.apply();    //writes changes - asynchronically to improve performance
    }

    /**
     * Loads which Bildungsgaenge favorites are and applies that to the specific object
     */
    public void loadDataFavorite() {
        int i;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FAV, MODE_PRIVATE);
        for(i = 0; i < bildungsgaenge.size(); i++) {
            int id = bildungsgaenge.get(i).getId();
            /**
             * key: @id (id of the bildungsgang)
             */
            bildungsgaenge.get(id).setFavorit(sharedPreferences.getBoolean(String.valueOf(id), false));
        }

    }
}
