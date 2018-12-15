package com.example.hlamm.bkolsberg;

/**
 * Klasse fuer das Layout-objekt "card_item.xml"
 */
public class CardItem {
    int id;
    String bezeichnung;
    float dauer;
    String beschreibung;

    public CardItem(int id, String bezeichnung, float dauer) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.dauer = dauer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDauer() {
        return dauer;
    }

    public void setDauer(float dauer) {
        this.dauer = dauer;
    }


    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
