public class Team {
    private String team_ID, Location, Name;

    public String getTeam_ID() {
        return team_ID;
    }

    public void setTeam_ID(String team_ID) {
        this.team_ID = team_ID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public char getLeague() {
        return League;
    }

    public void setLeague(char league) {
        League = league;
    }

    private char League;

}
