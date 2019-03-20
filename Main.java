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

        Podjetje p1 = new Podjetje("Henkel Maribor d.o.o.", "Industrijska ulica 23", "Maribor",
                "Slovenija", 2000, "+38622222100", "58665765", "6261752000",
                true);
        Podjetje p2 = new Podjetje("Arriva Stajerska d.d.", "Meljska Cesta 97", "Maribor",
                "Slovenija", 2000, "+38623003360", "31727077", "5263433000",
                false);
        Podjetje p3 = new Podjetje("Andra d.o.o.", "Fala 40", "Fala",
                "Slovenija", 2343, "+38641652168", "74545167", "5707323000",
                false);

        Racun rac = new Racun(new Date(), sez, o, o, Racun.NacinDostave.POSTA_SLOVENIJE, Racun.NacinPlacila.GOTOVINA, p1, p1.getDavcnaStevilka());
        Racun rac2 = new Racun(new Date(), sez, o, o, Racun.NacinDostave.DPD, Racun.NacinPlacila.KREDITNA_KARTICA, p2, p2.getDavcnaStevilka());

        if(rac.search("GOTOVINA")) {
            System.out.println("Niz GOTOVINA obstaja v racunu");
        } else {
            System.out.println("Niz GOTOVINA ne obstaja v racunu");
        }

        if(Artikel.checkDigit(a1.getEAN())) {
            System.out.println("Artikel " + a1.getIme() + " ima veljavno crtno kodo");
        } else {
            System.out.println("Artikel " + a1.getIme() + " nima veljavno crtno kodo");
        }

        if(rac.isDavcniZavezanec()) {
            System.out.println("Podjetje " + rac.getIzdajatelj().getIme() + " je davcni zavezanec\n");
        } else {
            System.out.println("Podjetje " + rac.getIzdajatelj().getIme() + " ni davcni zavezanec\n");
        }

        VodenjeRacunov v = new VodenjeRacunov(rac, rac2);
        System.out.println(v.toString());
    }
}
