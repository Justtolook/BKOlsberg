package com.example.hlamm.bkolsberg;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.bildungsgaenge;

public class DisplayFavoritesActivity extends AppCompatActivity {
    ArrayList<CardItem> cardItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean favorites_exist = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_alle_bildungsgaenge);



        for(int i = 0; i < bildungsgaenge.size(); i++) {
            //Selecting just Favorites
            if(bildungsgaenge.get(i).isFavorit()) {
                cardItems.add(new CardItem(bildungsgaenge.get(i).getId(), bildungsgaenge.get(i).getBezeichnung(), bildungsgaenge.get(i).getDauer(), bildungsgaenge.get(i).isFavorit()));
                favorites_exist = true;
            }
        }


        RecyclerView recyclerView = findViewById(R.id.rv_list);

        CardItemAdapter cardItemAdapter = new CardItemAdapter(this, cardItems);

        recyclerView.setAdapter(cardItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Error-Message if user did not chose favorites
        if(!favorites_exist) Toast.makeText(getApplicationContext(), getString(R.string.error_no_favorites), Toast.LENGTH_LONG).show();
    }
}
