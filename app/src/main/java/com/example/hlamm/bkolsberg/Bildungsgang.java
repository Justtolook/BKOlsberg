package com.example.hlamm.bkolsberg;

import java.util.ArrayList;

public class Bildungsgang {
    private int id;
    private String bezeichnung;
    private float dauer;
    private boolean favorit;
    private String kuerzel; //TODO get() set() konstruktor updaten
    private ArrayList<Abschluss> AbschlussNeeded;
    private ArrayList<Zusatzqualifikation> ZusatzqualifikationNeeded;
    private ArrayList<Abschluss> Abschluss;
    private ArrayList<Zusatzqualifikation> Zusatzqualifikation;

    public Bildungsgang() {
    }

    /**
     * Konstruktor für Bildungsgang
     *  param favorit wird automatisch auf 'false' gesetzt
     * @param bezeichnung
     * @param dauer
     */
    public Bildungsgang(int id, String bezeichnung, float dauer) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.dauer = dauer;
        this.favorit = false;
    }

    public Bildungsgang(int id,
                        String bezeichnung,
                        float dauer,
                        String kuerzel,
                        ArrayList<com.example.hlamm.bkolsberg.Abschluss> abschlussNeeded,
                        ArrayList<com.example.hlamm.bkolsberg.Zusatzqualifikation> zusatzqualifikationNeeded,
                        ArrayList<com.example.hlamm.bkolsberg.Abschluss> abschluss,
                        ArrayList<com.example.hlamm.bkolsberg.Zusatzqualifikation> zusatzqualifikation) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.dauer = dauer;
        this.kuerzel = kuerzel;
        AbschlussNeeded = abschlussNeeded;
        ZusatzqualifikationNeeded = zusatzqualifikationNeeded;
        Abschluss = abschluss;
        Zusatzqualifikation = zusatzqualifikation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public float getDauer() {
        return dauer;
    }

    public void setDauer(float dauer) {
        this.dauer = dauer;
    }

    public boolean isFavorit() {
        return favorit;
    }

    public void setFavorit(boolean favorit) {
        this.favorit = favorit;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public ArrayList<Abschluss> getAbschlussNeeded() {
        return AbschlussNeeded;
    }

    public void setAbschlussNeeded(ArrayList<Abschluss> abschlussNeeded) {
        AbschlussNeeded = abschlussNeeded;
    }

    public ArrayList<Zusatzqualifikation> getZusatzqualifikationNeeded() {
        return ZusatzqualifikationNeeded;
    }

    public void setZusatzqualifikationNeeded(ArrayList<Zusatzqualifikation> zusatzqualifikationNeeded) {
        ZusatzqualifikationNeeded = zusatzqualifikationNeeded;
    }

    public ArrayList<Abschluss> getAbschluss() {
        return Abschluss;
    }

    public void setAbschluss(ArrayList<Abschluss> abschluss) {
        Abschluss = abschluss;
    }

    public ArrayList<Zusatzqualifikation> getZusatzqualifikation() {
        return Zusatzqualifikation;
    }

    public void setZusatzqualifikation(ArrayList<Zusatzqualifikation> zusatzqualifikation) {
        Zusatzqualifikation = zusatzqualifikation;
    }


}

class Abschluss {
    private int id;
    private String bezeichnung;

    public Abschluss() {
    }

    public Abschluss(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}

class Zusatzqualifikation {
    private int id;
    private String bezeichnung;

    public Zusatzqualifikation() {
    }

    public Zusatzqualifikation(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
