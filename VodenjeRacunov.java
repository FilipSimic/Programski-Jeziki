package Vaja1;

import java.util.ArrayList;
import java.util.List;

public class VodenjeRacunov {
    private List<Racun> seznam;

    public VodenjeRacunov() {
        seznam = new ArrayList<Racun>();
    }
    public VodenjeRacunov(Racun... racun) {
        seznam = new ArrayList<Racun>();
        for(Racun r : racun) {
            seznam.add(r);
        }
    }

    public void add(Racun... racun) {
        for(Racun r : racun) {
            seznam.add(r);
        }
    }

    public void remove(int... index) {
        try {
            for(int i : index) {
                seznam.remove(i);
            }
        } catch(Exception ex) {
            throw ex;
        }
    }

    public List<Racun> getSeznam() {
        return seznam;
    }

    @Override
    public String toString() {
        String temp = new String();
        for(int i=0; i<seznam.size(); i++) {
            temp += "Racun " + (i+1) + "\n" + seznam.get(i).toString();
        }
        return temp;
    }
}
