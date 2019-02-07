package com.example.hlamm.bkolsberg;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;



import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.bildungsgaenge;

public class DisplayAlleBildungsgaenge extends AppCompatActivity {
    ArrayList<CardItem> cardItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_alle_bildungsgaenge);



        for(int iC = 0; iC < bildungsgaenge.size(); iC++) {
            cardItems.add(new CardItem(bildungsgaenge.get(iC).getId(), bildungsgaenge.get(iC).getBezeichnung(), bildungsgaenge.get(iC).getDauer(), bildungsgaenge.get(iC).isFavorit()));
        }


        RecyclerView recyclerView = findViewById(R.id.rv_list);

        CardItemAdapter cardItemAdapter = new CardItemAdapter(this, cardItems);

        recyclerView.setAdapter(cardItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
