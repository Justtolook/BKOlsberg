package com.example.hlamm.bkolsberg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.example.hlamm.bkolsberg.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
   /// ListView lv;
    ArrayAdapter<String> adapter;
    String address="http://bkoapp.cyka-bly.at/android/fetch.php";
    InputStream is=null;
    String line=null;
    String result=null;
    String[] data;

    public static  int test=1;
    static boolean bildungsgaengeCreated = false;
    static ArrayList<Bildungsgang> bildungsgaenge = new ArrayList();
    static final String SHARED_PREFS_FAV = "sharedPrefsFavorites";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///lv=(ListView) findViewById(R.id.listView1);
        //Allow Network in main thread
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        //Retrieve
        getData();
        //ADAPTER
        //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        ///lv.setAdapter(adapter);

        //DatabaseHelper
        if(!bildungsgaengeCreated) {
            createBildungsgangObjects();
            bildungsgaengeCreated = true;
            loadDataFavorite();
        }
        updateData();

    }

    public void btn_abfrage(View view) {
        Intent intent = new Intent (this, AbfrageActivity.class);
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

    public void OnLogin(View view){
        String username = "bkoapp_admin";
        String password = "KP955JeFAeGmHZmZckesdPyEbSFgMeDn";
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

    private void getData()
    {
        try
        {
            URL url=new URL(address);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();

            con.setRequestMethod("GET");

            is=new BufferedInputStream(con.getInputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //Read is Content into a string
        try
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();

            while((br.readLine())!=null)
            {
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;

            data=new String[ja.length()];

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                data[i]=jo.getString("Name");
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
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
        editor.commit();    //writes changes - asynchronically to improve performance
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
