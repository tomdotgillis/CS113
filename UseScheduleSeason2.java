
///////////////////////////////////////////////////////
// Name: Thomas Gillis
// 
// Date: 3/1/22
//
// Methods: 
//
// 
//
// 
//
///////////////////////////////////////////////////////

import javax.swing.JOptionPane;

public class UseScheduleSeason2 {
    // FIXME:
    public static void main(String[] args) {

        String input, team1, team2;
        int teamCt, output;
        boolean valid, complete;
        valid = false;
        complete = false;
        input = JOptionPane.showInputDialog(null, "How Many Teams", "Round Robin",
                JOptionPane.QUESTION_MESSAGE);
        teamCt = Integer.parseInt(input);
        ScheduleSeasonTEST thisSzn = new ScheduleSeasonTEST(teamCt);
        input = JOptionPane.showInputDialog(null, "Would you like to Pick the Names", "Round Robin",
                JOptionPane.QUESTION_MESSAGE);

        if (input.equals("yes")) {
            for (int i = 0; i < teamCt; i++) {
                input = JOptionPane.showInputDialog(null, "Pick the Names", "Round Robin",
                        JOptionPane.QUESTION_MESSAGE);
                thisSzn.addNames(input, i);
            }
        }
        input = JOptionPane.showInputDialog(null, "Would you like a full or partial schedule?", "Round Robin",
                JOptionPane.QUESTION_MESSAGE);
        if (input.equals("full")) {
            input = JOptionPane.showInputDialog(null,
                    "How would you like to see the schedule?\n--------------------------------\n[1]By Week\n[2]By Team");
            output = Integer.parseInt(input);
            if (output == 1 || output == 2)
                valid = true;
            while (valid == false) {
                input = JOptionPane.showInputDialog(null,
                        "[" + output
                                + "] is INVALID. Please enter:\n----------------------------\n[1]By Week\n[2]By Team");
                output = Integer.parseInt(input);
                if (output == 1 || output == 2)
                    valid = true;
            }
            if (Integer.parseInt(input) == 1)
                thisSzn.printWeekly();
            else if (Integer.parseInt(input) == 2)
                thisSzn.printByTeams();
        } else {
            thisSzn.buildWeekly();
            do {
                input = JOptionPane.showInputDialog(null, "Who is playing", "Round Robin",
                        JOptionPane.QUESTION_MESSAGE);
                team1 = input;
                input = JOptionPane.showInputDialog(null, "Who is playing the " + team1 + "?", "Round Robin",
                        JOptionPane.QUESTION_MESSAGE);
                team2 = input;
                System.out.println(team1 + " and " + team2 + " play during week " + thisSzn.addGame(team1, team2));
                input = JOptionPane.showInputDialog(null, "Would you like to select another matchup?", "Round Robin",
                        JOptionPane.QUESTION_MESSAGE);
            } while (input.equals("yes"));
            input = JOptionPane.showInputDialog(null,
                    "How would you like to see the schedule?\n--------------------------------\n[1]By Week\n[2]By Team",
                    "Round Robin", JOptionPane.QUESTION_MESSAGE);
            output = Integer.parseInt(input);
            if (output == 1 || output == 2)
                valid = true;
            while (valid == false) {
                input = JOptionPane.showInputDialog(null,
                        "[" + output
                                + "] is INVALID. Please enter:\n----------------------------\n[1]By Week\n[2]By Team");
                output = Integer.parseInt(input);
                if (output == 1 || output == 2)
                    valid = true;
            }
            if (Integer.parseInt(input) == 1)
                thisSzn.printWeeklyPartial();
            else if (Integer.parseInt(input) == 2)
                thisSzn.printByTeamsPartial();
        }
        System.out.println("");
        System.out.println("-----------------");
        System.out.println("All done for now.");
        System.out.println("-----------------");
        System.out.println("");
        System.exit(0);
    }

}