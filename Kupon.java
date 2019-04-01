package Vaja1;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Kupon {

    public enum Tip {
        CELOTNI_NAKUP,
        NAJDRAZJI_IZDELEK,
        SPLETNI_NAKUP,
        NAJDRAZJI_BOGO
    }

    private int procent;
    private Date veljavnost;
    private Tip tip;
    private String EAN;

    public Kupon(int procent, Date veljavnost, Tip tip) {
        if(procent > 0 && procent < 100) {
            this.procent = procent;
            this.veljavnost = veljavnost;
            this.tip = tip;
            this.EAN = generateEan();
        } else {
            throw new IllegalArgumentException("Popust mora bit med 0 in 100");
        }
    }

    private String generateEan() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        String strDate = dateFormat.format(this.veljavnost);
        String temp = "99"+String.format("%02d", this.procent)+strDate+this.tip.ordinal()+"1";
        temp = Artikel.calculateDigit(temp);
        return temp;
    }

    @Override
    public String toString() {
        if(tip == Tip.NAJDRAZJI_BOGO) {
            return "Veljavnost kupona: " + veljavnost.toString() + '\n' +
                    "Tip kupona: " + tip.toString() + "\nEAN koda: " + EAN + "\n";
        } else {
            return "Procent kupona: " + procent + "%\n" + "Veljavnost kupona: " + veljavnost.toString() + '\n' +
                    "Tip kupona: " + tip.toString() + "\nEAN koda: " + EAN + "\n";
        }
    }

    public int getProcent() {
        return procent;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    public Date getVeljavnost() {
        return veljavnost;
    }

    public void setVeljavnost(Date veljavnost) {
        this.veljavnost = veljavnost;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }
}
