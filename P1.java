import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class
P1 {

    /* Define data structures for holding the data here */
    ArrayList <Team> teams;
    ArrayList <Coach> coaches;
    public P1() {
        /* initialize the data structures */
        coaches = new ArrayList<>();
        teams = new ArrayList<>();
    }

    public void run() {
        CommandParser parser = new CommandParser();

        System.out.println("The mini-DB of NBA coaches and teams");
        System.out.println("Please enter a command.  Enter 'help' for a list of commands.");
        System.out.println();
        System.out.print("> ");

        Command cmd = null;
        while ((cmd = parser.fetchCommand()) != null) {
            //System.out.println(cmd);

            boolean result = false;

            if (cmd.getCommand().equals("help")) {
                result = doHelp();

                /* You need to implement all the following commands */
            } else if (cmd.getCommand().equals("add_coach")) {
                Coach coach = new Coach();

                coach.setCoach_ID(cmd.getParameters()[0]);
                coach.setFirst_name(cmd.getParameters()[2]);
                coach.setLast_name(cmd.getParameters()[3]);
                coach.setPlayoff_loss(Integer.parseInt(cmd.getParameters()[7]));
                coach.setSeason(Integer.parseInt(cmd.getParameters()[1]));
                coach.setSeason_loss (Integer.parseInt(cmd.getParameters()[5]));
                coach.setPlayoff_win(Integer.parseInt(cmd.getParameters()[6]));
                coach.setSeason_win(Integer.parseInt(cmd.getParameters()[4]));
                coach.setTeam(cmd.getParameters()[8]);



            } else if (cmd.getCommand().equals("add_team")) {
                Team team = new Team();
                team.setLeague(String.valueOf(cmd.getParameters()[0]).charAt(0));
                team.setLocation(cmd.getParameters()[1]);
                team.setName(cmd.getParameters()[2]);
                team.setTeam_ID(cmd.getParameters()[3]);

            } else if (cmd.getCommand().equals("print_coaches")) {
                for(Coach coach :coaches)
                {
                    System.out.println(coach.getCoach_ID() + " " + coach.getSeason()+ " " + coach.getFirst_name() +
                            " " + coach.getLast_name() + " " + coach.getSeason_win() +
                            " " + coach.getSeason_loss() + " "
                            + coach.getPlayoff_win()+ " " + coach.getPlayoff_loss() + " " + coach.getTeam());
                }

            } else if (cmd.getCommand().equals("print_teams")) {

                for (Team team: teams)
                {
                    System.out.println(team.getTeam_ID() + " " + team.getLocation() + " " + team.getName() + " " + team.getLeague() );
                }

            } else if (cmd.getCommand().equals("coaches_by_name")) {
                for (Coach coach : coaches)
                {
                    if(cmd.getParameters()[0].equalsIgnoreCase(coach.getLast_name())){
                    System.out.println(coach.getCoach_ID() + " " + coach.getSeason()+ " " + coach.getFirst_name() +
                            " " + coach.getLast_name() + " " + coach.getSeason_win() +
                            " " + coach.getSeason_loss() + " "
                            + coach.getPlayoff_win()+ " " + coach.getPlayoff_loss() + " " + coach.getTeam());
                }
                }

            } else if (cmd.getCommand().equals("teams_by_city")) {

                for (Team team : teams) {
                    if (cmd.getParameters()[0].equalsIgnoreCase(team.getLocation())) {
                        System.out.println(team.getTeam_ID() + " " + team.getLocation() + " " + team.getName() + " " + team.getLeague());
                    }

                }
            }else if (cmd.getCommand().equals("load_coaches")) {

                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(new File(cmd.getParameters()[0])));

                    //noinspection UnusedAssignment
                    String readLine = bufferedReader.readLine();

                    do{

                        readLine = bufferedReader.readLine();

                        if(readLine == null)
                            break;

                        String[] data = readLine.split(",");

                        Coach coach = new Coach();

                        coach.setCoach_ID(data[0]);
                        coach.setFirst_name(data[2]);
                        coach.setLast_name(data[3]);
                        coach.setPlayoff_loss(Integer.parseInt(data[7]));
                        coach.setSeason(Integer.parseInt(data[1]));
                        coach.setSeason_loss (Integer.parseInt(data[5]));
                        coach.setPlayoff_win(Integer.parseInt(data[6]));
                        coach.setSeason_win(Integer.parseInt(data[4]));
                        coach.setTeam(data[8]);

                        coaches.add(coach);

                    }while (true);

                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (cmd.getCommand().equals("load_teams")) {

                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(new File(cmd.getParameters()[0])));

                    //noinspection UnusedAssignment
                    String readLine = bufferedReader.readLine();

                    do{

                        readLine = bufferedReader.readLine();

                        String[] data = readLine.split(",");
                        Team team = new Team();
                        team.setLeague(String.valueOf(data[0]).charAt(0));
                        team.setLocation(data[1]);
                        team.setName(data[2]);
                        team.setTeam_ID(data[3]);

                        teams.add(team);

                    }while (readLine != null);

                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (cmd.getCommand().equals("best_coach")) {
                int max = -1;
                Coach coach1 = null;
                for( Coach coach : coaches )
                {
                    int netwins = (coach.getPlayoff_win()-coach.getPlayoff_loss()) + (coach.getSeason_win() - coach.getSeason_loss());
                    if(netwins > max){
                        max = netwins;
                        coach1 = coach;
                    }

                }

                if(coach1 != null) {

                    System.out.println(coach1.getCoach_ID() + " " + coach1.getSeason() + " " + coach1.getFirst_name() +
                            " " + coach1.getLast_name() + " " + coach1.getSeason_win() +
                            " " + coach1.getSeason_loss() + " "
                            + coach1.getPlayoff_win() + " " + coach1.getPlayoff_loss() + " " + coach1.getTeam());

                }else{

                    System.out.println("not found");

                }

            } else if (cmd.getCommand().equals("search_coaches")) {

                List<Coach> matchCoaches = new ArrayList<>();

                for(String p : cmd.getParameters()){

                    String[] param = p.split("=");

                    if(param[0].equalsIgnoreCase("first_name")){

                        for(Coach coach : coaches) {
                            if(param[1].equals(coach.getFirst_name())) {
                                matchCoaches.add(coach);
                            }
                        }

                    }else if(param[0].equalsIgnoreCase("last_name")){

                        for(Coach coach : coaches) {
                            if(param[1].equals(coach.getLast_name())) {
                                matchCoaches.add(coach);
                            }
                        }

                    }

                }

                for(Coach coach1 : matchCoaches){

                    System.out.println(coach1.getCoach_ID() + " " + coach1.getSeason() + " " + coach1.getFirst_name() +
                            " " + coach1.getLast_name() + " " + coach1.getSeason_win() +
                            " " + coach1.getSeason_loss() + " "
                            + coach1.getPlayoff_win() + " " + coach1.getPlayoff_loss() + " " + coach1.getTeam());


                }

            } else if (cmd.getCommand().equals("exit")) {
                System.out.println("Leaving the database, goodbye!");
                break;
            } else if (cmd.getCommand().equals("")) {
            } else {
                System.out.println("Invalid Command, try again!");
            }

            if (result) {
                // ...
            }

            System.out.print("> ");
        }
    }

    private boolean doHelp() {
        System.out.println("add_coach ID SEASON FIRST_NAME LAST_NAME SEASON_WIN ");
        System.out.println("SEASON_LOSS PLAYOFF_WIN PLAYOFF_LOSS TEAM - add new coach data");
        System.out.println("add_team ID LOCATION NAME LEAGUE - add a new team");
        System.out.println("print_coaches - print a listing of all coaches");
        System.out.println("print_teams - print a listing of all teams");
        System.out.println("coaches_by_name NAME - list info of coaches with the specified name");
        System.out.println("teams_by_city CITY - list the teams in the specified city");
        System.out.println("load_coach FILENAME - bulk load of coach info from a file");
        System.out.println("load_team FILENAME - bulk load of team info from a file");
        System.out.println("best_coach SEASON - print the name of the coach with the most netwins in a specified season");
        System.out.println("search_coaches field=VALUE - print the name of the coach satisfying the specified conditions");
        System.out.println("exit - quit the program");
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new P1().run();
    }


}
