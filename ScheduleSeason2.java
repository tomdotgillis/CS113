
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

public class ScheduleSeason2 {
    private int sched[][];// array that holds the schedule for a league
    private int newSched[][];// array that holds the reorganized schedule
    private int finalSched[][];// array that holds the resized, reorganized schedule
    private String squads[]; // array that holds the list of teams playing in said league
    private int matches[]; // array that holds the ID for teams already scheduled for that week
    private int isPlaced[];
    private int increment = 0;
    private int tmpTeams; // number of teams in the schedule
    private boolean odd;
    private final String DEFAULT_TEAM_NAME = "TEAM_"; // default team name
    private final String END_STRING = "DONE";
    private final char SPACE = '_'; // character that is added if team # goes above 9

    // constructor
    public ScheduleSeasonTEST(int size) {
        int newSize = size;
        if (size % 2 == 1)
            newSize = newSize + 1;
        squads = new String[newSize];
        matches = new int[newSize];
        for (int i = 0; i < newSize; i++) {
            if (i < 10)
                squads[i] = DEFAULT_TEAM_NAME + i;
            else
                squads[i] = DEFAULT_TEAM_NAME + SPACE + i;

        }
        tmpTeams = size;
        odd = false;
        setMatch();
        makeSchedule();
    }// end constructor

    // allows the user to replace the default names with user selected team names
    public void addNames(String name, int position) {
        squads[position] = name;
    }// end addNames()

    // prints out the teams organized weekly
    public void printWeekly() {
        int oddAdd = 0;
        int match;// holds the array location for the matching game
        for (int i = 0; i < tmpTeams - 1; i++) {
            System.out.println("\nWEEK " + (i + 1) + ":");
            if (odd == true)
                oddAdd = 1;
            for (int j = 0; j < tmpTeams + oddAdd; j++) {
                match = match(j, i);
                if (matched(match, j) == false) {
                    if (j == tmpTeams)
                        System.out.println(squads[match] + " vs. NO GAME");
                    else if (match == tmpTeams)
                        System.out.println(squads[j] + " vs. NO GAME");
                    else
                        System.out.println(squads[j] + " vs. " + squads[match]);
                    matches[j] = match;
                } // print schedule
            } // end cols
            setMatch();// resets the array of used numbers
        } // end weeks
    }// end printWeekly()

    // prints out the schedule organized by team
    public void printByTeams() {
        int oddAdd = 0;
        for (int i = 0; i < tmpTeams; i++) {
            System.out.println("\n" + squads[i] + "\n----");
            if (odd == true)
                oddAdd = 1;
            for (int j = 0; j < tmpTeams - 1 + oddAdd; j++) {
                if (sched[i][j] == tmpTeams)
                    System.out.println("WEEK " + j + ": NO GAME");
                else
                    System.out.println("WEEK " + j + ": " + squads[sched[i][j]]);
            }
        }
    }// end printByTeams()

    public void printWeeklyPartial() {
        int oddAdd = 0;
        int match;// holds the array location for the matching game
        for (int i = 0; i < increment + 1; i++) {
            System.out.println("\nWEEK " + (i + 1) + ":");
            if (odd == true)
                oddAdd = 1;
            for (int j = 0; j < tmpTeams + oddAdd; j++) {
                match = matchPart(j, i);
                if (matched(match, j) == false) {
                    if (j == tmpTeams)
                        System.out.println(squads[match] + " vs. NO GAME");
                    else if (match == tmpTeams)
                        System.out.println(squads[j] + " vs. NO GAME");
                    else
                        System.out.println(squads[j] + " vs. " + squads[match]);
                    matches[j] = match;
                } // print schedule
            } // end cols
            setMatch();// resets the array of used numbers
        } // end weeks
    }

    public void printByTeamsPartial() {
        int oddAdd = 0;
        for (int i = 0; i < tmpTeams; i++) {
            System.out.println("\n" + squads[i] + "\n----");
            if (odd == true)
                oddAdd = 1;
            for (int j = 0; j < increment - 1 + oddAdd; j++) {
                if (newSched[i][j] == tmpTeams)
                    System.out.println("WEEK " + j + ": NO GAME");
                else
                    System.out.println("WEEK " + j + ": " + squads[newSched[i][j]]);
            }
        }
    }

    public void buildWeekly() {
        newSched = new int[tmpTeams][tmpTeams - 1];
        isPlaced = new int[tmpTeams];
    }

