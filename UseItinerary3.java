
///////////////////////////////////////////////////////
// Name: Thomas Gillis
// 
// Date: 3/1/22
//
// Purpose - To ouput the user's desired flight if it exists.
//
//
// Methods: 
//    main() : Selects the file containing flight 
//             information and organizes it into an ArrayList
//
//    questions() : boolean - Asks the user a string of questions to 
//                            select what flight they are looking for
//        
//    search() : boolean - Looks within the ArrayList to find the 
//                         user's desired flight
//
///////////////////////////////////////////////////////

import java.util.*;
import javax.swing.*;

public class UseItinerary3 {
    private static String userIn; // user input, (departing city & arrival city)
    private static String userStart; // user's desired departure location
    private static String userLand; // user's desired arrival location
    private static ArrayList<Flight> flightList = new ArrayList<Flight>();// ArrayList of Flight
    private static ArrayList<Itinerary> allPlans = new ArrayList<Itinerary>(); // holds the current list of
                                                                               // iteraries.
    private static ArrayList<Itinerary> newPlans = new ArrayList<Itinerary>(); // transitional array that holds the
                                                                               // updated list.
    private static Itinerary current;
    private static Itinerary temporary;
    private static Flight thisFlight; // temporary Flight object that holds the Flight being searched
    private static String departure;
    private static String testDepart;
    private static final int MAX_LEGS = 10;

    public static void main(String[] args) throws Exception {
        String takeOff, // departing city
                landing, // arrival city
                inputString; // all info in one string (takeOff + landing + distance)
        Itinerary thisTrip;
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
        } // end while
        /* READ IN DATA DONE */

        for (int i = 0; i < flightList.size(); i++)
            System.out.println("");

        // keep selecting flights
        do {
            another = questions();
        } while (another == true);

        // program complete
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
        String finalPlan;
        Flight realFlight; // does the user's desired flight exist
        userIn = JOptionPane.showInputDialog(null, "Where would you like to takeoff?", "Cheaper  Fares  Airlines",
                JOptionPane.QUESTION_MESSAGE);
        userIn = userIn.toUpperCase();
        if (userIn.equals("STOP")) {
            System.out.println("\nSTOPPED");
            return false;
        }
        userIn = userIn.toUpperCase();
        userStart = userIn.substring(0, 4);

        userIn = JOptionPane.showInputDialog(null, "Where would you like to fly to from " + userStart + "?",
                "Cheaper Fares Airlines", JOptionPane.QUESTION_MESSAGE);
        userIn = userIn.toUpperCase();
        if (userIn.equals("STOP"))
            return false;
        userIn = userIn.toUpperCase();
        userLand = userIn.substring(0, 4);
        // is the flight real?

        realFlight = search(userStart, userLand);

        // is there a direct flight?
        if (realFlight == null) {
            System.out.println("NO VALID FLIGHT");
            System.out.println(connectionFound(userStart, userLand));

        } else {
            System.out.println(realFlight.toString());
            // System.out.println(thisFlight.toString());
        }

        return true;
    }

    // does the user's desired flight exist, YES return true, NO return false
    private static Flight search(String depart, String landing) {
        String temp; // holds the details from the arraylist of itinerary objects.
        for (int i = 0; i < flightList.size(); i++) {
            // is there a departing flight
            thisFlight = flightList.get(i);
            temp = thisFlight.getTakeOff();
            if (temp.equals(depart)) {
                // is there a flight?
                temp = thisFlight.getLanding();
                if (temp.equals(landing)) {
                    return thisFlight;
                } // end direct
            } // end departure
        } // end for
        return null;
    } // end search()

    private static String connectionFound(String departure, String landing) {
        boolean found;
        boolean validCount;
        String temp;
        String answer;
        final String NO_FLIGHT_FOUND = "NO VALID FLIGHT FOUND BETWEEN ";
        int index;
        int index2;
        int index3;
        int newPlanSize;
        int successPOS;
        index3 = 0;
        found = false; // flight is found
        validCount = true; // will not overfill the arrays
        // cycles through the arraylist of Flight objects
        for (int i = 0; i < flightList.size(); i++) {
            // adds the direct flights to the ArrayList of Itinerary objects
            if ((flightList.get(i).getTakeOff()).equals(departure)) {
                current = new Itinerary(flightList.get(i));
                allPlans.add(current);
            }
        } // end for

        // adds new elements to the ArrayList of Itinerary objects
        // repeats until connection found
        while (found == false && validCount == true) {
            // cycles through the existing elements in the ArrayList of Itinerary objects
            for (int i = 0; i < allPlans.size(); i++) {
                for (int j = 0; j < flightList.size(); j++) {
                    // adding flights from last flights landing airport
                    if ((flightList.get(j).getTakeOff()).equals(allPlans.get(i).lastLeg().getLanding())) {
                        current = allPlans.get(i).addLeg(flightList.get(j));
                        // too many legs
                        if (current == null)
                            validCount = false;
                        else {
                            newPlans.add(current);
                            newPlanSize = newPlans.size();
                            // successful flight plan found
                            if ((newPlans.get(newPlanSize - 1).lastLeg()).getLanding().equals(landing)) {
                                found = true;
                                allPlans = newPlans;
                                answer = allPlans.get(newPlanSize - 1).toString();
                                return answer;
                            }
                        } // end else
                    } // end if
                } // end for
            } // end for
              // updating the ArrayList of Itinerary objects
            allPlans = newPlans;
            // clearing the temporary ArrayList of Itinerary objects
            newPlans.clear();
        } // end while
        return (NO_FLIGHT_FOUND + departure + " AND " + landing);
    } // end connectionFound()
}
