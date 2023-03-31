class Main {
    public static void main(String[] args) {
        String[] teams = { "Team 1", "Team 2", "Team 3", "Team 4" };
        System.out.println("Match Schedule:");
        for (int i = 0; i < teams.length - 1; i++) {
            System.out.println("Day" + (i + 1));
            for (int j = 0; j < teams.length / 2; j++) {
                System.out.println(teams[j] + " vs " + teams[teams.length - 1 - j]);
            }
            String temp = teams[1];
            for (int k = 1; k < teams.length - 1; k++) {
                teams[k] = teams[k + 1];
            }
            teams[teams.length - 1] = temp;
            System.out.println();
        }
    }
}