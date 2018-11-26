package com.example.hlamm.bkolsberg;

import android.content.Intent;
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
    DatabaseHelper myDb;
   /// ListView lv;
    ArrayAdapter<String> adapter;
    String address="http://bkoapp.cyka-bly.at/android/fetch.php";
    InputStream is=null;
    String line=null;
    String result=null;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        ///lv=(ListView) findViewById(R.id.listView1);
        //Allow Network in main thread
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        //Retrieve
        getData();
        //ADAPTER
        //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        ///lv.setAdapter(adapter);

        //DatabaseHelper

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
        //Intent intent = new Intent (this, DisplayFavoritesActivity.class);
        //startActivity(intent);
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

}
