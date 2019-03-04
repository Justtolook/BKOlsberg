package com.example.hlamm.bkolsberg;

import java.util.ArrayList;

import static com.example.hlamm.bkolsberg.MainActivity.interessen;

public class Question {
    private String question;
    private ArrayList<String> answer;
    private int[] answersSelected = new int[interessen.size()];

    /**
     * Standardkonstruktor
     */
    public  Question() {}

    /**
     * Konstruktor
     * @param question
     * @param answer
     */
    public Question(String question, ArrayList<String> answer) {
        this.question = question;
        this.answer = answer;
        for(int i = 0; i < answersSelected.length; i++) {
            answersSelected[i] = -1; //none is selected
        }
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    /**
     * Nur bei RadioGroup verwenden!
     * Setzt die gewählte Antwort.
     * Dabei wird nur der Index der Antwort gespeichert!
     * @param answerSelected
     */
    public void setAnswerSelected(int answerSelected) {
        this.answersSelected[0] = answerSelected;
    }

    /**
     * Ändert die Auswahl einer Antwortmöglichkeit bei Checkboxen.
     * @param id            ID der Interesse
     * @param isChecked     Neuer Auswahlzustand
     */
    public void toggleAnswerSelected(int id, boolean isChecked) {
        boolean finished = false;
        boolean error = false;
        int i = 0;
        if(isChecked) {
            do{
                if(-1 == answersSelected[i]) {
                    answersSelected[i] = id;
                    finished = true;
                }
                else if(i >= answersSelected.length) error = true;
                i++;
            }while(!finished && !error);
        }
        else {
            do{
                if(id == answersSelected[i]) {
                    answersSelected[i] = -1;
                    finished = true;
                }
                else if(i >= answersSelected.length) error = true;
                i++;
            }while(!finished && !error);
        }
    }

    /**
     * Gibt die Frage als String zurück
     * @return
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * Gibt das Element an dem angegeben Index als String zurück
     * @param index
     * @return -> antwort als String
     */
    public String getAnswer(int index) {
        return (String) answer.get(index);
    }

    /**
     * Nur bei RadioGroup verwenden!
     * Gibt den Index der gewählten Antwort zurück
     * @return
     */
    public int getAnswerSelected() {
        return answersSelected[0];
    }

    /**
     * Gibt die Anzahl der möglichen Antworten zurück
     * @return int
     */
    public int getAnswerSize() {
        return answer.size();
    }

    public boolean isAnswerSelected(int id) {
        for(int i = 0; i < interessen.size(); i++) {
            if(id == answersSelected[i]) return true;
        }
        return false;
    }

}
