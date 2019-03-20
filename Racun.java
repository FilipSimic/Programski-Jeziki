package Vaja1;

import java.util.Date;
import java.util.UUID;

public class Racun implements Searchable {

    public enum NacinPlacila {
        GOTOVINA,
        NAKAZILO_NA_RACUN,
        KREDITNA_KARTICA,
        OBROCNO_PLACILO
    }

    public enum NacinDostave {
        BREZPLACNA_DOSTAVA,
        HITRA_DOSTAVA,
        POSTA_SLOVENIJE,
        DPD
    }

    private UUID id;
    private Date datum;
    private Artikli seznam;
    private double skupniZnesekRacuna;

    private Oseba podatkiZaNarocilo;
    private Oseba podatkiZaDostavo;
    private NacinPlacila nacinPlacila;
    private NacinDostave nacinDostave;

    private Podjetje izdajatelj;
    private String davcnaStevilkaPodjetja;

    public Racun(Date datum, Artikli seznam, Oseba podatkiZaNarocilo,
                 Oseba podatkiZaDostavo, NacinDostave nacinDostave, NacinPlacila nacinPlacila,
                 Podjetje izdajatelj, String davcnaStevilkaPodjetja) {
        this.id = UUID.randomUUID();
        this.datum = datum;
        this.seznam = new Artikli(seznam);
        this.podatkiZaNarocilo = podatkiZaNarocilo;
        this.podatkiZaDostavo = podatkiZaDostavo;
        this.nacinPlacila = nacinPlacila;
        this.nacinDostave = nacinDostave;
        this.skupniZnesekRacuna = skupnaCena();
        this.izdajatelj = izdajatelj;
        this.davcnaStevilkaPodjetja = davcnaStevilkaPodjetja;
    }

    public double skupnaCena() {
        int price = 0;
        for(int i=0; i<seznam.getSeznam().size(); i++) {
            price += seznam.getSeznam().get(i).getCenaZDDV();
        }
        double d = (double)price/100;
        return d;
    }

    public boolean isDavcniZavezanec() {
        return this.izdajatelj.isDavcniZavezanec();
    }

    public double getSkupniZnesekRacuna() {
        return skupniZnesekRacuna;
    }

    public boolean search(String niz) {
        if(this.id.toString() == niz || this.datum.toString() == niz || this.podatkiZaNarocilo.toString() == niz ||
            this.podatkiZaDostavo.toString() == niz || this.nacinDostave.toString() == niz ||
                this.nacinPlacila.toString() == niz || this.seznam.toString() == niz ||
                    String.valueOf(this.skupniZnesekRacuna) == niz) {
            return true;
        } else {
            return false;
        }
    }

    public Podjetje getIzdajatelj() {
        return izdajatelj;
    }

    public String getDavcnaStevilkaPodjetja() {
        return davcnaStevilkaPodjetja;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" + "Datum: " + datum.toString() + "\nIzdajatelj: " + izdajatelj.toString() + "\n" +
                "\nPodatki za narocilo: \n" + podatkiZaNarocilo.toString() + "\nPodatki za dostavo: \n" + podatkiZaDostavo.toString() +
                "\nNacin dostave: " + nacinDostave + "\nNacin placila: " + nacinPlacila + "\n\n" + seznam.toString() +
                "Skupni znesek racuna: " + skupniZnesekRacuna + "\n\n";
    }
}
