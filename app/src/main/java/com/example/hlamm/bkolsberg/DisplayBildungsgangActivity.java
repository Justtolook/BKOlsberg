package com.example.hlamm.bkolsberg;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.bildungsgaenge;
import static com.example.hlamm.bkolsberg.MainActivity.searchBildungsgang;

public class DisplayBildungsgangActivity extends AppCompatActivity {
    private TextView tv_BenQuali, tv_Abschluss, tv_kuerzel, tv_Bezeichnung, tv_Dauer, tv_Beschreibung, tv_weblink_info;
    private ImageButton btn_weblink_info;
    private Uri uri;

    private int id;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bildungsgang);
        Bundle b = getIntent().getExtras();
        if(b != null) id = b.getInt("id");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        index = searchBildungsgang(id);
        initViews();
        fillViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home) this.finish();

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        tv_Abschluss = findViewById(R.id.tv_Abschluss);
        tv_BenQuali = findViewById(R.id.tv_BenQuali);
        tv_Bezeichnung = findViewById(R.id.tv_Bezeichnung);
        tv_Dauer = findViewById(R.id.tv_Dauer);
        tv_kuerzel = findViewById(R.id.tv_kuerzel);
        tv_Beschreibung = findViewById(R.id.tv_Beschreibung);
        tv_weblink_info = findViewById(R.id.tv_weblink_info);
        btn_weblink_info = findViewById(R.id.btn_weblink_info);

        uri = bildungsgaenge.get(index).getUri();
        tv_weblink_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weblink", "onClick: " + uri.toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btn_weblink_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weblink", "onClick: " + uri.toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }

    public void fillViews() {
        ArrayList<String> stringlist = new ArrayList<>();
        tv_Bezeichnung.setText(bildungsgaenge.get(index).getBezeichnung());
        if(bildungsgaenge.get(index).getDauer() == 1) tv_Dauer.setText(String.valueOf(bildungsgaenge.get(index).getDauer()) + " Jahr");
        else tv_Dauer.setText(String.valueOf(bildungsgaenge.get(index).getDauer()) + " Jahre");
        tv_kuerzel.setText(bildungsgaenge.get(index).getKuerzel());
        tv_Beschreibung.setText(bildungsgaenge.get(index).getBeschreibung());

        //Zu erhalten
        //Abschluesse
        String string = "";
        for(int i = 0; i < bildungsgaenge.get(index).getAbschluss().size(); i ++) {
            stringlist.add(bildungsgaenge.get(index).getAbschluss().get(i).getBezeichnung());
        }

        //Zusatzqualifikation
        for(int i = 0; i < bildungsgaenge.get(index).getZusatzqualifikation().size(); i ++) {
            if(!bildungsgaenge.get(index).getZusatzqualifikation().get(i).getBezeichnung().equals("Keine")) stringlist.add(bildungsgaenge.get(index).getZusatzqualifikation().get(i).getBezeichnung());
        }

        for(int i = 0; i < stringlist.size(); i++) {
            string += stringlist.get(i);
            if(stringlist.size() -1 != i) string += ", ";
        }
        tv_Abschluss.setText(string);

        //Voraussetzungen
        stringlist.clear();
        string = "";
        //Abschluesse benoetigt
        for(int i = 0; i < bildungsgaenge.get(index).getAbschlussNeeded().size(); i ++) {
            stringlist.add(bildungsgaenge.get(index).getAbschlussNeeded().get(i).getBezeichnung());
        }

        //Zusatzqualifikation benoetigt
        for(int i = 0; i < bildungsgaenge.get(index).getZusatzqualifikationNeeded().size(); i ++) {
            if(!bildungsgaenge.get(index).getZusatzqualifikationNeeded().get(i).getBezeichnung().equals("Keine")) stringlist.add(bildungsgaenge.get(index).getZusatzqualifikationNeeded().get(i).getBezeichnung());
        }

        for(int i = 0; i < stringlist.size(); i++) {
            string += stringlist.get(i);
            if(stringlist.size() -1 != i) string += ", ";
        }
        tv_BenQuali.setText(string);


    }
}