    public int addGame(String tm1, String tm2) {
        int place1, place2;
        int location;
        place1 = -1;
        place2 = -1;
        if (tm1.equals(END_STRING) && tm2.equals(END_STRING)) {
            cleanUp();
            return -99;
        }
        for (int i = 0; i < squads.length; i++) {
            if (squads[i].equals(tm1))
                place1 = i;
            if (squads[i].equals(tm2))
                place2 = i;
        }
        for (int i = 0; i < tmpTeams - 1; i++) {
            if (sched[place1][i] == place2) {
                location = i;
                if (hasBeenPlaced(location) == false) {
                    addCol(location);
                    return location;
                } else {
                    location = location * -1;
                    return location;
                } // end else
            } // end if
        } // end for

        return -99;
    }
    // -PRIVATE METHODS-

    // creates a 2D array that displays the schedule
    private void makeSchedule() {
        int row,
                col,
                count,
                lastCol,
                start,
                numTeams;
        numTeams = tmpTeams;
        if (numTeams % 2 == 1) {
            numTeams++;
            odd = true;
        }
        sched = new int[numTeams][numTeams - 1];

        for (row = 0; row < numTeams; row++)
            for (col = 0; col < numTeams - 1; col++)
                sched[row][col] = -1;
        lastCol = numTeams - 2; // no (n-1)st column

        for (row = 0; row < numTeams; row++) {
            start = row + 1;
            for (count = 0, col = row; count < numTeams - 1; count++, col = (col + 1) % (numTeams - 1)) {
                if (sched[row][col % (numTeams - 1)] == -1) {
                    if (col == lastCol) {
                        sched[row][col % (numTeams - 1)] = numTeams - 1;
                        sched[numTeams - 1][col % (numTeams - 1)] = row;
                    } // end inner if
                    else {
                        sched[row][col % (numTeams - 1)] = start;
                        sched[start][col % (numTeams - 1)] = row;
                        start++;
                    } // end inner else
                } // end outer if
            } // end col loop
            if (lastCol == numTeams - 2)
                lastCol = 1;
            else
                lastCol = (lastCol + 2) % (numTeams - 1);
        } // end row loop
    }// end makeSchedule()

    // finds the array location for the opponent
    private int match(int key, int week) {
        boolean found;
        int index;
        int temp;

        found = false;
        index = 0;
        temp = -1; // if key is not found, temp returns a value outside of the range, notifying
                   // that the search failed

        while (index < tmpTeams && !found) {
            if (sched[index][week] == key) {
                found = true;
                temp = index;

            }
            index++;
        }
        return temp;
    }

    private int matchPart(int key, int week) {
        boolean found;
        int index;
        int temp;

        found = false;
        index = 0;
        temp = -1; // if key is not found, temp returns a value outside of the range, notifying
                   // that the search failed

        while (index < tmpTeams && !found) {
            if (newSched[index][week] == key) {
                found = true;
                temp = index;

            }
            index++;
        }
        return temp;
    }

    // finds whether or not a team's game has already been printed
    private boolean matched(int key, int key2) {
        int index;
        boolean found;
        index = 0;
        found = false;
        while (index < tmpTeams && !found) {
            if (matches[index] == key || matches[index] == key2) {
                found = true;
            }
            index++;
        }
        return found;
    }

    // Resets the list of found opponents
    private void setMatch() {
        for (int i = 0; i < tmpTeams; i++)
            matches[i] = -1;
    }

    // adds the used column to the placed[] array
    private void addCol(int nextCol) {
        isPlaced[increment] = nextCol;
        addToNewSchedule(nextCol, increment);
        increment++;
    }// end addCol

    // finalize the partial array
    private void cleanUp() {
        finalSched = new int[tmpTeams][increment - 1];
        for (int i = 0; i < tmpTeams; i++) {
            for (int j = 0; j < increment - 1; j++) {
                finalSched[i][j] = newSched[i][j];
            } // end col
        } // end row
    }// end cleanUp()

    // has the column already been placed?
    private boolean hasBeenPlaced(int potCol) {
        for (int i = 0; i < tmpTeams; i++) {
            if (isPlaced[i] == potCol)
                return true;
        }
        return false;
    }

    // adds the new schedule data to the newSched[][] array
    private void addToNewSchedule(int nextCol, int pos) {
        for (int i = 0; i < tmpTeams; i++) {
            newSched[i][pos] = sched[i][nextCol];
        }
    }
}
// end class
