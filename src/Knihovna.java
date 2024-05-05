import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import Database.Databaze;
import Knihy.Kniha;
import Knihy.Kniha.TypKnihy;

public class Knihovna {
    
    private SortedMap<String, Kniha> SeznamKnih;
    private Databaze _db;

    public Knihovna() {
        SeznamKnih = new TreeMap<>();
        _db = new Databaze();

    }

    public boolean pridatKnihu(Kniha kniha) {
        if(SeznamKnih.put(kniha.getNazev(), kniha) == null)
            return true;
        else
            return false;          
    }

    public boolean upravitKnihu(String nazev, Kniha kniha) {
        if(SeznamKnih.containsKey(nazev)) {
            SeznamKnih.remove(nazev);
            SeznamKnih.put(kniha.getNazev(), kniha);
            return true;
        }
        else
            return false;
    }

    public boolean smazatKnihu(String nazev) {
        if(SeznamKnih.remove(nazev) != null)
            return true;
        else
            return false;
    }
    
    public boolean vyhledat(String nazev) {
        Kniha vyhledanaKniha;
        if(SeznamKnih.containsKey(nazev)) {
            vyhledanaKniha = SeznamKnih.get(nazev);
            System.out.println("Vyhledaná kniha:");
            System.out.println(vyhledanaKniha);
            return true;
        }
        else
            return false;
    }

    public boolean nastavitDostupnostKnihy(String nazev, boolean stav) {
        Kniha vyhledanaKniha;
        if(SeznamKnih.containsKey(nazev)) {
            vyhledanaKniha = SeznamKnih.get(nazev);
            vyhledanaKniha.nastavitDostupnost(stav);
            return true;
        }
        else
            return false;
    }

    public boolean vypisKnih() {
        try {
        System.out.println("Výpis knih:");
        for (Kniha kniha : SeznamKnih.values()) {
            System.out.println(kniha);
        }
        return true;
        }catch(Exception e) {
            System.out.println("Nelze vypsat knihy");
            return false;
        }
    }

    public void vypisAutora(String jmeno) {
        SortedMap<Integer, List<Kniha>> autorovyKnizky = new TreeMap<>();
    
        for (Kniha kniha : SeznamKnih.values()) {
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
        for (Kniha kniha : SeznamKnih.values()) {
            if(kniha.getTypKnihy() == typ)
                System.out.println(kniha);
        }
    }

    public void vypisVypujceno() {
        
        System.out.println("Výpis vypůjčených knih: ");
        for (Kniha kniha : SeznamKnih.values()) {
            if(!kniha.getDostupnost())
                System.out.println(kniha);
        }
    }

    public boolean ulozitKnihyDoDatabaze() {
       if(_db.ulozitDb(new TreeMap<String,Kniha>(SeznamKnih)))
            return true;
        else
            return false;
    }

    public boolean nacistKnihyZDatabaze() {
        SeznamKnih = _db.nacistDb();
        return !SeznamKnih.isEmpty();
                        
    }

    public boolean ulozitKnihu(String nazev) {
        return _db.ulozitKnihu(SeznamKnih.get(nazev));
    }

    public boolean nacistKnihu(String nazev) {
        
        if (_db.nacistKnihu((TreeMap<String,Kniha>)SeznamKnih, nazev)) {
            System.out.println(SeznamKnih.get(nazev));
            return true; 
        } 
        else {
            return false;
        }
    }

    public TypKnihy getTypKnihy(String nazev) {
        Kniha vyhledanaKniha;
        vyhledanaKniha = SeznamKnih.get(nazev);
        return vyhledanaKniha.getTypKnihy();
                 
    }

    public boolean overitExistenci(String nazev) {
        return SeznamKnih.containsKey(nazev);
    }
}
