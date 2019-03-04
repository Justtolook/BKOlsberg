package com.example.hlamm.bkolsberg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.USECASE_ALLE;
import static com.example.hlamm.bkolsberg.MainActivity.USECASE_AUSWERTUNG;
import static com.example.hlamm.bkolsberg.MainActivity.USECASE_FAVORITE;
import static com.example.hlamm.bkolsberg.MainActivity.abschluesse;
import static com.example.hlamm.bkolsberg.MainActivity.bildungsgaenge;
import static com.example.hlamm.bkolsberg.MainActivity.isInterestsSimiliar;
import static com.example.hlamm.bkolsberg.MainActivity.questions;
import static com.example.hlamm.bkolsberg.MainActivity.searchAbschluss;

public class DisplayBildungsgangCardsActivity extends AppCompatActivity {
    ArrayList<CardItem> cardItems = new ArrayList<>();
    private String usecase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bildungsgang_cards);
        Bundle b = getIntent().getExtras();
        if(b != null) usecase = b.getString("usecase");
        boolean empty = true;
        switch(usecase) {
            case USECASE_FAVORITE:
                setTitle(R.string.favorites_title);
                for(int i = 0; i < bildungsgaenge.size(); i++) {
                    //Selecting just Favorites
                    if(bildungsgaenge.get(i).isFavorit()) {
                        cardItems.add(new CardItem(bildungsgaenge.get(i).getId(), bildungsgaenge.get(i).getBezeichnung(), bildungsgaenge.get(i).getBeschreibung(), bildungsgaenge.get(i).getDauer(), bildungsgaenge.get(i).isFavorit()));
                        empty = false;
                    }
                }
                break;
            case USECASE_ALLE:
                setTitle(R.string.bildungsgaenge_title);
                for(int i = 0; i < bildungsgaenge.size(); i++) {
                    cardItems.add(new CardItem(bildungsgaenge.get(i).getId(), bildungsgaenge.get(i).getBezeichnung(), bildungsgaenge.get(i).getBeschreibung(), bildungsgaenge.get(i).getDauer(), bildungsgaenge.get(i).isFavorit()));
                    empty = false;
                }
                break;
            case USECASE_AUSWERTUNG:
                boolean found = false;
                setTitle(R.string.text_Auswertung);
                for(int i = 0; i < bildungsgaenge.size(); i++) {
                    if(
                            bildungsgaenge.get(i).getAbschlussNeeded().get(0).getLevel() <= abschluesse.get(searchAbschluss(questions.get(1).getAnswerSelected())).getLevel() &&
                                    bildungsgaenge.get(i).getAbschluss().get(0).getId() == questions.get(2).getAnswerSelected() && isInterestsSimiliar(i)
                    ) {
                        cardItems.add(new CardItem(bildungsgaenge.get(i).getId(), bildungsgaenge.get(i).getBezeichnung(), bildungsgaenge.get(i).getBeschreibung(), bildungsgaenge.get(i).getDauer(), bildungsgaenge.get(i).isFavorit()));
                    }
                }
            default:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                break;
        }


        RecyclerView recyclerView = findViewById(R.id.rv_list);
        CardItemAdapter cardItemAdapter = new CardItemAdapter(this, cardItems);
        recyclerView.setAdapter(cardItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Error-Message if user did not chose favorites
        if(empty) Toast.makeText(getApplicationContext(), getString(R.string.error_no_favorites), Toast.LENGTH_LONG).show();
    }
}
