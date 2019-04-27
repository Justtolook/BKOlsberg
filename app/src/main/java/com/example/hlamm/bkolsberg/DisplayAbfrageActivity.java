package com.example.hlamm.bkolsberg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.USECASE_AUSWERTUNG;
import static com.example.hlamm.bkolsberg.MainActivity.abschluesse;
import static com.example.hlamm.bkolsberg.MainActivity.interessen;
import static com.example.hlamm.bkolsberg.MainActivity.questions;

//TODO: Progressbar/counter
//TODO: Resetbutton
public class DisplayAbfrageActivity extends AppCompatActivity {
    private int ANSWER_CHECKBOX_POSITION = 0;
    private int position = 0;
    /**
     * allowDataChange:
     * It gives the allowance, whether the selected answer of the question can be changed due to
     * a checkedChanged-Event.
     * It invokes a change of selected answer from the selection reset in updateView()
     */
    private boolean allowDataChange;
    private RadioGroup radioGroup;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private TextView tv_question;
    private static ArrayList<RadioButton> radioButtons = new ArrayList<>();
    private LinearLayout ll_answer;
    private Button btn_next;
    private Button btn_back;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_abfrage);
        init();
        updateView();
    }

    /**
     * initiate variables, views and listener
     */
    public void init() {
        radioGroup = new RadioGroup(this);
        tv_question = findViewById(R.id.tv_question);
        btn_back = findViewById(R.id.btn_back);
        btn_next = findViewById(R.id.btn_next);
        btn_send = findViewById(R.id.btn_send);
        ll_answer = findViewById(R.id.ll_answer);
        for(int i = 0; i < interessen.size(); i++) {
            checkBoxes.add(new CheckBox(this));
            checkBoxes.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(allowDataChange) {
                        questions.get(position).toggleAnswerSelected(buttonView.getId(), isChecked);
                    }
                }
            });
        }

        /** btn_back */
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approveNewPosition(position - 1);

            }
        });

        /** btn_next */
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approveNewPosition(position + 1);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(allowDataChange) {
                    questions.get(position).setAnswerSelected(checkedId);
                    Log.d("Selection " + position + " changed to: ", Integer.toString(checkedId));
                }

                if(isAbfrageComplete()) btn_send.setVisibility(View.VISIBLE);
                else btn_send.setVisibility(View.INVISIBLE);
                Log.d("Position: ", Integer.toString(position));
                Log.d("User: AnswerSelected: ", Integer.toString(checkedId));
            }
        });
    }

    public void btn_send(View view) {
        Bundle b = new Bundle();
        b.putString("usecase", USECASE_AUSWERTUNG);
        Intent intent = new Intent (this, DisplayBildungsgangCardsActivity.class);
        intent.putExtras(b);
        startActivity(intent);

        //Intent intent = new Intent(this, DisplayAuswertungActivity.class);
        //startActivity(intent);
    }


    /**
     * check if new position is legit
     * @param newPosition
     */
    public void approveNewPosition(int newPosition) {
        if(newPosition >= 0 && newPosition < questions.size()) this.position = newPosition;
        updateView();
    }

    /**
     * checks if User completed the survey
     * @return completed: true // incomplete: false
     */
    public boolean isAbfrageComplete() {
        for(int i = 0; i < questions.size(); i++) {
            if(questions.get(i).getAnswerSelected() == -1 ) return false;
        }
        return true;
    }

    /**
     * Update View-Content for another question
     */
    public void updateView() {
        /**
         * Fragetext festlegen
         */
        allowDataChange = false;
        ll_answer.removeAllViews();
        tv_question.setText(questions.get(position).getQuestion());

        //Checkboxen
        if(ANSWER_CHECKBOX_POSITION == position) {
            for(int i = 0; i < checkBoxes.size(); i++) {
                checkBoxes.get(i).setText(interessen.get(i).getBezeichnung());
                checkBoxes.get(i).setId(interessen.get(i).getId());
                checkBoxes.get(i).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                ll_answer.addView(checkBoxes.get(i));
                if(questions.get(position).isAnswerSelected(checkBoxes.get(i).getId())) checkBoxes.get(i).setChecked(true);
            }

        }

        //Radiobuttons
        if(ANSWER_CHECKBOX_POSITION != position) {
            ll_answer.addView(radioGroup);
            radioGroup.clearCheck();
            radioGroup.removeAllViews();
            radioButtons.clear();


            /**
             * RadioButtons erstellen und mit Antworttext fuellen
             */
            //or(int index = 0; index < questions.get(position).getAnswerSize(); index++){
            for(int index = 0; index < abschluesse.size(); index++){
                radioButtons.add(new RadioButton(getApplicationContext()));
                radioButtons.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                radioButtons.get(index).setId(abschluesse.get(index).getId());   //soll nicht index sein, sondern die Abschluss ID
                radioButtons.get(index).setText(abschluesse.get(index).getBezeichnung());
                //radioButtons.get(index).setText(questions.get(position).getAnswer(index));
                radioGroup.addView(radioButtons.get(index));
                //Log.d("index: ", Integer.toString(index));
                //Log.d("radioButton: ", Integer.toString(radioButtons.get(index).getId()));
            }
            radioGroup.check(questions.get(position).getAnswerSelected());
            Log.d("AnswerSelected: ", Integer.toString(questions.get(position).getAnswerSelected()));
        }

        allowDataChange = true;
    }
}