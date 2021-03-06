package com.example.hlamm.bkolsberg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

//TODO: Zurück-Button bei den DisplayBildungsgangActivity mit Abhängigkeit von Ursprungsactivity
public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    static boolean objectsInitialized = false;
    static ArrayList<Bildungsgang> bildungsgaenge = new ArrayList();
    static ArrayList<Abschluss> abschluesse = new ArrayList<>();
    static ArrayList<Zusatzqualifikation> quali = new ArrayList<>();
    static ArrayList<Interesse> interessen = new ArrayList<>();
    static ArrayList<Question> questions = new ArrayList<>();
    static final String SHARED_PREFS_FAV = "sharedPrefsFavorites";
    static final String USECASE_FAVORITE = "favorite";
    static final String USECASE_ALLE = "alle";
    static final String USECASE_AUSWERTUNG = "auswertung";
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        myDb= new DatabaseHelper(this);
        if(myDb.getUpdat()==0)
        {
            myDb.insert_all();
        }
        else
        {
            myDb.update_exists();
        }


        if(!objectsInitialized) {
            createAbschlussObjects();
            createQualiObjects();
            createInteressenObjects();
            createBildungsgangObjects();
            createQuestionObjects();
            objectsInitialized = true;
            loadDataFavorite();
        }
        updateData();

    }

    public void btn_abfrage(View view) {
        Intent intent = new Intent (this, DisplayAbfrageActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Help button */
    public void btn_help(View view) {
        Intent intent = new Intent (this, DisplayHelpActivity.class);
        startActivity(intent);
    }

    public void btn_favorites(View view) {
        Bundle b = new Bundle();
        b.putString("usecase", USECASE_FAVORITE);
        Intent intent = new Intent (this, DisplayBildungsgangCardsActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void btn_alleBildungsgaenge(View view) {
        Bundle b = new Bundle();
        b.putString("usecase", USECASE_ALLE);
        Intent intent = new Intent (this, DisplayBildungsgangCardsActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    /**
     * TODO: in den Bildungsgängen nicht ganze Abschluss (und andere) Objekte, sondern nur die ID
     */
    public void createBildungsgangObjects() {
        Cursor cursor=myDb.getBildungsgang();
        while(cursor.moveToNext())
        {
            bildungsgaenge.add(new Bildungsgang(cursor.getInt(0),       //ID
                                                cursor.getString(1),    //Bezeichnung
                                                cursor.getFloat(5),     //Dauer
                                                cursor.getString(2),    //kuerzel
                                                cursor.getString(3),    //Beschreibung
                                                Uri.parse(cursor.getString(4)),
                                                myDb.getAbschlussNoetig(cursor.getInt(0)),
                                                myDb.getZusatzqualifikationNoetig(cursor.getInt(0)),
                                                myDb.getAbschlussErhalt(cursor.getInt(0)),
                                                myDb.getZusatzqualifikationErhalt(cursor.getInt(0)),
                                                myDb.getInteressen(cursor.getInt(0))));
            //bildungsgaenge.get(bildungsgaenge.size()-1).setAbschlussNeeded();
        }
    }

    public void createAbschlussObjects() {

        Cursor cursor=myDb.getAbschluesse();
        while(cursor.moveToNext()) {
            abschluesse.add(new Abschluss(cursor.getInt(0),
                                            cursor.getString(1),
                                            cursor.getInt(2)));
        }
    }

    public void createQualiObjects() {
        quali.add(new Zusatzqualifikation(1, "Berufsausbildung"));
        quali.add(new Zusatzqualifikation(0, "QC"));
    }

    public void createInteressenObjects() {
        interessen = myDb.getInteressen();
    }

    /**
     * Question-Objekte werden mit Parameter erzeugt und in @questions gespeichert
     */
    public void createQuestionObjects() {
        ArrayList<String> answer = new ArrayList();

        /*answer.add(getString(R.string.q1_a1));
        answer.add(getString(R.string.q1_a2));
        answer.add(getString(R.string.q1_a3));
        */
        questions.add(new Question(getString(R.string.q1), new ArrayList<>(answer)));
        answer.clear();

        /*
        answer.add(getString(R.string.q2_a1));
        answer.add(getString(R.string.q2_a2));
        */
        questions.add(new Question(getString(R.string.q2), new ArrayList<>(answer)));
        answer.clear();

        /*
        answer.add(getString(R.string.q3_a1));
        answer.add(getString(R.string.q3_a2));
        answer.add(getString(R.string.q3_a3));
        */
        questions.add(new Question(getString(R.string.q3), new ArrayList<>(answer)));
        answer.clear();

    }


    /**
     * TODO: Update data straight after tapping on the star-button
     *      current Status: Updates when returning back to MainActivity
     * This function saves the as favorite marked Bildungsgaenge in a SharedPreferences-File
     */
    public void updateData() {
        int i;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FAV, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(i = 0; i < bildungsgaenge.size(); i++) {
            editor.putBoolean(String.valueOf(bildungsgaenge.get(i).getId()), bildungsgaenge.get(i).isFavorit());
        }
        editor.apply();    //writes changes - asynchronically to improve performance
    }

    /**
     * Loads which Bildungsgaenge favorites are and applies that to the specific object
     */
    public void loadDataFavorite() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FAV, MODE_PRIVATE);
        for(int i = 0; i < bildungsgaenge.size(); i++) {
            int id = bildungsgaenge.get(i).getId();
            /**
             * key: @id (id of the bildungsgang)
             */
            bildungsgaenge.get(searchBildungsgang(id)).setFavorit(sharedPreferences.getBoolean(String.valueOf(id), false));
        }

    }

    /**
     * Returns Bildungsgang-Index in bildungsgaenge-Array to given ID
     * @param id ID of Bildungsgang
     * @return Array Index of searched Bildungsgang
     */
    public static int searchBildungsgang(int id) {
        int i;
        for(i = 0; i < bildungsgaenge.size(); i++) {
            if(bildungsgaenge.get(i).getId() == id) return i;
        }
        return 99; //TODO: find better solution if bildungsgang not found
    }

    /**
     * Bekommt ID und liefert den Array-Index
     * @param id ID von dem Abschluss
     * @return  Index vom Array zum zugehörigen Abschluss
     */
    public static int searchAbschluss(int id) {
        int i;
        for(i = 0; i < abschluesse.size(); i++) {
            if(abschluesse.get(i).getId() == id) return i;
        }
        return 99; //TODO: find better solution if abschluss not found
    }

    public static boolean isInterestsSimiliar(int bildungsgangId) {
        int i;
        int j;
        /**
         * Problem
         * i = 1
         * j = 0
         */
                                        //vorher "i"
        for(i = 0; i < bildungsgaenge.get(bildungsgangId).getInteressenNeeded().size(); i++) {
            //for(j = 0; j < interessen.size(); j++) {
                Log.d("isInterestsSimiliar", "isInterestsSimiliar: COMPARISSON--------------");
                Log.d("isInterestsSimiliar", "i = " + i);
                //Log.d("isInterestsSimiliar", "j = " + j);
                            //vorher "2"
                if(questions.get(0).isAnswerSelected(bildungsgaenge.get(bildungsgangId).getInteressenNeeded().get(i).getId())) {
                    Log.d("isInterestsSimiliar", "isInterestsSimiliar: true");
                    return true;
                }
            //}
        }
        Log.d("isInterestsSimiliar", "isInterestsSimiliar: false");
        return false;
    }
}
