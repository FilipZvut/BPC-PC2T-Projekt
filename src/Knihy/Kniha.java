package Knihy;

abstract class Kniha {

    private String Nazev;
    private String[] Autor;
    private int RokVydani;
    private boolean Dostupnost;

    protected Kniha(String nazev, String[] autor, int rokVydani, boolean dostupnost) {
        Nazev = nazev;
        Autor = autor;
        RokVydani = rokVydani;
        Dostupnost = dostupnost;
    }

    public String getNazev() {
        return Nazev;
    }

    public void setNazev(String nazev) {
        Nazev = nazev;
    }

    public String[] getAutor() {
        return Autor;
    }

    public void setAutor(String[] autor) {
        Autor = autor;
    }

    public int getRokVydani() {
        return RokVydani;
    }

    public void setRokVydani(int rokVydani) {
        RokVydani = rokVydani;
    }

    public boolean isDostupnost() {
        return Dostupnost;
    }

    public void setDostupnost(boolean dostupnost) {
        Dostupnost = dostupnost;
    }
}
