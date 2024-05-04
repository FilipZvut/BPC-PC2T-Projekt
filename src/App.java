import java.util.Scanner;

public class App {
    
    private static Knihovna knihovna = new Knihovna();
    
    public static void main(String[] args) {
        String Nazev;
        Scanner sc = new Scanner(System.in);
        Scanner entrSc = new Scanner(System.in);
        knihovna.nacistKnihyZDatabaze();
        boolean run = true;
 
        while (run) {
            System.out.println();
            System.out.println("Vyberte akci:");
            System.out.println("1. Přidat knihu");
            System.out.println("2. Upravit knihu");
            System.out.println("3. Smazat knihu");
            System.out.println("4. Vyhledat knihu");
            System.out.println("5. Označit knihu Vypujceno/Volna");
            System.out.println("6. Vypis vsech knih");
            System.out.println("7. Vypis knih od urciteho autora");
            System.out.println("8. Vypis vsech knih podle zanru");
            System.out.println("9. Vypis vypujcenych knih");
            System.out.println("10. Ulozit knihu do souboru");
            System.out.println("11. Nacist knihu ze souboru");
            System.out.println("12. Konec");
            int volba = OsetreneZadavani.IntVstup();
            System.out.println();


            switch (volba) {
                case 1:
                    OsetreneZadavani.zaznamKnihy(1, "Pridat");
                    break;
                case 2:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro upravení:");
                    if(!"konec".equals(Nazev)) {
                        knihovna.vyhledat(Nazev);
                        System.out.println();
                        OsetreneZadavani.zaznamKnihy(0, Nazev);
                    }
                    break;
                case 3:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro smazání");
                    knihovna.smazatKnihu(Nazev);
                    break;
                case 4:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro vyhledání:");
                    knihovna.vyhledat(Nazev);
                    break;
                case 5:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro označení:");
                    System.out.println("Je kniha dostupna?");
                    boolean volna = OsetreneZadavani.BooleanVstup();
                    knihovna.nastavitDostupnostKnihy(Nazev, volna);
                    break;
                case 6:
                    knihovna.vypisKnih();
                    break;
                case 7:
                    System.out.println("Zadejte jméno autora:");
                    String autor = sc.nextLine();
                    knihovna.vypisAutora(autor);
                    break;
                case 8:
                    knihovna.vypisZanru(OsetreneZadavani.typKnihy());
                    break;
                case 9:
                    knihovna.vypisVypujceno();
                    break;
                case 10:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro uložení:");
                    knihovna.ulozitKnihu(Nazev);
                    break;
                case 11:
                    System.out.println("Zadejte jméno knihy pro načtení:");
                    Nazev = sc.nextLine();
                    knihovna.nacistKnihu(Nazev);
                    break;
                case 12:
                    run = false;
                    knihovna.ulozitKnihyDoDatabaze();
                    sc.close();
                    entrSc.close();
                    break;
                default:
                    System.out.println("Neplatná volba.");
            }

            if (run) {
                System.out.println(" ");
                System.out.println("Stiskněte Enter pro pokračování...");
                entrSc.nextLine();  
            }
        }

        System.out.println("Konec programu.");
    }

    public static Knihovna getKnihovna() {
        return knihovna;
    
    private static Knihovna knihovna = new Knihovna();
    
    public static void main(String[] args) {
        String Nazev;
         Scanner sc = new Scanner(System.in);
        Scanner entrSc = new Scanner(System.in);
        knihovna.nacistKnihyZDatabaze();
        boolean run = true;
 
        while (run) {
            System.out.println();
            System.out.println("Vyberte akci:");
            System.out.println("1. Přidat knihu");
            System.out.println("2. Upravit knihu");
            System.out.println("3. Smazat knihu");
            System.out.println("4. Vyhledat knihu");
            System.out.println("5. Označit knihu Vypujceno/Volna");
            System.out.println("6. Vypis vsech knih");
            System.out.println("7. Vypis knih od urciteho autora");
            System.out.println("8. Vypis vsech knih podle zanru");
            System.out.println("9. Vypis vypujcenych knih");
            System.out.println("10. Ulozit knihu do souboru");
            System.out.println("11. Nacist knihu ze souboru");
            System.out.println("12. Konec");
            int volba = OsetreneZadavani.IntVstup();
            System.out.println();


            switch (volba) {
                case 1:
                    OsetreneZadavani.zaznamKnihy(1, "Pridat");
                    break;
                case 2:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro upravení:");
                    if(!"konec".equals(Nazev)) {
                        knihovna.vyhledat(Nazev);
                        System.out.println();
                        OsetreneZadavani.zaznamKnihy(0, Nazev);
                    }
                    break;
                case 3:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro smazání");
                    knihovna.smazatKnihu(Nazev);
                    break;
                case 4:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro vyhledání:");
                    knihovna.vyhledat(Nazev);
                    break;
                case 5:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro označení:");
                    System.out.println("Je kniha dostupna?");
                    boolean volna = OsetreneZadavani.BooleanVstup();
                    knihovna.nastavitDostupnostKnihy(Nazev, volna);
                    break;
                case 6:
                    knihovna.vypisKnih();
                    break;
                case 7:
                    System.out.println("Zadejte jméno autora:");
                    String autor = sc.nextLine();
                    knihovna.vypisAutora(autor);
                    break;
                case 8:
                    knihovna.vypisZanru(OsetreneZadavani.typKnihy());
                    break;
                case 9:
                    knihovna.vypisVypujceno();
                    break;
                case 10:
                    Nazev = OsetreneZadavani.existujiciJmenoKnihy("Zadejte jméno knihy pro uložení:");
                    knihovna.ulozitKnihu(Nazev);
                    break;
                case 11:
                    System.out.println("Zadejte jméno knihy pro načtení:");
                    Nazev = sc.nextLine();
                    knihovna.nacistKnihu(Nazev);
                    break;
                case 12:
                    run = false;
                    knihovna.ulozitKnihyDoDatabaze();
                    sc.close();
                    entrSc.close();
                    break;
                default:
                    System.out.println("Neplatná volba.");
            }

            if (run) {
                System.out.println(" ");
                System.out.println("Stiskněte Enter pro pokračování...");
                entrSc.nextLine();  
            }
        }

        System.out.println("Konec programu.");
    }

    public static Knihovna getKnihovna() {
        return knihovna;
    }
}
