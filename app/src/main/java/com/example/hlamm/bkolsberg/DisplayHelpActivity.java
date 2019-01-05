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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_help);

        final ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,url,lv);

        Button b = (Button) findViewById(R.id.button4);
        Button b2=(Button) findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EXECUTE DOWNLOAD
                //d.execute();
                String test;
                test=d.downloadData2();
                Log.d("test",test);

                /*try {
                ja = d.getArray();

                JSONObject jo = null;

                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);

                    String name = jo.getString("Bezeichnung");
                    Log.d("Test", name);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }*/

            }
        });

            }
        }

