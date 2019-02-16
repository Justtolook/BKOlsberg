package com.example.hlamm.bkolsberg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

//TODO: Interesse Klasse
public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    static boolean objectsInitialized = false;
    static ArrayList<Bildungsgang> bildungsgaenge = new ArrayList();
    static ArrayList<Abschluss> abschluesse = new ArrayList<>();
    static ArrayList<Zusatzqualifikation> quali = new ArrayList<>();
    static ArrayList<Interesse> interessen = new ArrayList<>();
    static ArrayList<Question> questions = new ArrayList<>();
    static final String SHARED_PREFS_FAV = "sharedPrefsFavorites";
    DatabaseHelper myDb;
    DatabaseReader myRd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        myDb= new DatabaseHelper(this);
        myRd= new DatabaseReader(this);
        if(myRd.getUpdat()==0)
        {
            myDb.insert_all();
        }
        else
        {
            myDb.update_exists(myRd.getUpdat());
        }



        /**
         * TODO: Inserts überarbeiten
         * Insert funktionen nur ausführen, wenn lokale Datenbank veraltet ist
         * Eventuell in den Databasehelp verschieben
         * Vor den Einfügen, erst Datensätze löschen
         */
        //Log.d("test", Integer.toString(myRd.getUpdat()));



        if(!objectsInitialized) {
            createAbschlussObjects();
            createQualiObjects();
            createBildungsgangObjects();
            createInteressenObjects();
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
        Intent intent = new Intent (this, DisplayFavoritesActivity.class);
        startActivity(intent);
    }

    public void btn_alleBildungsgaenge(View view) {
        Intent intent = new Intent(this, DisplayAlleBildungsgaenge.class);
        startActivity(intent);
    }

    public void createBildungsgangObjects() {
        myRd= new DatabaseReader(this);
        Cursor cursor=myRd.getBildungsgang();
        while(cursor.moveToNext())
        {
            int result_0=cursor.getInt(0);
            String result_1=cursor.getString(1);
            int result_2=cursor.getInt(3);
            bildungsgaenge.add(new Bildungsgang(result_0, result_1, result_2));
        }
    }

    public void createAbschlussObjects() {
        abschluesse.add(new Abschluss(0, "MOR"));
        abschluesse.add(new Abschluss(1, "OR"));

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

        answer.add(getString(R.string.q1_a1));
        answer.add(getString(R.string.q1_a2));
        answer.add(getString(R.string.q1_a3));
        questions.add(new Question(getString(R.string.q1), new ArrayList<>(answer)));
        answer.clear();

        answer.add(getString(R.string.q2_a1));
        answer.add(getString(R.string.q2_a2));
        questions.add(new Question(getString(R.string.q2), new ArrayList<>(answer)));
        answer.clear();

        answer.add(getString(R.string.q3_a1));
        answer.add(getString(R.string.q3_a2));
        answer.add(getString(R.string.q3_a3));
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
        int i;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FAV, MODE_PRIVATE);
        for(i = 0; i < bildungsgaenge.size(); i++) {
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
}
