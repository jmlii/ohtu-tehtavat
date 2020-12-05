package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        Matcher m = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        
        // tehtävä 2: All, Not, HasFewerThan
        System.out.println("\nNumber of all players : "+ stats.matches(new All()).size());
        
        Matcher m2 = new And( 
            new Not( new HasAtLeast(1, "goals") ), 
            new PlaysIn("NYR")
        );
        System.out.println("\nNot: ");
        for (Player player : stats.matches(m2)) {
            System.out.println(player);
        }

        Matcher m3 = new And( 
            new HasFewerThan(1, "goals"), 
            new PlaysIn("NYR")
        );
        System.out.println("\nHasFewerThan:");
        for (Player player : stats.matches(m3)) {
            System.out.println(player);
        }

        // tehtävä 3: Or
        Matcher m4 = new Or( new HasAtLeast(40, "goals"),
            new HasAtLeast(60, "assists")
        );  
        System.out.println("\nOr:");
        for (Player player : stats.matches(m4)) {
            System.out.println(player);
        }
        
        Matcher m5 = new And(
            new HasAtLeast(50, "points"),
            new Or( 
                new PlaysIn("NYR"),
                new PlaysIn("NYI"),
                new PlaysIn("BOS")
            )
        );
        System.out.println("\nOr:");
        for (Player player : stats.matches(m5)) {
            System.out.println(player);
        }
        
        // tehtävä 4:
        QueryBuilder query = new QueryBuilder();
        Matcher m6 = query.build();
        System.out.println("\nQueryBuilder1, kaikki pelaajat:");
        for (Player player : stats.matches(m6)) {
            System.out.println( player );
        }
        

        Matcher m7 = query.playsIn("NYR").build();
        System.out.println("\nQueryBuilder2:");
        for (Player player : stats.matches(m7)) {
            System.out.println( player );
        }
     
        Matcher m8 = query.playsIn("NYR")
            .hasAtLeast(5, "goals")
            .hasFewerThan(10, "goals").build();    
        System.out.println("\nQueryBuilder3:");
        for (Player player : stats.matches(m8)) {
            System.out.println( player );
        }

        // Tehtävä 5
        Matcher m9 = query.oneOf(
        query.playsIn("PHI")
        .hasAtLeast(10, "assists")
        .hasFewerThan(5, "goals").build(),

        query.playsIn("EDM")
        .hasAtLeast(40, "points").build()
        ).build();
        
        System.out.println("\nQueryBuilder4, one of:");
        for (Player player : stats.matches(m9)) {
            System.out.println( player );
        }
        
    }
    
}
