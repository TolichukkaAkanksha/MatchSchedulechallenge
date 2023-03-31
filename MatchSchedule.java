import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Team> teams=new ArrayList<>();
        teams.add(new Team("Telugu Titans"));
        teams.add(new Team("Bengaluru bulls"));
        teams.add(new Team("Patna pirates"));
        teams.add(new Team("Bengal Warriors"));
        teams.add(new Team("U Mumba"));

        List<Match> matches=Scheduler.createSchedule(teams);
        System.out.println(matches);
        Simulator.playMatches(matches);
        Simulator.showPointsTable(teams, matches);
    }

    static class Match {
        private Team team1;
        private Team team2;
        private Team winner;
        private Team loser;

        public Match(Team team1, Team team2) {
            this.team1 = team1;
            this.team2 = team2;
        }

        public Team getTeam1() {
            return team1;
        }

        public Team getTeam2() {
            return team2;
        }

        public Team getWinner() {
            return winner;
        }

        public void setWinner(Team winner) {
            this.winner = winner;
        }

        public Team getLoser() {
            return loser;
        }

        public void setLoser(Team loser) {
            this.loser = loser;
        }

        @Override
        public String toString() {
            String matchDescription = team1 + " Vs " + team2;
            if (winner != null) {
                return matchDescription + "\n Winner: " + this.winner + ", Loser: " + this.loser;
            } else {
                return matchDescription;
            }
        }
    }

    static class Scheduler {
        public static List<Match> createSchedule(List<Team> teams) {
            List<Match> matches = new ArrayList<>();
            for (int i = 0; i < teams.size(); i++) {
                for (int j = i + 1; j < teams.size(); j++) {
                    Match match = new Match(teams.get(i), teams.get(j));
                    matches.add(match);
                }
            }
            return matches;
        }
    }

    static class Simulator {
        public static void playMatches(List<Match> matches) {
            for (Match match : matches) {
                int random = (int) ((Math.random() * 10) + 2);
                if (random % 2 == 0) {
                    match.setWinner(match.getTeam1());
                    match.setLoser(match.getTeam2());
                } else {
                    match.setWinner(match.getTeam2());
                    match.setLoser(match.getTeam1());
                }
            }
        }

        public static void showPointsTable(List<Team> teams, List<Match> matches) {
            System.out.println("Point Table");
            for (Team team : teams) {
                int wonGames = (int) matches.stream().filter(m -> m.getWinner().equals(team)).count();
                int lostGames = (int) matches.stream().filter(m -> m.getLoser().equals(team)).count();
                int totalMatches = wonGames + lostGames;
                int points = wonGames * 2 + lostGames;
                System.out.println("-------------------------------------------------------------");
                System.out.printf("Team: %s | Played: %d  | Won: %d | Lost: %d | Points: %d%n",
                        team, totalMatches, wonGames, lostGames, points);
            }
            System.out.println("Win: 3 points,Lost: 1 points");
        }
    }

    static class Team {
        private String name;

        public Team(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}