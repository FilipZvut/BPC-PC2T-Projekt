package Knihy;

public class Ucebnice extends Kniha{
    
    private int Rocnik;
    
    public Ucebnice(String nazev, String[] autor, int rokVydani, boolean dostupnost, int rocnik) {        
        super(nazev, autor, rokVydani, dostupnost, TypKnihy.UCEBNICE);
        Rocnik = rocnik;        
    }
    
    public int getRocnik() {
        return Rocnik;
    }
   
    @Override
    public String toString() {
        return super.toString() + ", typ: ucebnice, rocnik: " + Rocnik;
    }
    
    @Override
    public String formatovatDoSouboru()
    {
        return super.formatovatDoSouboru() + ";" + Rocnik;
    }
}
