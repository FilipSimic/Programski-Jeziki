package Vaja1;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        int slovenijaDDV = 22;

        Artikel a1 = new Artikel("Artikel1", 230, slovenijaDDV);
        Artikel a2 = new Artikel("Artikel2", 450, slovenijaDDV);
        Artikel a3 = new Artikel("Artikel3", 788, slovenijaDDV);

        Artikli sez = new Artikli();
        sez.add(a1, 1);
        sez.add(a2, 2);
        sez.add(a3, 3);

        sez.remove(0);

        sez.edit(0, "Test", 7500, 1);

        Oseba o = new Oseba("Gorazd", "Spletnik", "Dunajska 16",
                "Ljubljana","Slovenija","+3861234567",1000);

        Racun rac = new Racun(new Date(), sez, o, o, Racun.NacinDostave.POSTA_SLOVENIJE, Racun.NacinPlacila.GOTOVINA);
        Racun rac2 = new Racun(new Date(), sez, o, o, Racun.NacinDostave.DPD, Racun.NacinPlacila.KREDITNA_KARTICA);

        VodenjeRacunov v = new VodenjeRacunov(rac, rac2);
        System.out.println(v.toString());
    }
}
