
///////////////////////////////////////////////////////
// Name: Thomas Gillis
// 
// Date: 2/22/22
//
// Methods: 
//      Weather() - Constructor method
//      monthlyRainfall() - double[] - calculates the 
//                          rainfall for a particular 
//                          month over the course of 
//                          an unknown amount of years 
//
//      monthlySnowfall() - double[] - calculates the 
//                          snowfall for a particular 
//                          month over the coure of a user
//                          determined span of time
//      
//      yearlyRainfall() - double[] - calculates the 
//                         annual rainfall for a user 
//                         determined span of time
//
//      yearlySnowfall() - double[] - calculates the
//                         annual snowfall for a user
//                         determined span of time
//
//      yearlyHighTemp() - double[] - calculates the
//                         highest temperature per
//                         year during a user determined
//                         span of time
//
///////////////////////////////////////////////////////

import javax.swing.JFileChooser;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;

public class Weather {
    public final int ARRAY_SIZE = 10000; // how many weather data entries the array can hold
    public final int DATA_CATS = 8; // how many types of weather data
    private final int MONTH = 0; // denomination for 'month' data in the 'weatherData' array
    private final int DAY = 1; // denomination for 'day' data in the 'weatherData' array
    private final int YEAR = 2;// denomination for 'year' data in the 'weatherData' array
    private final int HIGH_TEMP = 3; // denomination for 'highTemp' data in the 'weatherData' array
    private final int LOW_TEMP = 4; // denomination for 'lowTemp' data in the 'weatherData' array
    private final int RAINFALL = 5; // denomination for 'rainfall' data in the 'weatherData' array
    private final int SNOWFALL = 6; // denomination for 'snowfall' data in the 'weatherData' array
    private final int SNOW_DEPTH = 7; // denomination for 'snowDepth' data in the 'weatherData' array
    private double addition; // holds the annual totals before they are entered into the array
    private int difference; // span of time between teh start year and the end year

    public double weatherData[][]; // 2D array of doubles that holds the weather data entered by the user
    public double temp[]; // 1D array of doubles that contains the annual totals for the weather data
                          // entered by the user

