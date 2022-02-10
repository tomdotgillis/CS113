
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Name: Thomas Gillis                                                                                                  //
//                                                                                                                      //
// Date: 2/15/22                                                                                                        //
//                                                                                                                      //
// Methods:                                                                                                             //
//      TestAverages() – create space to hold all the exams as well as initialize all the instance variables.           //
//                                                                                                                      //
//      addScore(int) : void - add this test score into the "exam database(DB)".                                        //
//                                                                                                                      //
//      computeMean() : double - add up all the test scores, divide by the number of valid scores and return it         //
//                                                                                                                      //
//      computeMode() : int - return the test score that occurred the most. If more than one, choose one.               //
//                                                                                                                      //
//      computeMedian() : double - return the middle test score. (If an even number of test scores were entered,        //
//                               | you must average the two middle scores and return that value, which is why           //
//                               | this returns a double.)                                                              //
//                                                                                                                      //
//      getValidCount() : int - returns the number of valid scores added to the DB.                                     //
//                                                                                                                      //
//      getInvalidCount() : int - returns the number of invalid scores attempted to be added.                           //
//                                                                                                                      //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import javax.swing.JOptionPane;

public class TestAverages {
    public static int scores[]; // array of integers that will hold the test scores that range from 0-100.
    private static final int ARRAY_SIZE = 2000; // max number of data file elements, therefore the size of the array
    private static int howMany; // total number of scores entered into the array with a size of ARRAY_SIZE
    private static int validInputs; // number of valid scores
    private static int invalidInputs; // number of invalid scores
    private static int mode;
    private static double median;
    private static double mean;
    private static int swag;

    // create space to hold all the exams as well as initialize all the instance
    // variables.
    // WORKING...
    public TestAverages() {
        scores = new int[ARRAY_SIZE];
        howMany = 0;
        validInputs = 0;
        invalidInputs = 0;
    }

    // add this test score into the "exam database(DB)".
    // WORKING...
    public void addScore(int score) {
        if (howMany < ARRAY_SIZE) {
            if (score >= 0 && score < 101) {
                scores[howMany] = score;
                howMany++;
                validInputs++;
            } // end if
            else
                invalidInputs++;

        }
    }

    // add up all the test scores, divide by the number of valid scores and return
    // it.
    // WORKING...
    public double computeMean() {
        int preAverage = 0;// total of the array added together.
        int i;
        for (i = 0; i < howMany; i++) {
            preAverage += scores[i];
        }
        mean = (double) preAverage / i;
        return mean;
    } // end computeMean

    // return the test score that occurred the most. If more than one, choose one.
    // WORKING...
    public int computeMode() {
        mode = scores[0]; // sets the mode to the first integer in the array to be compared with the
                          // following values
        int modePresence = 0; // how many times does the mode appear in the array scores[]

        // loop that looks at how many times an element in the array appears, returns
        // the most common.
        for (int i = 0; i < howMany; i++) {
            int value = scores[i];
            int count = 1;
            for (int j = 0; j < howMany; j++) {
                if (scores[j] == value)
                    count++;
                if (count > modePresence) {
                    mode = value;
                    modePresence = count;
                }
            }
        }
        return mode;

    } // end computeMode

    // return the middle test score. (If an even number of test scores were entered,
    // you must average the two middle scores and return that value, which is why
    // this returns a double.)
    // WORKING...
    public double computeMedian() {
        int finder;// this is the location of the middle of the array 'scores'
        // if the array has an odd number of test scores
        sort(scores, howMany);
        if (howMany % 2 != 0) {
            finder = howMany / 2;
            median = (double) scores[finder];
        }
        // if the arary has an even number of test scores
        else if (howMany % 2 == 0) {
            finder = howMany / 2;
            // if the two middle elements are the same number, set median to that value
            if (scores[finder] == scores[finder - 1])
                median = (double) scores[finder];
            // if the two middle elements are not the same number, set median to the average
            // of the two middle elements
            else
                median = (double) (scores[finder] + scores[finder - 1]) / 2;

        }

        return median;
    } // end computeMedian

    // returns the number of valid scores added to the DB.
    // WORKING...
    public int getValidCount() {
        swag = validInputs;
        return validInputs;
    } // end getValidCount

    // returns the number of invalid scores attempted to be added.
    // WORKING;
    public int getInvalidCount() {
        // invalidInputs = howMany - swag;
        return invalidInputs;
    } // end getInvalidCount

    // FIXME: Get array working with invalid inputs

    private static void sort(int values[], int howMany) {
        boolean sorted; // this will be true if the array is now in ascending after the for loop
        int save = howMany, // save will be decremented on each iteration since next largest
                            // value will be correctly placed at the end of the array after each iteration.
                temp; // place to save the to be copied over value when swapping ints.

        do {
            sorted = true;
            for (int inner = 0; inner < save - 1; inner++) {
                if (values[inner] > 0 && values[inner] < 101) {
                    if (values[inner] > values[inner + 1]) {
                        temp = values[inner];
                        values[inner] = values[inner + 1];
                        values[inner + 1] = temp;
                        sorted = false;
                    }
                }
            }
            save--;
        } while (save > 1 && !sorted);

    }
}
