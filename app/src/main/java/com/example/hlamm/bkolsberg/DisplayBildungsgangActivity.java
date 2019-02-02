package com.example.hlamm.bkolsberg;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.bildungsgaenge;

public class DisplayBildungsgangActivity extends AppCompatActivity {
    private TextView tv_BenQuali, tv_Abschluss, tv_kuerzel, tv_Bezeichnung, tv_Dauer;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bildungsgang);
        initViews();
    }

    private void initViews() {
        tv_Abschluss = findViewById(R.id.tv_Abschluss);
        tv_BenQuali = findViewById(R.id.tv_BenQuali);
        tv_Bezeichnung = findViewById(R.id.tv_Bezeichnung);
        tv_Dauer = findViewById(R.id.tv_Dauer);
        tv_kuerzel = findViewById(R.id.tv_kuerzel);
    }

    public void fillViews() {

        tv_Bezeichnung.setText(bildungsgaenge.get(id).getBezeichnung());
        tv_Dauer.setText(String.valueOf(bildungsgaenge.get(id).getDauer()));

        //Abschluesse
        String string = "";
        for(int i = 0; i < bildungsgaenge.get(id).getAbschluss().size(); i ++) {
            string += bildungsgaenge.get(id).getAbschluss().get(i).getBezeichnung();
            if(bildungsgaenge.get(id).getAbschluss().size() -1 == i) string += ", ";
        }
        tv_Abschluss.setText(string);

        //Zusatzqualifikation
        string = "";
        for(int i = 0; i < bildungsgaenge.get(id).getZusatzqualifikation().size(); i ++) {
            string += bildungsgaenge.get(id).getZusatzqualifikation().get(i).getBezeichnung();
            if(bildungsgaenge.get(id).getZusatzqualifikation().size() -1 == i) string += ", ";
        }
        //tv_Abschluss.setText(string);

        //Abschluesse benoetigt
        string = "";
        for(int i = 0; i < bildungsgaenge.get(id).getAbschlussNeeded().size(); i ++) {
            string += bildungsgaenge.get(id).getAbschlussNeeded().get(i).getBezeichnung();
            if(bildungsgaenge.get(id).getAbschlussNeeded().size() -1 == i) string += ", ";
        }
        tv_BenQuali.setText(string);

        //Zusatzqualifikation benoetigt
        string = "";
        for(int i = 0; i < bildungsgaenge.get(id).getZusatzqualifikationNeeded().size(); i ++) {
            string += bildungsgaenge.get(id).getZusatzqualifikationNeeded().get(i).getBezeichnung();
            if(bildungsgaenge.get(id).getZusatzqualifikationNeeded().size() -1 == i) string += ", ";
        }
        //tv_Abschluss.setText(string);

    }


/*
    public class ListViewAbschluss extends ListActivity {
        ArrayList<String> listItems = new ArrayList<>();
        ArrayAdapter<String> adapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_bildungsgang);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
            setListAdapter(adapter);
            addItems();
        }

        /**
         * Add Items to ListView

        public void addItems() {
            //Add Items here:
            listItems.add("Abitur");
            listItems.add("Berufsabschluss");

            //update ListView
            adapter.notifyDataSetChanged();
        }
    }*/
}


