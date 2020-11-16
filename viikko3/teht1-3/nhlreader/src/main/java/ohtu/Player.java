
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;    
    
    public Player(String name, String nationality, String team, int goals, int assists) {
        this.name = name;
        this.nationality = nationality;
        this.team = team;
        this.goals = goals;
        this.assists = assists;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public String getTeam() {
        return team;
    }
    
    public int getGoals() {
        return goals;
    }
    
    public int getAssists() {
        return assists;
    }
    
    public int getPoints() {
        return goals + assists;
    }
    
    @Override
    public String toString() {
        return String.format("%-25s", name) + String.format("%-5s", team) + " points: " + getPoints();
    }
      
    public int compareTo(Player other) {
        return other.getPoints() - this.getPoints();
    }
}
