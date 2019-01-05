package com.example.hlamm.bkolsberg;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DisplayHelpActivity extends AppCompatActivity {

    JSONArray ja;
    String url="https://bkoapp.cyka-bly.at/java-scripts/SelectAbschluss.php";
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_help);
         myDb= new DatabaseHelper(this);
        final ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,url,lv);

        Button b = (Button) findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EXECUTE DOWNLOAD
                //d.execute();
                String test;
                test=d.downloadData2();

                try
                {
                    //ADD THAT DATA TO JSON ARRAY FIRST
                    ja=new JSONArray(test);

                    //CREATE JO OBJ TO HOLD A SINGLE ITEM
                    JSONObject jo=null;

                    //LOOP THRU ARRAY
                    for(int i=0;i<ja.length();i++)
                    {
                        jo=ja.getJSONObject(i);
                        String Bezeichnung=jo.getString("Bezeichnung");
                        String Bildungsstufe=jo.getString("Bildungsstufe");

                    }
                    myDb.hinzufuegen("hallo");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

            }
        }

