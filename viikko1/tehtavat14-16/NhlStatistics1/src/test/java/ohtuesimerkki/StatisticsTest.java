package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    } 
    
    @Test 
    public void pelaajahakuLoytaaPelaajan() {
        assertEquals("Kurri", stats.search("Kurri").getName());
    }
    
    @Test
    public void pelaajahakuEiLoydaOlemattomia() {
        assertNull(stats.search("Jutila"));
    }
    
    @Test
    public void joukkueenPelaajalistausToimii() {
        List<String> namesInTeam = new ArrayList<String>();
        for (Player player : stats.team("EDM")) {
            namesInTeam.add(player.getName());
        }
        assertEquals("[Semenko, Kurri, Gretzky]", namesInTeam.toString());
    }
    
    @Test
    public void oikeatEnitenPisteitaTehneet() {
        List<String> topScorerNames = new ArrayList<String>();
        for (Player player : stats.topScorers(1)) {
            topScorerNames.add(player.getName());
        }
        assertEquals("[Gretzky, Lemieux]", topScorerNames.toString());
    }
}
