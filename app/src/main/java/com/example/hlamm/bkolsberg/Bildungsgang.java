package com.example.hlamm.bkolsberg;

import java.util.ArrayList;

public class Bildungsgang {
    private String bezeichnung;
    private float dauer;
    private ArrayList<Abschluss> AbschlussNeeded;
    private ArrayList<Zusatzqualifikation> ZusatzqualifikationNeeded;
    private ArrayList<Abschluss> Abschluss;
    private ArrayList<Zusatzqualifikation> Zusatzqualifikation;

    public Bildungsgang() {
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
    private String bezeichnung;

    public Abschluss() {
    }

    public Abschluss(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}

class Zusatzqualifikation {
    private String bezeichnung;

    public Zusatzqualifikation() {
    }

    public Zusatzqualifikation(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
