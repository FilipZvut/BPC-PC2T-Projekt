package Knihy;

public abstract class Kniha {

    private String Nazev;
    private String[] Autor;
    private int RokVydani;
    private boolean Dostupnost;
    private TypKnihy Typ;
    
    public enum TypKnihy{
        ROMAN,
        UCEBNICE
    }

    protected Kniha(String nazev, String[] autor, int rokVydani, boolean dostupnost, TypKnihy typ) {
        Nazev = nazev;
        Autor = autor;
        RokVydani = rokVydani;
        Dostupnost = dostupnost;
        Typ = typ;
    }

    public String getNazev() {
        return Nazev;
    }

    public int getRokVydani() {
        return RokVydani;
    }
    
    public String[] getAutor() {
        return Autor;
    }

    public TypKnihy getTypKnihy() {
        return Typ;
    }

    public int getTypKnihyINT() {
        if(Typ==TypKnihy.ROMAN)
            return 0;
        else
            return 1;
    } 

    public boolean getDostupnost() {
        return Dostupnost;
    }

    public void nastavitDostupnost(boolean dostupnost) {
        Dostupnost = dostupnost;
    }

    public String autoriNaString()
    {
        return String.join(", ", Autor);
    }

    @Override
    public String toString() {
        String vypis;
        String dostupnost;

        if(Dostupnost)
            dostupnost = "Volna";
        else
            dostupnost = "Vypujcena";

        vypis = "Nazev: "+ Nazev + ", autor: " + autoriNaString() + ", rok vydani: " + RokVydani + ", dostupnost: " + dostupnost;

        return vypis;
    }

    public String formatovatDoSouboru(){

        return String.format("%s;%s;%d;%s;%s", Nazev, autoriNaString(), RokVydani, Dostupnost, getTypKnihyINT());
    }
}
