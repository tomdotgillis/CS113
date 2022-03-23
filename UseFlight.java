
///////////////////////////////////////////////////////
// Name: Thomas Gillis
// 
// Date: 3/1/22
//
// Methods: 
//    main() : Selects the file containing flight 
//             information and organizes it into an ArrayList
//
//    questions() : boolean - Asks the user a string of questions to 
//                            select what flight they are looking for
//        
//    search() : boolean - looks within the ArrayList to find the 
//                         user's desired flight
// 
//
///////////////////////////////////////////////////////

import java.util.*;
import java.util.concurrent.TransferQueue;

import javax.swing.*;

public class UseFlight {
    private static String userIn; // user input, (departing city & arrival city)
    private static String userStart; // user's desired departure location
    private static String userLand; // user's desired arrival location
    private static ArrayList<Flight> flightList = new ArrayList<Flight>();// ArrayList of Flight
    private static Flight thisFlight; // temporary Flight object that holds the Flight being searched
    private static int printMiles;

    public static void main(String[] args) throws Exception {
        String takeOff, // departing city
                landing, // arrival city
                inputString; // all info in one string (takeOff + landing + distance)

        int distance; // distance in miles between two cities (takeOff + landing)
        boolean another = false; // does the user want to see another flight
        StringTokenizer tokens;

        /* READ IN DATA START */
        Scanner input = new Scanner(System.in);
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("Could not find the file!");
            System.exit(-1);
        }

        java.io.File file = fileChooser.getSelectedFile();
        input = new Scanner(file);
        // chopping the data
        while (input.hasNext()) {
            inputString = input.nextLine();
            // splits the string into tokens
            tokens = new StringTokenizer(inputString);
            takeOff = tokens.nextToken();
            landing = tokens.nextToken();
            distance = Integer.parseInt(tokens.nextToken());
            distance = distance * 100;

            // places tokens into the ArrayList of Flight objects.
            flightList.add(new Flight(takeOff, landing, distance));
            // prints out all flights on the list
            // System.out.println(takeOff + " TO " + landing + " IN " + distance + "
            // MILES.");
        }
        /* READ IN DATA DONE */
        System.out.println("");
        do {
            another = questions();
        } while (another == true);
        System.out.println("");
        System.out.println("-----------------");
        System.out.println("All done for now.");
        System.out.println("-----------------");
        System.out.println("");
        System.exit(0);
    }

    // prompts the user to enter a new desired flight, returns a boolean whether or
    // not they want to select a new flight
    private static boolean questions() {
        boolean again = false; // does the user want to select a new flight

        boolean realFlight = false; // does the user's desired flight exist
        userIn = JOptionPane.showInputDialog(null, "Where would you like to takeoff?", "Cheaper  Fares  Airlines",
                JOptionPane.QUESTION_MESSAGE);
        userIn = userIn.toUpperCase();
        if (userIn.equals("STOP")) {
            System.out.println("\nSTOPPED");
            return false;
        }
        userStart = userIn.substring(0, 4);

        userIn = JOptionPane.showInputDialog(null, "Where would you like to fly to from " + userStart + "?",
                "Cheaper Fares Airlines", JOptionPane.QUESTION_MESSAGE);
        userIn = userIn.toUpperCase();
        if (userIn.equals("STOP"))
            return false;
        userLand = userIn.substring(0, 4);
        // is the flight real?
        realFlight = search(userStart, userLand);

        // the user's desired flight was not found
        if (realFlight == false) {
            JOptionPane.showMessageDialog(null, "FLIGHT FROM " + userStart + " TO " + userLand + " DOES NOT EXIST",
                    "Cheaper Fares Airlines", JOptionPane.PLAIN_MESSAGE);
            System.out.println("FLIGHT FROM " + userStart + " TO " + userLand + " DOES NOT EXIST");
        }

        return true;
    }

    // does the user's desired flight exist, YES return true, NO return false
    private static boolean search(String depart, String landing) {
        String temp; // holds the details from the arraylist of
        String response;
        int index;
        boolean found = false;
        for (int i = 0; i < flightList.size(); i++) {
            // is there a departing flight
            thisFlight = flightList.get(i);
            temp = thisFlight.getTakeOff();
            if (temp.equals(depart)) {
                // is there a flight?
                temp = thisFlight.getLanding();
                if (temp.equals(landing)) {
                    response = thisFlight.toString();
                    JOptionPane.showMessageDialog(null, thisFlight.toString(), "Cheaper Fare Airlines",
                            JOptionPane.PLAIN_MESSAGE);
                    System.out.println(thisFlight.toString());
                    return true;
                }
            }
        }
        return false;
    }

}
