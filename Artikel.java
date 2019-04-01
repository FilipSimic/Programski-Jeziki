package Vaja1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Artikel implements Searchable {

    private String ime;
    private int cenaBrezDDV;
    private int cenaZDDV;
    private int davcnaStopnja;
    private String EAN = generateEAN();
    private String drzava = getCountry();

    private String oddelek;
    private int teza;

    public Artikel(String ime, int cena, int ddv) {
        if(ime.length() == 12) {
            String odd = ime.substring(0, 3);
            String id = ime.substring(3, 7);
            if(Integer.parseInt(odd) >= 200 && Integer.parseInt(odd) <= 299) {
                this.EAN = calculateDigit(ime);
                this.drzava = "Slovenija";
                obdelajEANkodo();
                double a = (this.teza*cena)/1000.0;
                this.cenaBrezDDV = (int)Math.round(a);
                this.davcnaStopnja = ddv;
                vracunajDDV();
            } else {
                this.EAN = calculateDigit(ime);
                this.ime = "Artikel"+id;
                this.drzava = getCountry();
                this.cenaBrezDDV = cena;
                this.davcnaStopnja = ddv;
                this.oddelek = "/";
                this.teza = 0;
                vracunajDDV();

            }
        } else {
            this.ime = ime;
            this.cenaBrezDDV = cena;
            this.davcnaStopnja = ddv;
            this.oddelek = "/";
            this.teza = 0;
            vracunajDDV();
        }
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

    private String generateEAN() {
        String temp = new String();
        for(int i=0; i<12; i++) {
            int t =(int)(Math.random() * 9);
            temp += t;
        }
        return temp;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEAN() {
        return EAN;
    }

    public String getDrzava() {
        return drzava;
    }

    public int getCenaBrezDDV() {
        return cenaBrezDDV;
    }

    public int getTeza() {
        return teza;
    }

    public void setTeza(int teza) {
        String odd = this.EAN.substring(0, 3);
        String id = this.EAN.substring(3, 7);
        this.EAN = calculateDigit(odd+id+String.format("%04d", teza)+"1");
        this.teza = teza;
        double a = (this.teza*cenaBrezDDV)/1000.0;
        this.cenaBrezDDV = (int)Math.round(a);
    }

    public void setCenaBrezDDV(int cenaBrezDDV) {
        if(teza != 0) {
            double a = (this.teza*cenaBrezDDV)/1000.0;
            this.cenaBrezDDV = (int)Math.round(a);
        } else {
            this.cenaBrezDDV = cenaBrezDDV;
        }
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

    public boolean search(String niz) {
        if(this.ime.contains(niz) || this.EAN.contains(niz) || String.valueOf(this.cenaBrezDDV).contains(niz) ||
            String.valueOf(this.cenaZDDV).contains(niz) || String.valueOf(this.davcnaStopnja).contains(niz)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDigit(String EAN) {
        List<Integer> nums = new ArrayList<>();
        int checkDigit = Character.getNumericValue(EAN.charAt(EAN.length()-1));
        for(int i=0; i<EAN.length()-1; i++) {
            nums.add(Character.getNumericValue(EAN.charAt(i)));
        }
        int sum = 0;
        for(int i=0; i<nums.size(); i++) {
            if(i%2 != 0) {
                nums.set(i, nums.get(i)*3);
            }
            sum += nums.get(i);
        }
        int round = (int)(Math.round(sum/10.0)*10);
        if(round<sum) {
            round += 10;
        }

        if(checkDigit == (round-sum)) {
            return true;
        } else {
            return false;
        }
    }

    public String calculateDigit(String EAN) {
        List<Integer> nums = new ArrayList<>();
        int checkDigit = Character.getNumericValue(EAN.charAt(EAN.length()-1));
        for(int i=0; i<EAN.length()-1; i++) {
            nums.add(Character.getNumericValue(EAN.charAt(i)));
        }
        int sum = 0;
        for(int i=0; i<nums.size(); i++) {
            if(i%2 != 0) {
                nums.set(i, nums.get(i)*3);
            }
            sum += nums.get(i);
        }
        int round = (int)(Math.round(sum/10.0)*10);
        if(round<sum) {
            round += 10;
        }

        if(checkDigit == (round-sum)) {
            return EAN;
        } else {
            return EAN.substring(0, EAN.length()-1) + (round-sum);
        }
    }

    private String getCountry() {
        String countryCode = this.EAN.substring(0, 3);
        List<String> countryInfo = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("drzave.txt"));
            String line = br.readLine();

            while (line != null) {
                countryInfo.add(line);
                line = br.readLine();
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        for(int i=0; i<countryInfo.size(); i++) {
            String[] info = countryInfo.get(i).split(":::");
            if(info[0].contains("-")) {
                String[] temp = info[0].split(" - ");
                for(int j = Integer.parseInt(temp[0]); j<Integer.parseInt(temp[1]); j++) {
                    if(String.format("%03d", j).equals(countryCode)) {
                        return info[1];
                    }
                }
            } else {
                if(info[0].equals(countryCode)) {
                    return info[1];
                }
            }
        }

        return "/";
    }

    public void setEAN(String EAN) {
        this.EAN = calculateDigit(EAN);
    }

    private void obdelajEANkodo() {
        String odd = this.EAN.substring(0, 3);
        String id = this.EAN.substring(3, 7);
        String t = this.EAN.substring(7, 11);

        List<String> oddelki = new ArrayList<>();
        List<String> idji = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("oddelki.txt"));
            String line = br.readLine();

            while (line != null) {
                oddelki.add(line);
                line = br.readLine();
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("idji.txt"));
            String line = br.readLine();

            while (line != null) {
                idji.add(line);
                line = br.readLine();
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        boolean valid = false;

        for(int i=0; i<oddelki.size(); i++) {
            String[] o = oddelki.get(i).split(" - ");
            if(odd.equals(o[0])) {
                if(Integer.parseInt(id) >= Integer.parseInt(o[2]) && Integer.parseInt(id) <= Integer.parseInt(o[3])) {
                    for(int j=0; j<idji.size(); j++) {
                        String[] temp = idji.get(j).split(" - ");
                        if (Integer.parseInt(id) == Integer.parseInt(temp[1])) {
                            this.oddelek = o[1];
                            this.ime = temp[0];
                            this.teza = Integer.parseInt(t);
                            valid = true;
                        }
                    }
                }
            }
        }

        if(!valid) {
            this.oddelek = "/";
            this.ime = "Artikel"+id;
            this.teza = 0;
        }
    }

    @Override
    public String toString() {
        double p1 = (double)this.cenaBrezDDV/100;
        double p2 = (double)this.cenaZDDV/100;
        double p3 = (double)this.davcnaStopnja/100;
        if(teza == 0 && oddelek.equals("/")) {
            return "EAN Code: " + EAN + "\n" + "Drzava: " + drzava + '\n' + "Ime artikla: " + ime + "\n" +
                    "Cena artikla brez DDV: " + p1 + "eur" +
                    "\n" + "Cena artikla z " + p3 + "% DDV: " + p2 + "eur";
        } else {
            return "EAN Code: " + EAN + "\n" + "Drzava: " + drzava + '\n' + "Ime artikla: " + ime + "\n" +
                    "Cena artikla brez DDV: " + p1 + "eur" +
                    "\n" + "Cena artikla z " + p3 + "% DDV: " + p2 + "eur" + "\n" + "Oddelek: " + oddelek + "\nTeza: " + teza;
        }
    }
}
