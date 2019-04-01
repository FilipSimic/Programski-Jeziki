package Vaja1;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class Invoices implements JsonSupport {
    private List<Racun> seznam;

    public Invoices() {
        seznam = new ArrayList<Racun>();
    }
    public Invoices(Racun... racun) {
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

    public void setSeznam(List<Racun> seznam) {
        this.seznam = seznam;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public void fromJson(String json) {
        Invoices temp = new Gson().fromJson(json, Invoices.class);
        setSeznam(temp.getSeznam());
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
