package Vaja1;

import java.util.*;

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
    private double skupniZnesekRacunaPopusti;

    private Oseba podatkiZaNarocilo;
    private Oseba podatkiZaDostavo;
    private NacinPlacila nacinPlacila;
    private NacinDostave nacinDostave;

    private Podjetje izdajatelj;
    private String davcnaStevilkaPodjetja;

    private List<Kupon> popusti;

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
        this.popusti = new ArrayList<>();
    }

    public double skupnaCena() {
        int price = 0;
        for(int i=0; i<seznam.getSeznam().size(); i++) {
            price += seznam.getSeznam().get(i).getCenaZDDV();
        }
        double d = (double)price/100;
        return d;
    }

    public void dodajKuponZaPopust(Kupon kupon) {
        if(!popusti.isEmpty()) {
            for(Kupon k : popusti) {
                if(k.getEAN() == kupon.getEAN()) {
                    throw new IllegalArgumentException("Kupon je ze dodan na racun");
                }
            }
            popusti.add(kupon);
        } else {
            popusti.add(kupon);
        }
    }

    public boolean isDavcniZavezanec() {
        return this.izdajatelj.isDavcniZavezanec();
    }

    public double getSkupniZnesekRacuna() {
        return skupniZnesekRacuna;
    }

    public boolean search(String niz) {
        if(this.id.toString().contains(niz) || this.datum.toString().contains(niz) || this.podatkiZaNarocilo.toString().contains(niz) ||
            this.podatkiZaDostavo.toString().contains(niz) || this.nacinDostave.toString().contains(niz) ||
                this.nacinPlacila.toString().contains(niz) || this.seznam.toString().contains(niz) ||
                    String.valueOf(this.skupniZnesekRacuna).contains(niz)) {
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

    private void obdelajPopuste() {
        for(Kupon k : popusti) {
            if(k.getTip() == Kupon.Tip.CELOTNI_NAKUP || k.getTip() == Kupon.Tip.SPLETNI_NAKUP) {
                double odstotek = (skupniZnesekRacuna*k.getProcent())/100.0;
                skupniZnesekRacunaPopusti = skupniZnesekRacuna-odstotek;
            } else if(k.getTip() == Kupon.Tip.NAJDRAZJI_IZDELEK) {
                Artikel najdrazji = Collections.max(seznam.getSeznam(), new Comparator<Artikel>(){
                    public int compare(Artikel x, Artikel y) {
                        return Integer.compare(x.getCenaZDDV(), y.getCenaZDDV());
                    }
                });
                najdrazji.setCenaZDDV(najdrazji.getCenaZDDV()-((najdrazji.getCenaZDDV()*k.getProcent())/100));
                skupniZnesekRacunaPopusti = skupnaCena();
            } else if(k.getTip() == Kupon.Tip.NAJDRAZJI_BOGO) {
                Artikel najdrazji = Collections.max(seznam.getSeznam(), new Comparator<Artikel>(){
                    public int compare(Artikel x, Artikel y) {
                        return Integer.compare(x.getCenaZDDV(), y.getCenaZDDV());
                    }
                });
                seznam.add(najdrazji, 1);
            }
        }
    }

    @Override
    public String toString() {
        String dav;
        if(isDavcniZavezanec()) {
            dav = "DA("+davcnaStevilkaPodjetja+")";
        } else {
            dav = "NE";
        }
        if(!popusti.isEmpty()) {
            obdelajPopuste();
            String temp = "Kuponi\n";
            for(Kupon k : popusti) {
                temp += k.toString() + "\n";
            }
            return "ID: " + id + "\n" + "Datum: " + datum.toString() + "\nIzdajatelj: " + izdajatelj.toString() + "\n" +
                    "Podjetje je davcni zavezanec: " + dav + "\n" +
                    "\nPodatki za narocilo: \n" + podatkiZaNarocilo.toString() + "\nPodatki za dostavo: \n" + podatkiZaDostavo.toString() +
                    "\nNacin dostave: " + nacinDostave + "\nNacin placila: " + nacinPlacila + "\n\n" + seznam.toString() + temp + "\n" +
                    "Skupni znesek racuna: " + skupniZnesekRacuna + "\n" + "Skupni znesek z popustom: " + String.format("%.2f", skupniZnesekRacunaPopusti) + "\n\n";
        } else {
            return "ID: " + id + "\n" + "Datum: " + datum.toString() + "\nIzdajatelj: " + izdajatelj.toString() + "\n" +
                    "Podjetje je davcni zavezanec: " + dav + "\n" +
                    "\nPodatki za narocilo: \n" + podatkiZaNarocilo.toString() + "\nPodatki za dostavo: \n" + podatkiZaDostavo.toString() +
                    "\nNacin dostave: " + nacinDostave + "\nNacin placila: " + nacinPlacila + "\n\n" + seznam.toString() +
                    "Skupni znesek racuna: " + skupniZnesekRacuna + "\n\n";
        }
    }
}
