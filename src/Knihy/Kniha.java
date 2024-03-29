package Knihy;

public abstract class Kniha {

    protected String Nazev;
    protected String[] Autor;
    protected int RokVydani;
    protected boolean Dostupnost;

    public Kniha(String nazev, String[] autor, int rokVydani, boolean dostupnost) {
        Nazev = nazev;
        Autor = autor;
        RokVydani = rokVydani;
        Dostupnost = dostupnost;
    }
}
