
///////////////////////////////////////////////////////
// Name: Thomas Gillis
// 
// Date: 3/1/22
//
// Methods:
//      ScheduleSeason() : constructor
//      addNames(int) : allows the user to replace the default names with user selected team names
//
// 
//
///////////////////////////////////////////////////////

import javax.swing.JOptionPane;

public class ScheduleSeason {
    private int sched[][];
    private String squads[];
    private int tmpTeams; // number of teams in the schedule
    private int weeks; // how many weeks of games

    private final String DEFAULT_TEAM_NAME = "TEAM_";
    private final char SPACE = '_';

    public ScheduleSeason(int size) {
        squads = new String[size];
        for (int i = 0; i < size; i++) {
            if (i < 10)
                squads[i] = DEFAULT_TEAM_NAME + i;
            else
                squads[i] = DEFAULT_TEAM_NAME + SPACE + i;

        }
        tmpTeams = size;
        makeSchedule();
    }// end ScheduleSeason()

    // allows the user to replace the default names with user selected team names
    // FIXME:
    public void addNames(String name, int position) {
        squads[position] = name;
    }// end addNames()

    // prints out the teams organized weekly
    // FIXME:
    public void printWeekly() {
        int match;// holds the array location for the matching game
        for (int i = 0; i < size - 1; i++) {
            System.out.println("WEEK " + (i + 1) + ":");
            for (int j = 0; j < squads.length; j++) {
                match = match(j, i);
                System.out.println(squads[j] + " vs. " + squads[match]);
            }
        }
    }// end printWeekly()

    private int match(int key, int week) {
        boolean found;
        int index;
        int temp;

        found = false;
        index = 0;
        temp = -1; // if key is not found, temp returns a value outside of the range, notifying
                   // that the search failed

        while (index < howMany && !found) {
            if (sched[index][week] == key) {
                found = true;
                temp = index;
            }
        }
        return temp;
    }

    // prints out the schedule organized by team
    // FIXME:
    public void printByTeams() {
    }// end printByTeams()

    // creates a 2D array that displays the schedule
    // FIXME:
    private void makeSchedule() {
        int row,
                col,
                count,
                lastCol,
                start,
                numTeams;

        numTeams = tmpTeams;
        if (numTeams % 2 == 1)
            numTeams++;
        sched = new int[numTeams][numTeams - 1]; // -1 because you don't play against yourself
        for (row = 0; row < numTeams; row++) {
            for (col = 0; col < numTeams - 1; col++) {
                sched[row][col] = -1;
            } // end col
        } // end row
        lastCol = numTeams - 2; // no (n-1)st column
        for (row = 0; row < numTeams - 1; row++) {
            start = row + 1;
            for (col = 0; col < numTeams - 1; col++) {
                if (sched[row][col % (numTeams - 1)] == -1) {
                    if (col == lastCol) {
                        sched[row][col % (numTeams - 1)] = numTeams - 1;
                        sched[numTeams - 1][col % (numTeams - 1)] = row;
                    } else { // 'regular' case of next team added to this team's schedule
                        sched[row][col % (numTeams - 1)] = start;
                        // FIXME: ArrayIndexOutOfBoundsException
                        sched[start][col % (numTeams - 1)] = row;
                        start++;
                    }
                } // end outer if
            } // end col
            if (lastCol == numTeams - 2)
                lastCol = 1;
            else
                lastCol = (lastCol + 2) % (numTeams - 1);
        } // end row
    }// end makeSchedule()
}// end class
