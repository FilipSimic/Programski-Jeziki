package Vaja1;

import java.util.Date;

public class Racun {

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

    private static int counter = 1;
    private int id;

    private Date datum;
    private Artikli seznam;
    private double skupniZnesekRacuna;

    private Oseba podatkiZaNarocilo;
    private Oseba podatkiZaDostavo;
    private NacinPlacila nacinPlacila;
    private NacinDostave nacinDostave;

    private int EAN = generateEAN();

    public Racun(Date datum, Artikli seznam, Oseba podatkiZaNarocilo,
                 Oseba podatkiZaDostavo, NacinDostave nacinDostave, NacinPlacila nacinPlacila) {
        this.id = counter++;
        this.datum = datum;
        this.seznam = new Artikli(seznam);
        this.podatkiZaNarocilo = podatkiZaNarocilo;
        this.podatkiZaDostavo = podatkiZaDostavo;
        this.nacinPlacila = nacinPlacila;
        this.nacinDostave = nacinDostave;
        this.skupniZnesekRacuna = skupnaCena();
    }

    private int generateEAN() {
        return (int) Math.floor(Math.random() * (99999999 - 10000000 + 1)) + 10000000;
    }


    public double skupnaCena() {
        int price = 0;
        for(int i=0; i<seznam.getSeznam().size(); i++) {
            price += seznam.getSeznam().get(i).getCenaZDDV();
        }
        double d = (double)price/100;
        return d;
    }

    public double getSkupniZnesekRacuna() {
        return skupniZnesekRacuna;
    }

    @Override
    public String toString() {
        return "EAN Code: " + EAN + "\n" + "ID: " + id + "\n" + "Datum: " + datum.toString() +
                "\nPodatki za narocilo: \n" + podatkiZaNarocilo.toString() + "\nPodatki za dostavo: \n" + podatkiZaDostavo.toString() +
                "\nNacin dostave: " + nacinDostave + "\nNacin placila: " + nacinPlacila + "\n\n" + seznam.toString() +
                "Skupni znesek racuna: " + skupniZnesekRacuna + "\n\n";
    }
}
