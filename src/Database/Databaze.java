package Database;
import Knihy.Kniha;
import Knihy.Roman;
import Knihy.Roman.Zanry;
import Knihy.Ucebnice;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Databaze {
    
    private Connection PropojenaDatabaze;

    public Databaze() {
        try {
            PropojenaDatabaze = DatabaseConnection.getConnection();
        }
        catch(SQLException e) {
            System.out.println("Chyba při připojování k databázi: " + e.getMessage());
        }
        catch(NullPointerException e) {
            System.out.println("Nepodařilo se připojit k databázi");
        }
    }    

    public boolean ulozitKnihu(Kniha kniha) {
        try {
            File soubor = new File("UlozeneKnihy\\" + kniha.getNazev() + ".txt");
            soubor.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(soubor);
            fw.write(kniha.formatovatDoSouboru());
            fw.close();
            return true;
        }
        catch(IOException e) {
            System.out.println("Chyba pri ukladani knihy: " + e.getMessage());
            return false;
        }
    }

    public boolean nacistKnihu(TreeMap<String,Kniha> SeznamKnih, String nazevSouboru) {
        try {
            Zanry[] zanr = Zanry.values();
            File soubor = new File("UlozeneKnihy\\" + nazevSouboru + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(soubor));

            String radek = br.readLine();

            String[] hodnoty = radek.split(";");

            String nazev = hodnoty[0];
            String[] autori = hodnoty[1].split(", ");
            int rokVydani = Integer.parseInt(hodnoty[2]);
            boolean dostupnost = Boolean.parseBoolean(hodnoty[3]);
            int typ = Integer.parseInt(hodnoty[4]);
            int zanrRocnik = Integer.parseInt(hodnoty[5]);

            
            if(typ==0) {
                Roman roman = new Roman(nazev, autori, rokVydani, dostupnost, zanr[zanrRocnik]);
                SeznamKnih.put(nazev, roman);
            }
            else {
                Ucebnice ucebnice = new Ucebnice(nazev,autori,rokVydani,dostupnost,zanrRocnik);
                SeznamKnih.put(nazev, ucebnice);
            }

            br.close();
            return true;

        } catch (IOException e) {
            System.out.println("Chyba při načítání knihy: " + e.getMessage());
            return false;
        }
    }

    public boolean ulozitDb(TreeMap<String,Kniha> SeznamKnih) {
        return DatabaseConnection.ulozitKnihyDoDatabaze(SeznamKnih, PropojenaDatabaze);
    }

    public SortedMap<String, Kniha> nacistDb() {
        return DatabaseConnection.nacistKnihyZDatabase(PropojenaDatabaze);
    }
}
