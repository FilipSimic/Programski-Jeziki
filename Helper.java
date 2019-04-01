package Vaja1;

import java.io.*;

public class Helper {
    public static void write(String json, String filename) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            out.write(json);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static String read(String filename) {
        StringBuilder ret = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line = br.readLine();
            while (line != null) {
                ret.append(line);
                line = br.readLine();
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ret.toString();
    }
}
