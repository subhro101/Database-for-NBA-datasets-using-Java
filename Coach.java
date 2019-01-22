public class Coach {

    private String Coach_ID, first_name, last_name, team;
    private int season, season_win, season_loss, playoff_win, playoff_loss;

    public String getCoach_ID() {
        return Coach_ID;
    }

    public void setCoach_ID(String coach_ID) {
        Coach_ID = coach_ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getSeason_win() {
        return season_win;
    }

    public void setSeason_win(int season_win) {
        this.season_win = season_win;
    }

    public int getSeason_loss() {
        return season_loss;
    }

    public void setSeason_loss(int season_loss) {
        this.season_loss = season_loss;
    }

    public int getPlayoff_win() {
        return playoff_win;
    }

    public void setPlayoff_win(int playoff_win) {
        this.playoff_win = playoff_win;
    }

    public int getPlayoff_loss() {
        return playoff_loss;
    }

    public void setPlayoff_loss(int playoff_loss) {
        this.playoff_loss = playoff_loss;
    }
}
