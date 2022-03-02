
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

public class UseScheduleSeason {
    // FIXME:
    public static void main(String[] args) {

        String input;
        int teamCt;
        input = JOptionPane.showInputDialog(null, "How Many Teams", "Round Robin",
                JOptionPane.QUESTION_MESSAGE);
        teamCt = Integer.parseInt(input);
        ScheduleSeason thisSzn = new ScheduleSeason(teamCt);
        input = JOptionPane.showInputDialog(null, "Would you like to Pick the Names", "Round Robin",
                JOptionPane.QUESTION_MESSAGE);

        if (input.equals("yes")) {
            for (int i = 0; i < teamCt; i++) {
                input = JOptionPane.showInputDialog(null, "Pick the Names", "Round Robin",
                        JOptionPane.QUESTION_MESSAGE);
                thisSzn.addNames(input, i);
            }
        }
        thisSzn.printWeekly();
        // thisSzn.printTest();
        thisSzn.printWeekly();
        System.out.println("");
        System.out.println("-----------------");
        System.out.println("All done for now.");
        System.out.println("-----------------");
        System.out.println("");
        System.exit(0);
    }

}

/*
 * public static void main(String[] args) {
 * int swag = 6;
 * 
 * for (int i = 0; i < swag; i++) {
 * for (int j = 1; j < swag; j++) {
 * System.out.print(schedule[i][j] + " ");
 * }
 * System.out.println("");
 * }
 * 
 * }
 */