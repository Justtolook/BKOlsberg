package com.example.hlamm.bkolsberg;

import static com.example.hlamm.bkolsberg.MainActivity.bildungsgaenge;
/**
 * Klasse fuer das Layout-objekt "card_item.xml"
 */
public class CardItem {
    int id;
    String bezeichnung;
    float dauer;
    String beschreibung;
    boolean favorite;

    public CardItem(int id, String bezeichnung, float dauer) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.dauer = dauer;
    }

    public CardItem(int id, String bezeichnung, float dauer, boolean favorite) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.dauer = dauer;
        this.favorite = favorite;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void swapFavorite() {
        this.favorite = !this.favorite;
        bildungsgaenge.get(id).setFavorit(this.favorite);   //Aenderung wird an das Bildungsgangobjekt weitergeleitet
    }
}
