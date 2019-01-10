package com.example.hlamm.bkolsberg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;

public class DisplayHelpActivity extends AppCompatActivity {

    JSONArray ja;
    String url = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_help);

    }
}

