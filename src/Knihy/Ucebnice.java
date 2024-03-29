package Knihy;

public class Ucebnice extends Kniha{
    
    int Rocnik;
    
    public Ucebnice(String nazev, String[] autor, int rokVydani, boolean dostupnost, int rocnik) {
        
        super(nazev, autor, rokVydani, dostupnost);
        Rocnik = rocnik;        
    }
    
}
