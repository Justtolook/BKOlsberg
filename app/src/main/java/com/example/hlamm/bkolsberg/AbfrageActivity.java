package com.example.hlamm.bkolsberg;

import android.app.ActionBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



import java.util.ArrayList;

public class AbfrageActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * Speichert die Question-Objekte
     */
    private static ArrayList<Question> questions = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createQuestionObjects();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abfrage);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_abfrage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_abfrage, container, false);
            RadioGroup radioGroup = rootView.findViewById(R.id.answerRadioGroup);
            TextView tv_question = rootView.findViewById(R.id.tv_question);
            ArrayList<RadioButton> radioButtons = new ArrayList<>();

            /**
             * Fragetext festlegen
             */
            tv_question.setText(questions.get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion());

            /**
             * RadioButtons erstellen und mit Antworttext fuellen
             */
            for(int index = 0; index < questions.get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswerSize(); index++){
                radioButtons.add(new RadioButton(this.getContext()));
                radioButtons.get(index).setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                radioButtons.get(index).setText(questions.get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer(index));
                radioGroup.addView(radioButtons.get(index));
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    /**
     * Question-Objekte werden mit Parameter erzeugt und in @questions gespeichert
     */
    public void createQuestionObjects() {
        ArrayList<String> answer = new ArrayList();

        answer.add(getString(R.string.q1_a1));
        answer.add(getString(R.string.q1_a2));
        answer.add(getString(R.string.q1_a3));
        questions.add(new Question(getString(R.string.q1), new ArrayList<String>(answer)));
        answer.clear();

        answer.add(getString(R.string.q2_a1));
        answer.add(getString(R.string.q2_a2));
        questions.add(new Question(getString(R.string.q2), new ArrayList<String>(answer)));
        answer.clear();

        answer.add(getString(R.string.q3_a1));
        answer.add(getString(R.string.q3_a2));
        answer.add(getString(R.string.q3_a3));
        questions.add(new Question(getString(R.string.q3), new ArrayList<String>(answer)));
        answer.clear();

    }
}
