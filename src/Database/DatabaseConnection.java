package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedMap;
import java.util.TreeMap;
import Knihy.Kniha;
import Knihy.Ucebnice;
import Knihy.Roman.Zanry;
import Knihy.Roman;


public abstract class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/knihovna";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static SortedMap<String, Kniha> nacistKnihyZDatabase(Connection connection) {
        
        SortedMap<String, Kniha> knihy = new TreeMap<>();
        Zanry[] zanr = Zanry.values();
        String[] autori;
        String sql = "SELECT Nazev, Autor, RokVydani, Dostupnost, Typ, ZanrRocnik FROM Knihy";
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String nazev = resultSet.getString("Nazev");
                String autor = resultSet.getString("Autor");
                int rokVydani = resultSet.getInt("RokVydani");
                boolean dostupnost = resultSet.getBoolean("Dostupnost");
                int typ = resultSet.getInt("Typ");
                int zanrRocnik = resultSet.getInt("ZanrRocnik");

                autori = autor.split(", ");

                if(typ==1){
                    Ucebnice ucebnice = new Ucebnice(nazev, autori, rokVydani, dostupnost, zanrRocnik);
                    knihy.put(nazev, ucebnice);
                }
                else{
                    Roman roman = new Roman(nazev, autori, rokVydani, dostupnost, zanr[zanrRocnik]);
                    knihy.put(nazev, roman);
                }
            }
        }
        catch(SQLException e) {
            System.out.println("Chyba při přístupu k databázi:");    
        } 
        catch(NullPointerException e) {
            System.out.println("Chyba při načítání knih, databáze není připojena");
        }
        return knihy;
    }

    public static boolean ulozitKnihyDoDatabaze(TreeMap<String,Kniha> knihy, Connection connection) {

        int zanrRocnik;     
        String sqlInsert = "INSERT INTO Knihy (Nazev, Autor, RokVydani, Dostupnost, Typ, ZanrRocnik) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlDelete = "DELETE FROM Knihy";

        try (PreparedStatement statementDelete = connection.prepareStatement(sqlDelete);
             PreparedStatement statementInsert = connection.prepareStatement(sqlInsert)) {
            
            statementDelete.executeUpdate();
               
            for(Kniha kniha : knihy.values()){
            
                if(kniha.getTypKnihyINT()==0)
                    zanrRocnik = ((Roman)kniha).getZanr().ordinal();
                else
                    zanrRocnik = ((Ucebnice)kniha).getRocnik();

                statementInsert.setString(1, kniha.getNazev());
                statementInsert.setString(2,  kniha.autoriNaString());
                statementInsert.setInt(3, kniha.getRokVydani());
                statementInsert.setBoolean(4, kniha.getDostupnost());
                statementInsert.setInt(5, kniha.getTypKnihyINT());
                statementInsert.setInt(6, zanrRocnik);

                statementInsert.executeUpdate();
            }
            return true;
        }
        catch (SQLException e) {
            System.out.println("Chyba při přístupu k databázi");
            return false;
        }
        catch (NullPointerException e) {
            System.out.println("Chyba při ukládání knih, databáze není připojena");
            return false;
        }

    }

}


