package com.example.hlamm.bkolsberg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayAbfrageActivity extends AppCompatActivity {
    /**
     * Speichert die Question-Objekte
     */
    private ArrayList<Question> questions = new ArrayList<Question>();
    private int position = 0;
    private RadioGroup radioGroup;
    private TextView tv_question;
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();
    private Button btn_next;
    private Button btn_back;
    private int[] selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_abfrage);
        createQuestionObjects();
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
        selection = new int[questions.size()];

        /** btn_back */
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection[position] = radioGroup.getCheckedRadioButtonId();
                approveNewPosition(position - 1);

            }
        });

        /** btn_next */
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection[position] = radioGroup.getCheckedRadioButtonId();
                approveNewPosition(position + 1);
            }
        });
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
        radioGroup.check(selection[position]);
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
