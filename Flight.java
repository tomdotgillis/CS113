
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

//TODO: write the toString method for the Flight object

import javax.swing.JOptionPane;

public class Flight {
    private String takeOff;
    private String landing;
    private int distance;

    public Flight(String start, String land, int miles) {
        reset();
        takeOff = start;
        landing = land;
        distance = miles;
    }

    // returns a
    public String toString() {
        return "FLIGHT FROM " + takeOff + " TO " + landing + " IS " + distance + " MILES";
    }

    public String getTakeOff() {
        return takeOff;
    }

    public String getLanding() {
        return landing;
    }

    public int getDistance() {
        return distance;
    }

    private void reset() {
        takeOff = null;
        landing = null;
        distance = 0;
    }
}
