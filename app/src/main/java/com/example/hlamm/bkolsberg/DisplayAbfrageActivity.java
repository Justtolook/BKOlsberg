package com.example.hlamm.bkolsberg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.questions;

public class DisplayAbfrageActivity extends AppCompatActivity {
    /**
     * Speichert die Question-Objekte
     */
    //private ArrayList<Question> questions = new ArrayList<>();
    private int position = 0;
    private RadioGroup radioGroup;
    private TextView tv_question;
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();
    private Button btn_next;
    private Button btn_back;
    private Button btn_send;
    private int[] selection;

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
        radioGroup = findViewById(R.id.answerRadioGroup);
        tv_question = findViewById(R.id.tv_question);
        btn_back = findViewById(R.id.btn_back);
        btn_next = findViewById(R.id.btn_next);
        btn_send = findViewById(R.id.btn_send);
        selection = new int[questions.size()];

        /** btn_back */
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selection[position] = radioGroup.getCheckedRadioButtonId();
                //questions.get(position).setAnswerSelected(radioGroup.getCheckedRadioButtonId());
                approveNewPosition(position - 1);

            }
        });

        /** btn_next */
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selection[position] = radioGroup.getCheckedRadioButtonId();
                //questions.get(position).setAnswerSelected(radioGroup.getCheckedRadioButtonId());
                approveNewPosition(position + 1);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                questions.get(position).setAnswerSelected(checkedId);
                if(isAbfrageComplete()) btn_send.setVisibility(View.VISIBLE);
                else btn_send.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void btn_send(View view) {
        Intent intent = new Intent(this, DisplayAuswertungActivity.class);
        startActivity(intent);
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
        tv_question.setText(questions.get(position).getQuestion());
        radioGroup.clearCheck();
        radioGroup.removeAllViews();

        /**
         * RadioButtons erstellen und mit Antworttext fuellen
         */
        for(int index = 0; index < questions.get(position).getAnswerSize(); index++){
            radioButtons.add(new RadioButton(getApplicationContext()));
            radioButtons.get(index).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            radioButtons.get(index).setText(questions.get(position).getAnswer(index));
            radioGroup.addView(radioButtons.get(index));
        }
        //radioGroup.check(selection[position]);
        radioGroup.check(questions.get(position).getAnswerSelected());
    }


}
