package Vaja1;

public class Podjetje implements Searchable {
    private String ime;
    private String naslov;
    private String mesto;
    private String drzava;
    private int postnaStevilka;
    private String telefonskaStevilka;

    private String davcnaStevilka;
    private String maticnaStevilka;
    private boolean davcniZavezanec;

    public Podjetje(String ime, String naslov, String mesto, String drzava, int postnaStevilka,
                    String telefonskaStevilka, String davcnaStevilka, String maticnaStevilka, boolean davcniZavezanec) {
        this.ime = ime;
        this.naslov = naslov;
        this.mesto = mesto;
        this.drzava = drzava;
        this.postnaStevilka = postnaStevilka;
        this.telefonskaStevilka = telefonskaStevilka;
        this.davcnaStevilka = davcnaStevilka;
        this.maticnaStevilka = maticnaStevilka;
        this.davcniZavezanec = davcniZavezanec;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public int getPostnaStevilka() {
        return postnaStevilka;
    }

    public void setPostnaStevilka(int postnaStevilka) {
        this.postnaStevilka = postnaStevilka;
    }

    public String getTelefonskaStevilka() {
        return telefonskaStevilka;
    }

    public void setTelefonskaStevilka(String telefonskaStevilka) {
        this.telefonskaStevilka = telefonskaStevilka;
    }

    public String getDavcnaStevilka() {
        return davcnaStevilka;
    }

    public void setDavcnaStevilka(String davcnaStevilka) {
        this.davcnaStevilka = davcnaStevilka;
    }

    public String getMaticnaStevilka() {
        return maticnaStevilka;
    }

    public void setMaticnaStevilka(String maticnaStevilka) {
        this.maticnaStevilka = maticnaStevilka;
    }

    public boolean isDavcniZavezanec() {
        return davcniZavezanec;
    }

    public void setDavcniZavezanec(boolean davcniZavezanec) {
        this.davcniZavezanec = davcniZavezanec;
    }

    public boolean search(String niz) {
        if(this.ime.contains(niz) || this.naslov.contains(niz) || this.mesto.contains(niz) ||
                this.drzava.contains(niz) || String.valueOf(this.postnaStevilka).contains(niz) ||
                this.telefonskaStevilka.contains(niz) || this.davcnaStevilka.contains(niz) ||
                this.maticnaStevilka.contains(niz)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Podjetje " + ime + '\n' +
                "Naslov: " + naslov + '\n' +
                "Mesto: " + mesto + '\n' +
                "Drzava: " + drzava + '\n' +
                "Postna stevilka: " + postnaStevilka + "\n" +
                "T: " + telefonskaStevilka + '\n' +
                "Davcna stevilka: " + davcnaStevilka + '\n' +
                "Maticna stevilka: " + maticnaStevilka + '\n' +
                "Davcni zavezanec: " + (davcniZavezanec ? "Da" : "Ne");
    }
}
