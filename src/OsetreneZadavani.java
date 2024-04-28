import java.util.Scanner;

import Knihy.Roman;
import Knihy.Roman.Zanry;
import VlastniException.KnihaException;
import Knihy.Ucebnice;
import Knihy.Kniha.TypKnihy;

public abstract class OsetreneZadavani {

    private static Knihovna knihovna = App.getKnihovna();
    private static Scanner sc = new Scanner(System.in);

    public static int IntVstup() {
        int cislo = 0;
        boolean uspesne = false;

        while (!uspesne) {
            if (sc.hasNextInt()) {
                cislo = sc.nextInt();
                uspesne = true;
            } else {
                System.out.println("Chybný vstup. Zadejte platné celé číslo.");
                sc.next(); // Zahození neplatného vstupu
            }
        }
        sc.nextLine();
        return cislo;
    }

    public static boolean BooleanVstup() {
        boolean bool = false;
        boolean uspesne = false;

        while (!uspesne) {
            System.out.print("Zadejte ano/ne: ");
            String input = sc.nextLine().toLowerCase();
            if (input.equals("ano") || input.equals("a") || input.equals("yes") || input.equals("y")) {
                bool = true;
                uspesne = true;
            } else if (input.equals("ne") || input.equals("n") || input.equals("no")) {
                bool = false;
                uspesne = true;
            } else {
                System.out.println("Chybný vstup. .");
            }
        }
        return bool;
    }

    static void zaznamKnihy(int operace, String nazev1) {
        TypKnihy typ;
        
        try {
            if(operace==0)
                System.out.println("Nove udaje:");
            String nazev = neExistujiciJmenoKnihy(nazev1);
            if("konec".equals(nazev))
                throw new KnihaException("Operace ukoncena");
            System.out.println("Zadejte autora(y) knihy oddělené čárkou:");
            String autori = sc.nextLine();
            System.out.println("Zadejte rok vydání:");
            int rokVydani = IntVstup();
            System.out.println("Je kniha dostupná");
            boolean dostupnost = BooleanVstup();
            if(operace == 1) {
                typ = typKnihy();

            } else {
                typ = knihovna.getTypKnihy(nazev1);
            }
            if (typ == TypKnihy.Roman) {
                Zanry zanr = romanZanry();
                Roman novaKniha = new Roman(nazev, autori.split(", "), rokVydani, dostupnost, zanr);
                if(operace == 1)
                    knihovna.pridatKnihu(novaKniha);
                else
                    knihovna.upravitKnihu(nazev1, novaKniha);
            } else if (typ == TypKnihy.Ucebnice) {
                System.out.println("Zadejte ročník učebnice:");
                int rocnik = rocnikUcebnice();
                Ucebnice novaKniha = new Ucebnice(nazev, autori.split(","), rokVydani, dostupnost, rocnik);

                if(operace == 1)
                    knihovna.pridatKnihu(novaKniha);
                else
                    knihovna.upravitKnihu(nazev1, novaKniha);
            } else {
                System.out.println("Neplatná volba typu knihy.");
            }
        } catch (KnihaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Zanry romanZanry() {
        Zanry zanr = null;
        boolean uspesne = false;
        System.out.println("Zadejte žánr románu (Historicky/Biograficky/Profesni/Rytirsky/Goticky):");

        while (!uspesne) {
            String zanrString = sc.nextLine();
            try {
                zanr = Zanry.valueOf(zanrString);
                uspesne = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Neplatný žánr. Zadejte prosím znovu.");
            }
        }

        return zanr;
    }
    
    public static TypKnihy typKnihy() {
        TypKnihy typKnihy = null;
        boolean uspesne = false;
        
        while (!uspesne) {
            System.out.println("Zadejte typ knihy (Roman/Ucebnice):");
            String typString = sc.nextLine();
            try {
                typKnihy = TypKnihy.valueOf(typString);
                uspesne = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Neplatný typ knihy. Zadejte prosím znovu.");
            }
        }

        return typKnihy;
    }

    public static int rocnikUcebnice() {
        int rocnik = 0;
        boolean uspesne = false;
        
        while (!uspesne) {
            System.out.println("Zadejte typ knihy (Roman/Ucebnice):");
            try {
                rocnik = IntVstup();
                if(rocnik>9 || rocnik<1)
                    throw new KnihaException("Neplatný doporučený ročník. Zadejte prosím znovu.");

                uspesne = true;
            } catch (KnihaException e) {
                System.out.println(e.getMessage());
            }
        }

        return rocnik;
    
    }

    public static String existujiciJmenoKnihy(String zprava) {
        String nazev = null;
        boolean uspesne = false;

        System.out.println(zprava);
        while (!uspesne) {
            try {
                nazev = sc.nextLine();
                if("konec".equals(nazev))
                    uspesne = true;
                else{
                    if(!knihovna.overitExistenci(nazev))
                        throw new KnihaException("Kniha neexistuje. Zadejte jiný název nebo 'konec' pro zrušení operace");  
                    uspesne = true;
                }
            } catch (KnihaException e) {
                System.out.println(e.getMessage());
            }
        }
        return nazev;

    }

    public static String neExistujiciJmenoKnihy(String nazev1) {
        String nazev = null;
        boolean uspesne = false;

        System.out.println("Zadejte název knihy:");
        while (!uspesne) {
            try {
                nazev = sc.nextLine();
                if("konec".equals(nazev))
                    uspesne = true;
                else{
                    if(knihovna.overitExistenci(nazev)&&!nazev1.equals(nazev))
                        throw new KnihaException("Kniha již existuje. Zadejte jiný název nebo 'konec' pro zrušení operace");  
                    uspesne = true;
                }
            } catch (KnihaException e) {
                System.out.println(e.getMessage());
            }
        }
        return nazev;

    }


}
