package Knihy;

public class Roman extends Kniha {
    
    private Zanry Zanr;

    public enum Zanry { 
        HISTORICKY,
        BIOGRAFICKY, 
        PROFESNI, 
        RYTIRSKY, 
        GOTICKY
    }
    
    public Roman(String nazev, String[] autor, int rokVydani, boolean dostupnost, Zanry zanr) {    
        super(nazev,autor,rokVydani,dostupnost, TypKnihy.ROMAN);
        Zanr = zanr;
    }

    public Zanry getZanr() {
        return Zanr;
    }

    @Override
    public String toString() {
        return super.toString() + ", typ: roman, zanr: " + Zanr;
    }

    @Override
    public String formatovatDoSouboru()
    {
        return super.formatovatDoSouboru() + ";" + Zanr.ordinal();
    }
}
