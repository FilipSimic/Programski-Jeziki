package Vaja1;

import java.util.ArrayList;
import java.util.List;

public class Artikli {
    private List<Artikel> seznam;
    private List<Integer> kolicina;

    public Artikli() {
        seznam = new ArrayList<Artikel>();
        kolicina = new ArrayList<Integer>();
    }
    public Artikli(final Artikli a) {
        this.seznam = new ArrayList<Artikel>();
        this.kolicina = new ArrayList<Integer>();
        for(Artikel ar : a.seznam) {
            this.seznam.add(new Artikel(ar));
        }
        for(Integer i : a.kolicina) {
            this.kolicina.add(i);
        }
    }

    public List<Artikel> getSeznam() {
        return seznam;
    }

    public void setSeznam(List<Artikel> seznam) {
        this.seznam = seznam;
    }

    public void add(Artikel a, int kolicina) {
        try {
            a.setCenaBrezDDV(a.getCenaBrezDDV()*kolicina);
            this.seznam.add(a);
            this.kolicina.add(kolicina);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void remove(int... items) {
        try {
            for (int i : items) {
                seznam.remove(i);
                kolicina.remove(i);
            }
        } catch(Exception ex) {
            throw ex;
        }
    }

    public void edit(int item, String name, int price, int kolicina) {
        try {
            seznam.get(item).setIme(name);
            seznam.get(item).setCenaBrezDDV(price*kolicina);
            this.kolicina.set(item, kolicina);
        } catch(Exception ex) {
            throw ex;
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        for(int i=0; i<seznam.size(); i++) {
            ret += "Artikel " + (i+1) + "\n" + seznam.get(i).toString() + "\nKolicina: " + kolicina.get(i) + "\n\n";
        }
        return ret;
    }


}
