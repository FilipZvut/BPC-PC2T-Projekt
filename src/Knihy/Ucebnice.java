package Knihy;

public class Ucebnice extends Kniha{
    
    private int Rocnik;
    
    public Ucebnice(String nazev, String[] autor, int rokVydani, boolean dostupnost, int rocnik) {
        
        super(nazev, autor, rokVydani, dostupnost);
        Rocnik = rocnik;        
    }

    public int getRocnik() {
        return Rocnik;
    }

    public void setRocnik(int rocnik) {
        Rocnik = rocnik;
    }
    
}
