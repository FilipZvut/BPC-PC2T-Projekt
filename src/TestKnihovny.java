import Knihy.Roman;
import Knihy.Ucebnice;
import Knihy.Kniha.TypKnihy;
import Knihy.Roman.Zanry;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.SQLException;

public class TestKnihovny {

    private Knihovna knihovna;
    private Knihovna PuvodniDB;

    @Before
    public void setUp() throws SQLException {
        knihovna = new Knihovna();
        PuvodniDB = new Knihovna();
    }

    @Test
    public void testPridatUcebnici() {
        Ucebnice ucebnice = new Ucebnice("TestUcebnice1", new String[]{"TestAutor"}, 9999, true, 1);
        assertTrue(knihovna.pridatKnihu(ucebnice));
        assertTrue(knihovna.vyhledat("TestUcebnice1"));
    }

    @Test
    public void testPridatRoman() {
        Roman roman = new Roman("TestRoman1", new String[]{"TestAutor"}, 2020, true, Zanry.Goticky);
        assertTrue(knihovna.pridatKnihu(roman));
        assertTrue(knihovna.vyhledat("TestRoman1"));
    }

    @Test
    public void testUpravitKnihu() {
        Ucebnice ucebnice = new Ucebnice("TestUcebnice2", new String[]{"TestAutor"}, 9999, true, 1);
        knihovna.pridatKnihu(ucebnice);
        assertTrue(knihovna.vyhledat("TestUcebnice2"));
        Ucebnice novaUcebnice = new Ucebnice("TestUcebniceNova2", new String[]{"TestAutor2"}, 2020, false, 2);
        assertTrue(knihovna.upravitKnihu("TestUcebnice2", novaUcebnice));
        assertTrue(knihovna.vyhledat("TestUcebniceNova2"));
        assertFalse(knihovna.vyhledat("TestUcebnice2"));
    }

    @Test
    public void testSmazatKnihu() {
        Ucebnice ucebnice = new Ucebnice("TestUcebnice3", new String[]{"TestAutor"}, 2019, true, 1);
        knihovna.pridatKnihu(ucebnice);
        assertTrue(knihovna.smazatKnihu("TestUcebnice3"));
        assertFalse(knihovna.vyhledat("TestUcebnice3"));
    }

    @Test
    public void testZmeniDostupnost() {
        Ucebnice ucebnice = new Ucebnice("TestUcebnice3", new String[]{"TestAutor"}, 2019, true, 1);
        knihovna.pridatKnihu(ucebnice);
        assertTrue(ucebnice.getDostupnost());
        knihovna.nastavitDostupnostKnihy(ucebnice.getNazev(), false);
        assertFalse(ucebnice.getDostupnost());

    }

    @Test
    public void testVyhledat() {
        Ucebnice ucebnice = new Ucebnice("TestUcebnice", new String[]{"TestAutor"}, 2019, true, 1);
        knihovna.pridatKnihu(ucebnice);
        assertTrue(knihovna.vyhledat("TestUcebnice"));
        assertFalse(knihovna.vyhledat("NeexistujiciKniha"));
    }

    @Test
    public void testVypisKnih() {
        Ucebnice ucebnice = new Ucebnice("TestUcebnice", new String[]{"TestAutor"}, 2019, true, 1);
        knihovna.pridatKnihu(ucebnice);
        knihovna.vypisKnih();
    }

    @Test
    public void testVypisAutora() {
        Roman roman = new Roman("Test", new String[]{"test2"}, 2020, true, Zanry.Goticky);
        Roman roman2 = new Roman("Test2", new String[]{"test2"}, 2020, true, Zanry.Goticky);
        knihovna.pridatKnihu(roman);
        knihovna.pridatKnihu(roman2);
        knihovna.vypisAutora("test2"); 
    }

    @Test
    public void testVypisZanru() {
        Roman roman = new Roman("Test", new String[]{"TestAutora"}, 2020, true, Zanry.Goticky);
        knihovna.pridatKnihu(roman);
        Ucebnice ucebnice = new Ucebnice("TestUcebnice", new String[]{"TestAutor"}, 2019, true, 1);
        knihovna.pridatKnihu(ucebnice);
        knihovna.vypisZanru(TypKnihy.Roman);
        knihovna.vypisZanru(TypKnihy.Ucebnice);
    }

    @Test
    public void testVypisVypujceno() {
        Roman roman = new Roman("Test", new String[]{"TestAutora"}, 2020, false, Zanry.Goticky);
        knihovna.pridatKnihu(roman);
        Ucebnice ucebnice = new Ucebnice("TestUcebnice4", new String[]{"TestAutor"}, 2019, true, 1);
        knihovna.pridatKnihu(ucebnice);
        knihovna.pridatKnihu(ucebnice);
        knihovna.vypisVypujceno();
    }

    @Test
    public void testDatabaze() throws SQLException {
        PuvodniDB.nacistKnihyZDatabaze();
        Ucebnice ucebnice = new Ucebnice("Test", new String[]{"TestAutora"}, 2019, true, 1);
        knihovna.pridatKnihu(ucebnice);
        assertTrue(knihovna.ulozitKnihyDoDatabaze());
        knihovna.nacistKnihyZDatabaze();
        assertTrue(knihovna.vyhledat("Test"));
        PuvodniDB.ulozitKnihyDoDatabaze();
    }

    @Test
    public void testUlozitKnihuDoSouboru() {
        Ucebnice ucebnice = new Ucebnice("Test", new String[]{"TestAutora"}, 2019, true, 1);
        assertTrue(knihovna.pridatKnihu(ucebnice));
        assertTrue(knihovna.ulozitKnihu("Test"));
        assertTrue(knihovna.nacistKnihu("Test"));
    }

    @Test
    public void testOveritExistenci() {
        Ucebnice ucebnice = new Ucebnice("Test", new String[]{"TestAutora"}, 2019, true, 1);
        assertTrue(knihovna.pridatKnihu(ucebnice));
        assertTrue(knihovna.overitExistenci("Test"));
        assertFalse(knihovna.overitExistenci("TestFalse"));
    }

}