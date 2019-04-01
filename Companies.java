package Vaja1;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Companies implements JsonSupport {
    private List<Podjetje> seznam;

    public Companies() {
        seznam = new ArrayList<Podjetje>();
    }
    public Companies(Podjetje... podjetje) {
        seznam = new ArrayList<Podjetje>();
        for(Podjetje p : podjetje) {
            seznam.add(p);
        }
    }

    public void add(Podjetje... podjetje) {
        for(Podjetje p : podjetje) {
            seznam.add(p);
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

    public List<Podjetje> getSeznam() {
        return seznam;
    }

    public void setSeznam(List<Podjetje> seznam) {
        this.seznam = seznam;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public void fromJson(String json) {
        Companies temp = new Gson().fromJson(json, Companies.class);
        setSeznam(temp.getSeznam());
    }

    @Override
    public String toString() {
        String temp = new String();
        for(int i=0; i<seznam.size(); i++) {
            temp += "Podjetje " + (i+1) + "\n" + seznam.get(i).toString();
        }
        return temp;
    }
}
