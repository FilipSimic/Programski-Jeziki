package Vaja1;

public class Artikel {
    private String ime;
    private int cenaBrezDDV;
    private int cenaZDDV;
    private int davcnaStopnja;

    public Artikel(String ime, int cena, int ddv) {
        this.ime = ime;
        this.cenaBrezDDV = cena;
        this.davcnaStopnja = ddv;
        vracunajDDV();
    }

    public Artikel(final Artikel a) {
        this.ime = a.ime;
        this.cenaBrezDDV = a.cenaBrezDDV;
        this.cenaZDDV = a.cenaZDDV;
        this.davcnaStopnja = a.davcnaStopnja;
    }

    private void vracunajDDV() {
        double d = (this.cenaBrezDDV*this.davcnaStopnja)/100.0;
        int s = (int)Math.round(d);
        this.cenaZDDV = this.cenaBrezDDV + s;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getCenaBrezDDV() {
        return cenaBrezDDV;
    }

    public void setCenaBrezDDV(int cenaBrezDDV) {
        this.cenaBrezDDV = cenaBrezDDV;
        vracunajDDV();
    }

    public int getCenaZDDV() {
        return cenaZDDV;
    }

    public int getDavcnaStopnja() {
        return davcnaStopnja;
    }

    public void setDavcnaStopnja(int davcnaStopnja) {
        this.davcnaStopnja = davcnaStopnja;
    }

    @Override
    public String toString() {
        double p1 = (double)this.cenaBrezDDV/100;
        double p2 = (double)this.cenaZDDV/100;
        double p3 = (double)this.davcnaStopnja/100;
        return "Ime artikla: " + ime + "\n" + "Cena artikla brez DDV: " + p1 + "eur" +
                "\n" + "Cena artikla z " + p3 + "% DDV: " + p2 + "eur";
    }
}
