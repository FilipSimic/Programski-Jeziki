package Vaja1;

import org.apache.commons.dbcp2.BasicDataSource;
import si.um.feri.database.DBHelper;

import java.util.Date;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        try (BasicDataSource dataSource = DBHelper.getDataSource();
             Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM actor");)
        {
            System.out.println("The Connection Object is of Class: " + connection.getClass());
            try (ResultSet resultSet = pstmt.executeQuery();)
            {
                while (resultSet.next())
                {
                    System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
                }
            }
            catch (Exception e)
            {
                connection.setAutoCommit(false);
                connection.rollback();
                e.printStackTrace();
            }
        }

//        int slovenijaDDV = 22;
//
//        Artikel a1 = new Artikel("Artikel1", 230, slovenijaDDV);
//        Artikel a2 = new Artikel("Artikel2", 450, slovenijaDDV);
//        Artikel a3 = new Artikel("Artikel3", 788, slovenijaDDV);
//
//        Artikli sez = new Artikli();
//        sez.add(a1, 1);
//        sez.add(a2, 2);
//        sez.add(a3, 3);
//
//        sez.remove(0);
//
//        sez.edit(0, "Test", 7500, 1);
//
//        Oseba o = new Oseba("Gorazd", "Spletnik", "Dunajska 16",
//                "Ljubljana","Slovenija","+3861234567",1000);
//
//        Podjetje p1 = new Podjetje("Henkel Maribor d.o.o.", "Industrijska ulica 23", "Maribor",
//                "Slovenija", 2000, "+38622222100", "58665765", "6261752000",
//                true);
//        Podjetje p2 = new Podjetje("Arriva Stajerska d.d.", "Meljska Cesta 97", "Maribor",
//                "Slovenija", 2000, "+38623003360", "31727077", "5263433000",
//                false);
//        Podjetje p3 = new Podjetje("Andra d.o.o.", "Fala 40", "Fala",
//                "Slovenija", 2343, "+38641652168", "74545167", "5707323000",
//                false);
//
//        String asd = sez.toJson();
//        Artikli test = new Artikli();
//        test.fromJson(asd);
//        System.out.println(test.toString());
//
//        Helper.write(asd, "artikli.json");
//        System.out.println(Helper.read("artikli.json"));
//
//        Artikel ar1 = new Artikel("200010005001", 150, slovenijaDDV);
//        Artikel ar2 = new Artikel("220210008002", 180, slovenijaDDV);
//        Artikel ar3 = new Artikel("240410003003", 210, slovenijaDDV);
//
//        System.out.println(ar1.toString());
//        System.out.println(ar2.toString());
//        System.out.println(ar3.toString());
//        ar1.setTeza(900);
//        ar2.setCenaBrezDDV(250);
//        System.out.println(ar1.toString());
//        System.out.println(ar2.toString());
//        System.out.println(ar3.toString() + "\n\n");
//
//        Racun rac = new Racun(new Date(), test, o, o, Racun.NacinDostave.POSTA_SLOVENIJE, Racun.NacinPlacila.GOTOVINA, p1, p1.getDavcnaStevilka());
//        Racun rac2 = new Racun(new Date(), test, o, o, Racun.NacinDostave.DPD, Racun.NacinPlacila.KREDITNA_KARTICA, p2, p2.getDavcnaStevilka());
//
//        Kupon k1 = new Kupon(10, new Date(), Kupon.Tip.CELOTNI_NAKUP);
//        Kupon k2 = new Kupon(15, new Date(), Kupon.Tip.NAJDRAZJI_IZDELEK);
//        Kupon k3 = new Kupon(5, new Date(), Kupon.Tip.NAJDRAZJI_BOGO);
//
//        rac.dodajKuponZaPopust(k1);
//        rac.dodajKuponZaPopust(k3);
//        rac2.dodajKuponZaPopust(k2);
//
//        System.out.println(rac.toString());
//        System.out.println(rac2.toString());
//
//        if(rac.search("GOTOVINA")) {
//            System.out.println("Niz GOTOVINA obstaja v racunu");
//        } else {
//            System.out.println("Niz GOTOVINA ne obstaja v racunu");
//        }
//
//        if(Artikel.checkDigit(a1.getEAN())) {
//            System.out.println("Artikel " + a1.getIme() + " ima veljavno crtno kodo");
//        } else {
//            System.out.println("Artikel " + a1.getIme() + " nima veljavno crtno kodo");
//        }
//
//        Invoices v = new Invoices(rac, rac2);
//        System.out.println(v.toString());
    }
}
