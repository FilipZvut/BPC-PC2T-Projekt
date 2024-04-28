package Knihy;

public class Roman extends Kniha {
    
    private Zanry Zanr;

    public enum Zanry { 
        Historicky,
        Biograficky, 
        Profesni, 
        Rytirsky, 
        Goticky
    }
    
    public Roman(String nazev, String[] autor, int rokVydani, boolean dostupnost, Zanry zanr) {    
        super(nazev,autor,rokVydani,dostupnost, TypKnihy.Roman);
        Zanr = zanr;
    }

    public Zanry getZanr() {
        return Zanr;
    }

    @Override
    public String toString() {
        return super.toString() + ", typ: roman, Zanr: " + Zanr;
    }

    @Override
    public String formatovatDoSouboru()
    {
        return super.formatovatDoSouboru() + ";" + Zanr.ordinal();
    }
}
