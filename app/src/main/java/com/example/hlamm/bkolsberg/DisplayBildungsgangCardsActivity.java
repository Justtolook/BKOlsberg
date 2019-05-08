package com.example.hlamm.bkolsberg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
                if(empty) Toast.makeText(getApplicationContext(), getString(R.string.error_no_favorites), Toast.LENGTH_LONG).show();
                break;
            case USECASE_ALLE:
                setTitle(R.string.bildungsgaenge_title);
                for(int i = 0; i < bildungsgaenge.size(); i++) {
                    cardItems.add(new CardItem(bildungsgaenge.get(i).getId(), bildungsgaenge.get(i).getBezeichnung(), bildungsgaenge.get(i).getBeschreibung(), bildungsgaenge.get(i).getDauer(), bildungsgaenge.get(i).isFavorit()));
                    empty = false;
                }
                if(empty) Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
                break;
            case USECASE_AUSWERTUNG:
                //boolean found = false;
                setTitle(R.string.auswertung_title);
                // start bei i=1  ??
                for(int i = 0; i < bildungsgaenge.size(); i++) {
                    Log.d("Auswertung_In_For-Loop", "true --- i = " + i);
                    if(

                            isInterestsSimiliar(i) //funktioniert
                            &&
                            bildungsgaenge.get(i).getAbschlussNeeded().get(0).getLevel() <= abschluesse.get(searchAbschluss(questions.get(1).getAnswerSelected())).getLevel()
                            &&
                            bildungsgaenge.get(i).getAbschluss().get(0).getId() == questions.get(2).getAnswerSelected()

                    ) {
                        /*
                        Log.d("Auswertung_In_For-Loop", "LEVEL Antwort : " + abschluesse.get(searchAbschluss(questions.get(1).getAnswerSelected())).getLevel());
                        Log.d("Auswertung_In_For-Loop", "LEVEL SUCHE   : " + bildungsgaenge.get(i).getAbschlussNeeded().get(0).getLevel());
                        Log.d("Auswertung_In_For-Loop", "LEVEL Antwort : " + questions.get(2).getAnswerSelected());
                        Log.d("Auswertung_In_For-Loop", "LEVEL SUCHE   : " + bildungsgaenge.get(i).getAbschluss().get(0).getId());
                        */



                        cardItems.add(new CardItem(bildungsgaenge.get(i).getId(), bildungsgaenge.get(i).getBezeichnung(), bildungsgaenge.get(i).getBeschreibung(), bildungsgaenge.get(i).getDauer(), bildungsgaenge.get(i).isFavorit()));
                        //Toast.makeText(getApplicationContext(), "Habe was gefunden", Toast.LENGTH_LONG).show();
                        empty = false;
                    }
                }
                if(empty) Toast.makeText(getApplicationContext(), getString(R.string.error_no_match), Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                break;
        }


        RecyclerView recyclerView = findViewById(R.id.rv_list);
        CardItemAdapter cardItemAdapter = new CardItemAdapter(this, cardItems);
        recyclerView.setAdapter(cardItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Error-Message if user did not chose favorites

    }

    public String getUsecase() {
        return usecase;
    }
}
