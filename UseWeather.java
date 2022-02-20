
///////////////////////////////////////////////////////
// Name: Thomas Gillis
// 
// Date: 2/22/22
//
// Methods: 
//      + main(String[] args) - void - utilizes the Weather 
//                            class to find various data 
//                            about the weather in Burlington, 
//                            Vermont over a specific time.
// 
//      - isInvalidResponseYear() - int - asks the user  to provide
//                                        enter a valid year
//
//      - isInvalidResponseMonth() - int - asks the user to create
//                                         enter a valid month (1-12)
//
//      - inRangeYear() - boolean -  returns true if the user 
//                                   provided a valid year
//
//      - inRangeMonth() - boolean - returns true if the user
//                                   provided a valid month
//
///////////////////////////////////////////////////////

import javax.swing.JFileChooser;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;

public class UseWeather {
        public static final int YEAR_START = 1970; // starting year for this specific data set
        public static final int YEAR_END = 1989; // ending year for this specific data set
        public static final int MONTH_START = 1; // starting month for this specific data set
        public static final int MONTH_END = 12; // ending month for this specific data set

        public static void main(String[] args) throws Exception {
                double[] monthlyRain, // array that holds te necessary amount of doubles - monthlyRain()
                                monthlySnow, // array that holds te necessary amount of doubles - monthlySnow()
                                yearlyRain, // array that holds te necessary amount of doubles - yearlyRain()
                                yearlySnow, // array that holds te necessary amount of doubles - yearlySnow()
                                anualHighTemp;// array that holds te necessary amount of doubles - anualHighTemp()
                int start, // year to START searching
                                end, // year to STOP searhing
                                month;// month to specifically search for
                String inputString; // String representation of the next line in the data file being input
                int input;// integer representation of the next datapoint in the string 'inputString'
                Weather weather = new Weather();
                boolean isInvalidResponse = false;
                String[] monthStrings = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST",
                                "SEPTEMBER",
                                "OCTOBER", "NOVEMBER", "DECEMBER" };
                inputString = JOptionPane.showInputDialog(null,
                                "Would you like to:\n[1] Monthly Rain Totals\n[2] MonthlySnowfall\n[3] Yearly Rainfall\n Yearly Snowfall\n[4] Yearly Temperature Difference",
                                "Weather Data", JOptionPane.QUESTION_MESSAGE);
                input = Integer.parseInt(inputString);

                // Monthly Rainfall Data
                if (input == 1) {
                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to start?",
                                        "Weather Data", JOptionPane.QUESTION_MESSAGE);
                        start = Integer.parseInt(inputString);

