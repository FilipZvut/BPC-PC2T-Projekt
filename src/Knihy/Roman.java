package Knihy;

public class Roman extends Kniha {
    
    public enum Zanry { 
        Historicky,
        Biograficky, 
        Profesni, 
        Rytirsky, 
        Goticky
    }
    
    private Zanry Zanr;

    public Roman(String nazev, String[] autor, int rokVydani, boolean dostupnost, Zanry zanr){    
        super(nazev,autor,rokVydani,dostupnost);
        Zanr = zanr;
    }

    public Zanry getZanr() {
        return Zanr;
    }

    public void setZanr(Zanry zanr) {
        Zanr = zanr;
    }

}
