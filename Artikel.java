package Vaja1;

import java.util.ArrayList;
import java.util.List;

public class Artikel implements Searchable {
    private String ime;
    private int cenaBrezDDV;
    private int cenaZDDV;
    private int davcnaStopnja;
    private String EAN = generateEAN();
    private String drzava = getCountry();

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

    private String generateEAN() {
        String temp = new String();
        for(int i=0; i<13; i++) {
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

    public boolean search(String niz) {
        if(this.ime == niz || this.EAN == niz || String.valueOf(this.cenaBrezDDV) == niz ||
            String.valueOf(this.cenaZDDV) == niz || String.valueOf(this.davcnaStopnja) == niz) {
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

    private String getCountry() {
        String countryCode = this.EAN.substring(0, 3);
        for(int i=0; i<20; i++) {
            if(i<=9) {
                if(("00"+String.valueOf(i)).equals(countryCode)) {
                    return "GS1 ZDA";
                }
            } else {
                if(("0"+String.valueOf(i)).equals(countryCode)) {
                    return "GS1 ZDA";
                }
            }
        }

        for(int i=30; i<40; i++) {
            if(("0"+String.valueOf(i)).equals(countryCode)) {
                return "GS1 ZDA";
            }
        }

        for(int i=60; i<140; i++) {
            if(i<=99) {
                if(("0"+String.valueOf(i)).equals(countryCode)) {
                    return "GS1 ZDA";
                }
            } else {
                if((String.valueOf(i)).equals(countryCode)) {
                    return "GS1 ZDA";
                }
            }
        }

        for(int i=300; i<380; i++) {
            if((String.valueOf(i)).equals(countryCode)) {
                return "GS1 Francija";
            }
        }

        if("380".equals(countryCode)) {
            return "GS1 Bulgarija";
        }

        if("383".equals(countryCode)) {
            return "GS1 Slovenija";
        }

        if("385".equals(countryCode)) {
            return "GS1 Hrvaska";
        }

        if("387".equals(countryCode)) {
            return "GS1 BIH";
        }

        if("389".equals(countryCode)) {
            return "GS1 Monte Negro";
        }

        for(int i=400; i<441; i++) {
            if((String.valueOf(i)).equals(countryCode)) {
                return "GS1 Nemcija";
            }
        }

        for(int i=460; i<470; i++) {
            if((String.valueOf(i)).equals(countryCode)) {
                return "GS1 Rusija";
            }
        }

        if("470".equals(countryCode)) {
            return "GS1 Kirgistan";
        }

        if("471".equals(countryCode)) {
            return "GS1 Taivan";
        }

        if("474".equals(countryCode)) {
            return "GS1 Estonija";
        }

        if("475".equals(countryCode)) {
            return "GS1 Latvija";
        }

        if("476".equals(countryCode)) {
            return "GS1 Azerbejdzan";
        }

        if("477".equals(countryCode)) {
            return "GS1 Litva";
        }

        if("478".equals(countryCode)) {
            return "GS1 Uzbekistan";
        }

        if("479".equals(countryCode)) {
            return "GS1 Sri Lanka";
        }

        if("480".equals(countryCode)) {
            return "GS1 Filipini";
        }

        if("481".equals(countryCode)) {
            return "GS1 Belorusija";
        }

        if("482".equals(countryCode)) {
            return "GS1 Ukrajina";
        }

        if("484".equals(countryCode)) {
            return "GS1 Moldavija";
        }

        if("485".equals(countryCode)) {
            return "GS1 Armenija";
        }

        if("486".equals(countryCode)) {
            return "GS1 Gruzija";
        }

        if("487".equals(countryCode)) {
            return "GS1 Kazahstan";
        }

        if("489".equals(countryCode)) {
            return "GS1 Hong Kong";
        }

        for(int i=450; i<460; i++) {
            if((String.valueOf(i)).equals(countryCode)) {
                return "GS1 Japonska";
            }
        }

        for(int i=490; i<500; i++) {
            if((String.valueOf(i)).equals(countryCode)) {
                return "GS1 Japonska";
            }
        }

        for(int i=500; i<510; i++) {
            if((String.valueOf(i)).equals(countryCode)) {
                return "GS1 VB";
            }
        }

        if("520".equals(countryCode)) {
            return "GS1 Grcija";
        }

        if("528".equals(countryCode)) {
            return "GS1 Libanon";
        }

        if("529".equals(countryCode)) {
            return "GS1 Ciper";
        }

        if("530".equals(countryCode)) {
            return "GS1 Albanija";
        }

        if("531".equals(countryCode)) {
            return "GS1 Albanija";
        }

        if("535".equals(countryCode)) {
            return "GS1 Malta";
        }

        if("539".equals(countryCode)) {
            return "GS1 Irska";
        }

        for(int i=540; i<550; i++) {
            if((String.valueOf(i)).equals(countryCode)) {
                return "GS1 Belgija & Luksemburg";
            }
        }

        if("560".equals(countryCode)) {
            return "GS1 Portugalska";
        }

        if("569".equals(countryCode)) {
            return "GS1 Islandija";
        }

        for(int i=570; i<580; i++) {
            if((String.valueOf(i)).equals(countryCode)) {
                return "GS1 Belgija & Luksemburg";
            }
        }

        return "/";
    }

    @Override
    public String toString() {
        double p1 = (double)this.cenaBrezDDV/100;
        double p2 = (double)this.cenaZDDV/100;
        double p3 = (double)this.davcnaStopnja/100;
        return "EAN Code: " + EAN + "\n" + "Drzava: " + drzava + '\n' + "Ime artikla: " + ime + "\n" +
                "Cena artikla brez DDV: " + p1 + "eur" +
                "\n" + "Cena artikla z " + p3 + "% DDV: " + p2 + "eur";
    }
}
