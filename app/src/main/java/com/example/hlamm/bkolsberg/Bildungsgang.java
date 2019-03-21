package com.example.hlamm.bkolsberg;

import java.net.URL;
import java.util.ArrayList;

public class Bildungsgang {
    private int id;
    private String bezeichnung;
    private float dauer;
    private boolean favorit;
    private String kuerzel;
    private String beschreibung;
    private URL url;
    private ArrayList<Abschluss> abschlussNeeded;
    private ArrayList<Zusatzqualifikation> zusatzqualifikationNeeded;
    private ArrayList<Abschluss> abschluss;
    private ArrayList<Zusatzqualifikation> zusatzqualifikation;
    private ArrayList<Interesse> interessen;
    //TODO: Beschreibung und URL (URI) zur Webseitenvorstellung

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
                        ArrayList<Abschluss> abschlussNeeded,
                        ArrayList<Zusatzqualifikation> zusatzqualifikationNeeded,
                        ArrayList<Abschluss> abschluss,
                        ArrayList<Zusatzqualifikation> zusatzqualifikation) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.dauer = dauer;
        this.kuerzel = kuerzel;
        this.abschlussNeeded = abschlussNeeded;
        this.zusatzqualifikationNeeded = zusatzqualifikationNeeded;
        this.abschluss = abschluss;
        this.zusatzqualifikation = zusatzqualifikation;
    }

    public Bildungsgang(int id,
                        String bezeichnung,
                        float dauer,
                        String kuerzel,
                        String beschreibung,
                        ArrayList<Abschluss> abschlussNeeded,
                        ArrayList<Zusatzqualifikation> zusatzqualifikationNeeded,
                        ArrayList<Abschluss> abschluss,
                        ArrayList<Zusatzqualifikation> zusatzqualifikation,
                        ArrayList<Interesse> interessen) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.dauer = dauer;
        this.favorit = favorit;
        this.kuerzel = kuerzel;
        this.beschreibung = beschreibung;
        //this.url = url;
        this.abschlussNeeded = abschlussNeeded;
        this.zusatzqualifikationNeeded = zusatzqualifikationNeeded;
        this.abschluss = abschluss;
        this.zusatzqualifikation = zusatzqualifikation;
        this.interessen = interessen;
        this.favorit = false;
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

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public ArrayList<Interesse> getInteressenNeeded() {
        return interessen;
    }

    public void setInteressenNeeded(ArrayList<Abschluss> InteressenNeeded) {
        this.abschlussNeeded = abschlussNeeded;
    }

    public ArrayList<Abschluss> getAbschlussNeeded() {
        return abschlussNeeded;
    }

    public void setAbschlussNeeded(ArrayList<Abschluss> abschlussNeeded) {
        this.abschlussNeeded = abschlussNeeded;
    }

    public ArrayList<Zusatzqualifikation> getZusatzqualifikationNeeded() {
        return zusatzqualifikationNeeded;
    }

    public void setZusatzqualifikationNeeded(ArrayList<Zusatzqualifikation> zusatzqualifikationNeeded) {
        this.zusatzqualifikationNeeded = zusatzqualifikationNeeded;
    }

    public ArrayList<Abschluss> getAbschluss() {
        return abschluss;
    }

    public void setAbschluss(ArrayList<Abschluss> abschluss) {
        this.abschluss = abschluss;
    }

    public ArrayList<Zusatzqualifikation> getZusatzqualifikation() {
        return zusatzqualifikation;
    }

    public void setZusatzqualifikation(ArrayList<Zusatzqualifikation> zusatzqualifikation) {
        this.zusatzqualifikation = zusatzqualifikation;
    }

    @Override
    public String toString() {
        return "Bildungsgang [ID=" + id + ", bezeichnung=" + bezeichnung + ", dauer=" + dauer + "]";
    }


}

class Abschluss {
    private int id;
    private String bezeichnung;
    private int level;

    public Abschluss() {
    }

    public Abschluss(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public Abschluss(int id, String bezeichnung, int level) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

class Zusatzqualifikation {
    private int id;
    private String bezeichnung;
    private int level;

    public Zusatzqualifikation() {
    }

    public Zusatzqualifikation(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public Zusatzqualifikation(int id, String bezeichnung, int level) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

class Interesse {
    private int id;
    private String bezeichnung;

    public Interesse() {

    }

    public Interesse(int id, String bezeichnung) {
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

    @Override
    public String toString() {
        return "Interesse[id=" + id + ", bezeichnung=" + bezeichnung + "]";
    }
}