                        // is valid year
                        while (!inRangeYear(start))
                                start = isInvalidResponseMonth();

                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to end?",
                                        "Weather Data", JOptionPane.QUESTION_MESSAGE);
                        end = Integer.parseInt(inputString);

                        // is valid year
                        while (inRangeYear(end) == false)
                                end = isInvalidResponseMonth();

                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What month to look at?\n[1-12] Jan-Dec",
                                        "Weather Data", JOptionPane.QUESTION_MESSAGE);
                        month = Integer.parseInt(inputString);

                        // is valid month
                        while (inRangeMonth(month) == false)
                                month = isInvalidResponseMonth();

                        monthlyRain = weather.monthlyRainfall(start, end, month);
                        System.out.println("- -\n" + monthStrings[month - 1] + "\n- -");
                        for (int i = 0; i < monthlyRain.length; i++) {
                                System.out.println("RAINFALL FOR " + monthStrings[month - 1] + " IN " + (start + i)
                                                + ": "
                                                + String.format("%.3f", monthlyRain[i]));
                        }
                } // end monthlyRain possible loop.

                // Monthly Snowfall Data
                else if (input == 2) {

                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to start?",
                                        "SNOWFALL Data", JOptionPane.QUESTION_MESSAGE);
                        start = Integer.parseInt(inputString);

                        // is valid input
                        while (!inRangeYear(start))
                                start = isInvalidResponseYear();

                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to end?",
                                        "SNOWFALL Data", JOptionPane.QUESTION_MESSAGE);
                        end = Integer.parseInt(inputString);

                        // is valid input
                        while (!inRangeYear(end))
                                end = isInvalidResponseYear();

                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What month to look at?\n[1-12] Jan-Dec",
                                        "SNOWFALL Data", JOptionPane.QUESTION_MESSAGE);
                        month = Integer.parseInt(inputString);

                        // is valid input
                        while (!inRangeYear(start))
                                month = isInvalidResponseMonth();
                        monthlySnow = weather.monthlySnowfall(start, end, month);
                        for (int i = 0; i < monthlySnow.length; i++) {
                                System.out.println("SNOWFALL FOR " + (start + i) + ": "
                                                + String.format("%.3f", monthlySnow[i]));
                        }
                } // end monthlyRain possible loop

                // Yearly Rainfall Data
                else if (input == 3) {
                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to start?",
                                        "YEARLY RAINFALL Data", JOptionPane.QUESTION_MESSAGE);
                        start = Integer.parseInt(inputString);

                        // is valid input
                        while (!inRangeYear(start))
                                start = isInvalidResponseYear();

                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to end?",
                                        "YEARLY RAINFALL Data", JOptionPane.QUESTION_MESSAGE);
                        end = Integer.parseInt(inputString);

                        // is valid input
                        while (!inRangeYear(start))
                                end = isInvalidResponseYear();

                        // final printout
                        yearlyRain = weather.yearlyRainfall(start, end);
                        for (int i = 0; i < yearlyRain.length; i++) {
                                System.out.println("YEARLY RAINFALL FOR " + (start + i) + ": "
                                                + String.format("%.3f", yearlyRain[i]));
                        }

                } // end yearlyRainfall possible loop

                // Yearly Snowfall Data
                else if (input == 4) {
                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to start?",
                                        "YEARLY SNOWFALL DATA", JOptionPane.QUESTION_MESSAGE);
                        start = Integer.parseInt(inputString);

                        // is valid input
                        while (!inRangeYear(start))
                                start = isInvalidResponseYear();
                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to end?",
                                        "YEARLY SNOWFALL DATA", JOptionPane.QUESTION_MESSAGE);
                        end = Integer.parseInt(inputString);

                        // is valid input
                        while (!inRangeYear(end))
                                end = isInvalidResponseYear();

                        yearlySnow = weather.yearlySnowfall(start, end);
                        for (int i = 0; i < yearlySnow.length; i++) {
                                System.out.println("YEARLY SNOWFALL FOR " + (start + i) + ": "
                                                + String.format("%.3f", yearlySnow[i]));
                        }

                } // end yearlySnowfall possible loop

                // Yearly High Temperature Data
                else if (input == 5) {
                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to start?",
                                        "YEARLY HIGH TEMP DATA", JOptionPane.QUESTION_MESSAGE);
                        start = Integer.parseInt(inputString);

                        // is valid input
                        while (!inRangeYear(start))
                                start = isInvalidResponseYear();
                        // ask user for next input
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to end?",
                                        "YEARLY HIGH TEMP DATA", JOptionPane.QUESTION_MESSAGE);
                        end = Integer.parseInt(inputString);

                        // is valid input
                        while (!inRangeYear(start))
                                start = isInvalidResponseYear();
                        anualHighTemp = weather.yearlyHighTemp(start, end);
                        for (int i = 0; i < anualHighTemp.length; i++) {
                                System.out.println("YEARLY HIGH TEMP FOR " + (start + i) + ": "
                                                + String.format("%.3f", anualHighTemp[i]) + "⁰" + "F");
                        }

                } // end yearlySnowfall possible loop
                System.out.println("");
                System.out.println("-----------------");
                System.out.println("All done for now.");
                System.out.println("-----------------");
                System.out.println("");
                System.exit(0);
        }

        // ensures that the program will not continue until a valid input has been
        // received
        private static int isInvalidResponseYear() {
                int input; // holds the user's response in an int
                String inputString; // holds the user's response in a string

                // was the user's input a valid year
                inputString = JOptionPane.showInputDialog(null,
                                "That input was invalid, \n please enter a valid number",
                                "Weather data", JOptionPane.QUESTION_MESSAGE);
                input = Integer.parseInt(inputString);
                return input;
        }// end isInvalidResponse()

        // ensures that the program will not continue until a valid input has been
        // received
        private static int isInvalidResponseMonth() {
                int input; // holds the user's response in an int
                String inputString; // holds the user's response in a string

                // was the user's input a valid year
                inputString = JOptionPane.showInputDialog(null,
                                "That input was invalid, \nplease enter a valid number",
                                "Weather data", JOptionPane.QUESTION_MESSAGE);
                input = Integer.parseInt(inputString);
                return input;
        }// end isInvalidResponse()

        // checks whether or not the user's input is within the year range
        private static boolean inRangeYear(int user) {
                if (user < YEAR_START || user > YEAR_END)
                        return false;
                return true;
        }

        // checks whether or not the user's input is within the year range
        private static boolean inRangeMonth(int user) {
                if (user < MONTH_START || user > MONTH_END)
                        return false;
                return true;
        }
}
