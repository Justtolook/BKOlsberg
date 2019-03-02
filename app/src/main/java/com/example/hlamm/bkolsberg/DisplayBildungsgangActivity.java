package com.example.hlamm.bkolsberg;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.bildungsgaenge;
import static com.example.hlamm.bkolsberg.MainActivity.searchBildungsgang;

public class DisplayBildungsgangActivity extends AppCompatActivity {
    private TextView tv_BenQuali, tv_Abschluss, tv_kuerzel, tv_Bezeichnung, tv_Dauer, tv_Beschreibung;
    private int id;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bildungsgang);
        Bundle b = getIntent().getExtras();
        if(b != null) id = b.getInt("id");
        index = searchBildungsgang(id);
        initViews();
        fillViews();
    }

    private void initViews() {
        tv_Abschluss = findViewById(R.id.tv_Abschluss);
        tv_BenQuali = findViewById(R.id.tv_BenQuali);
        tv_Bezeichnung = findViewById(R.id.tv_Bezeichnung);
        tv_Dauer = findViewById(R.id.tv_Dauer);
        tv_kuerzel = findViewById(R.id.tv_kuerzel);
        tv_Beschreibung = findViewById(R.id.tv_Beschreibung);
    }

    public void fillViews() {
        ArrayList<String> stringlist = new ArrayList<>();
        tv_Bezeichnung.setText(bildungsgaenge.get(index).getBezeichnung());
        tv_Dauer.setText(String.valueOf(bildungsgaenge.get(index).getDauer()));
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
            stringlist.add(bildungsgaenge.get(index).getZusatzqualifikation().get(i).getBezeichnung());
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
            stringlist.add(bildungsgaenge.get(index).getZusatzqualifikationNeeded().get(i).getBezeichnung());
        }

        for(int i = 0; i < stringlist.size(); i++) {
            string += stringlist.get(i);
            if(stringlist.size() -1 != i) string += ", ";
        }
        tv_BenQuali.setText(string);
    }
}


