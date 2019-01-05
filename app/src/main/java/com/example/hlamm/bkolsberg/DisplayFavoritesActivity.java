package com.example.hlamm.bkolsberg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.bildungsgaenge;

public class DisplayFavoritesActivity extends AppCompatActivity {
    ArrayList<CardItem> cardItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_alle_bildungsgaenge);



        for(int i = 0; i < bildungsgaenge.size(); i++) {
            //Selecting just Favorites
            if(bildungsgaenge.get(i).isFavorit()) cardItems.add(new CardItem(bildungsgaenge.get(i).getId(), bildungsgaenge.get(i).getBezeichnung(), bildungsgaenge.get(i).getDauer(), bildungsgaenge.get(i).isFavorit()));
        }


        RecyclerView recyclerView = findViewById(R.id.rv_list);

        CardItemAdapter cardItemAdapter = new CardItemAdapter(this, cardItems);

        recyclerView.setAdapter(cardItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * TODO: Show details about the selected Bildungsgang
     * @param view
     */
    public void btn_details(View view) {
        Intent intent = new Intent(this, DisplayBildungsgangActivity.class);
        startActivity(intent);
    }
}