    // creates the necessary variables and arrays for the possible questions
    public Weather() throws Exception {
        String nextInput, // holds the next String Token held in the string 'inputString'
                inputString; // holds the next line of data from the file
        double next; // converts the data held in 'nextInput' from String to double
        int tokenCt; // counts the amount of tokens in the String
        StringTokenizer tokens; // Used to splice the input String of data
        weatherData = new double[ARRAY_SIZE][DATA_CATS]; // array that holds all of the data points
        int rows = 0, // increments through the 2D array of doubles
                cols = 0; // increments through the 2D array of doubles

        // open 'file' as in previous assignments using JFileChooser
        Scanner input = new Scanner(System.in);

        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("Could not find the file!");
            System.exit(-1);
        }
        java.io.File file = fileChooser.getSelectedFile();
        input = new Scanner(file);
        while (input.hasNext()) {
            for (rows = 0; rows < ARRAY_SIZE && input.hasNext(); rows++) {
                inputString = input.nextLine(); // 8 #'s on this line
                tokens = new StringTokenizer(inputString); // convert to STO
                                                           // STO == StringTokenizer Object
                tokenCt = tokens.countTokens();
                if (tokenCt != 8) {
                    System.out.println("Not Enough Tokens: " + inputString);
                    System.exit(-1);
                }
                for (cols = 0; cols < DATA_CATS; cols++) {
                    nextInput = tokens.nextToken(); // extracts next string from tokens object
                    next = Double.parseDouble(nextInput); // converts it!
                    if (rows < ARRAY_SIZE && cols < DATA_CATS) {
                        if (cols < DATA_CATS) {
                            weatherData[rows][cols] = next;
                        } // end if statement
                    } // end if statement
                } // end cols for loop
            } // end rows for loop
        } // end of the input loop
    } // end Weather()

    // calculates the rainfall for a particular month over the course of an unknown
    // amount of years
    public double[] monthlyRainfall(int start, int end, int month) {
        resetData();
        difference = (end - start) + 1; // span of time for the querie
        temp = new double[difference];// temp is now the length of the years being questioned
        addition = 0;
        int k = 0;
        int index = 0;
        // array of doubles get filled to the correct amount
        for (int i = 0; i < difference; i++) {
            for (index = index + 1; index < ARRAY_SIZE
                    && weatherData[index][YEAR] == weatherData[index + 1][YEAR]; index++) {
                if (weatherData[index][MONTH] == month && k <= temp.length) {
                    if (weatherData[index][RAINFALL] != -1.0)
                        addition += weatherData[index][RAINFALL];
                    // System.out.print((index + 1) + ": ");
                    // System.out.format("%.3f", addition);
                    // System.out.println("");
                    // System.out.println(addition);
                } // end month if statement
            } // end rows for loop
            temp[i] = addition;
            addition = 0;
        } // end fill array loop

        return temp;
    } // end monthlyRainfall()

    // calculates the snowfall for a particular month over the coure of a user
    // determined span of time
    public double[] monthlySnowfall(int start, int end, int month) {
        resetData();
        difference = (end - start) + 1; // span of time for the querie
        temp = new double[difference];// temp is now the length of the years being questioned
        addition = 0;
        int k = 0;
        int index = 0;
        for (int i = 0; i < difference; i++) {
            for (index = index + 1; index < ARRAY_SIZE
                    && weatherData[index][YEAR] == weatherData[index + 1][YEAR]; index++) {
                if (weatherData[index][MONTH] == month && k <= temp.length) {
                    if (weatherData[index][SNOWFALL] != -1.0)
                        addition += weatherData[index][SNOWFALL];
                    // System.out.print((index + 1) + ": ");
                    // System.out.format("%.3f", addition);
                    // System.out.println("");
                    // System.out.println(addition);
                } // end month if
            } // end rows 4
            temp[i] = addition;
            addition = 0;
        } // end fill array loop
        return temp;
    } // end monthlySnowfall()

    // calculates the annual rainfall for a user determined span of time
    public double[] yearlyRainfall(int start, int end) {
        resetData();
        int difference = (end - start) + 1;
        int index = 0;
        int index2 = 0;
        addition = 0;
        temp = new double[difference];
        while (index2 < difference) {
            addition = 0;
            for (index += 1; index < ARRAY_SIZE - 1
                    && weatherData[index][YEAR] == weatherData[index + 1][YEAR]; index++) {
                if (weatherData[index][RAINFALL] != -1.0)
                    addition += weatherData[index][RAINFALL];
            }
            temp[index2] = addition;
            index2++;
        } // end fill array loop

        return temp;
    } // end yearlyRainfall()

    // calculates the annual snowfall for a a user determined span of time
    public double[] yearlySnowfall(int start, int end) {
        resetData();
        int difference = (end - start) + 1;
        int index = 0;
        int index2 = 0;
        addition = 0;
        temp = new double[difference];
        while (index2 < difference) {
            addition = 0;
            for (index += 1; index < ARRAY_SIZE - 1
                    && weatherData[index][YEAR] == weatherData[index + 1][YEAR]; index++) {
                if (weatherData[index][SNOWFALL] != -1.0)
                    addition += weatherData[index][SNOWFALL];
            }
            temp[index2] = addition;
            index2++;
        } // end fill array loop
        return temp;
    } // end yearlySnowfall()

    // calculates the annual high temp during a user determined span of time
    public double[] yearlyHighTemp(int start, int end) {
        resetData();
        int difference = (end - start) + 1;
        int index = 0;
        int index2 = 0;
        double maxTemp;

        addition = 0;
        temp = new double[difference];
        maxTemp = weatherData[0][HIGH_TEMP];
        while (index2 < difference) {
            maxTemp = weatherData[0][HIGH_TEMP];
            addition = 0;
            for (index += 1; index < ARRAY_SIZE - 1
                    && weatherData[index][YEAR] == weatherData[index + 1][YEAR]; index++) {
                if (weatherData[index][HIGH_TEMP] > maxTemp)
                    maxTemp = weatherData[index][HIGH_TEMP];
            }
            temp[index2] = maxTemp;
            index2++;
        } // end fill array loop
        return temp;
    } // end yearlyHighTempß

    // private method that resets the variables in the class
    private void resetData() {
        addition = 0;
        // weatherData = null;
        temp = null;

    } // end resetData()
}
