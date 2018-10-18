package com.example.hlamm.bkolsberg;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answer;
    private int answerSelected;

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
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    /**
     * Setzt die gewählte Antwort.
     * Dabei wird nur der Index der Antwort gespeichert!
     * @param answerSelected
     */
    public void setAnswerSelected(int answerSelected) {
        this.answerSelected = answerSelected;
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
     * Gibt den Index der gewählten Antwort zurück
     * @return
     */
    public int getAnswerSelected() {
        return answerSelected;
    }

    /**
     * Gibt die Anzahl der möglichen Antworten zurück
     * @return int
     */
    public int getAnswerSize() {
        return answer.size();
    }


}
