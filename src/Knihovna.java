import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import Database.Databaze;
import Knihy.Kniha;
import Knihy.Kniha.TypKnihy;

public class Knihovna {

    private final SortedMap<String, Kniha> seznamKnih;
    private final Databaze db;

    public Knihovna() {
        seznamKnih = new TreeMap<>();
        db = new Databaze();
    }

    public boolean pridatKnihu(Kniha kniha) {
        return seznamKnih.putIfAbsent(kniha.getNazev(), kniha) == null;
    }

    public boolean upravitKnihu(String nazev, Kniha kniha) {
        if (!seznamKnih.containsKey(nazev)) {
            return false;
        }
        seznamKnih.put(nazev, kniha);
        return true;
    }

    public boolean smazatKnihu(String nazev) {
        return seznamKnih.remove(nazev) != null;
    }

    public boolean vyhledat(String nazev) {
        Kniha kniha = seznamKnih.get(nazev);
        if (kniha == null) {
            return false;
        }
        System.out.println("Vyhledaná kniha:");
        System.out.println(kniha);
        return true;
    }

    public boolean nastavitDostupnostKnihy(String nazev, boolean stav) {
        Kniha kniha = seznamKnih.get(nazev);
        if (kniha == null) {
            return false;
        }
        kniha.nastavitDostupnost(stav);
        return true;
    }

    public boolean vypisKnih() {
        try {
            System.out.println("Výpis knih:");
            for (Kniha kniha : seznamKnih.values()) {
                System.out.println(kniha);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Nelze vypsat knihy");
            return false;
        }
    }

    public void vypisAutora(String jmeno) {
        SortedMap<Integer, List<Kniha>> autorovyKnizky = new TreeMap<>();
        for (Kniha kniha : seznamKnih.values()) {
            String[] autori = kniha.getAutor();
            for (String autor : autori) {
                if (autor.equals(jmeno)) {
                    int rokVydani = kniha.getRokVydani();
                    autorovyKnizky.putIfAbsent(rokVydani, new ArrayList<>());
                    autorovyKnizky.get(rokVydani).add(kniha);
                    break;
                }
            }
        }
        System.out.println("Chronologicky seřazené knihy od autora " + jmeno + ":");
        for (List<Kniha> knihy : autorovyKnizky.values()) {
            for (Kniha kniha : knihy) {
                System.out.println(kniha);
            }
        }
    }

    public void vypisZanru(TypKnihy typ) {
        System.out.println("Výpis knih podle žánru: ");
        for (Kniha kniha : seznamKnih.values()) {
            if (kniha.getTypKnihy() == typ) {
                System.out.println(kniha);
            }
        }
    }

    public void vypisVypujceno() {
        System.out.println("Výpis vypůjčených knih: ");
        for (Kniha kniha : seznamKnih.values()) {
            if (!kniha.getDostupnost()) {
                System.out.println(kniha);
            }
        }
    }

    public boolean ulozitKnihyDoDatabaze() {
        return db.ulozitDb(new TreeMap<>(seznamKnih));
    }

    public boolean nacistKnihyZDatabaze() {
        seznamKnih.putAll(db.nacistDb());
        return !seznamKnih.isEmpty();
    }

    public boolean ulozitKnihu(String nazev) {
        return db.ulozitKnihu(seznamKnih.get(nazev));
    }

    public boolean nacistKnihu(String nazev) {
        return db.nacistKnihu((TreeMap<String, Kniha>) seznamKnih, nazev);
    }

    public TypKnihy getTypKnihy(String nazev) {
        Kniha kniha = seznamKnih.get(nazev);
        return kniha == null ? null : kniha.getTypKnihy();
    }

    public boolean overitExistenci(String nazev) {
        return seznamKnih.containsKey(nazev);
    }
}
