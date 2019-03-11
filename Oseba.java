package Vaja1;

public class Oseba {
    private String ime;
    private String priimek;
    private String naslov;
    private String mesto;
    private String drzava;
    private String telefonskaStevilka;
    private int postnaStevilka;

    public Oseba(String ime, String priimek, String naslov, String mesto, String drzava, String telefonskaStevilka, int postnaStevilka) {
        this.ime = ime;
        this.priimek = priimek;
        this.naslov = naslov;
        this.mesto = mesto;
        this.drzava = drzava;
        this.telefonskaStevilka = telefonskaStevilka;
        this.postnaStevilka = postnaStevilka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
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

    public String getTelefonskaStevilka() {
        return telefonskaStevilka;
    }

    public void setTelefonskaStevilka(String telefonskaStevilka) {
        this.telefonskaStevilka = telefonskaStevilka;
    }

    public int getPostnaStevilka() {
        return postnaStevilka;
    }

    public void setPostnaStevilka(int postnaStevilka) {
        this.postnaStevilka = postnaStevilka;
    }

    @Override
    public String toString() {
        return  ime + " " + priimek + "\n" + naslov +
                "\n" + mesto + ", " + postnaStevilka + "\n" + drzava +
                "\nT: " + telefonskaStevilka + "\n";
    }
}
