
///////////////////////////////////////////////////////
// Name: Thomas Gillis
// 
// Date: 2/1/22
//
// Purpose: The purpose of this program is to sort and 
//          rearrange an array read in from a file by 
//          using a shear sort method, and have the 
//          program output the new sorted array.
//
// Inputs: A data file from the user.
//
// Outputs: A sorted 2D array of numbers organized in 
//          a series of Strings.
//
///////////////////////////////////////////////////////

import javax.swing.JFileChooser;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class ShearSort {
    public static void main(String[] args) throws Exception {
        Scanner input;
        String inputString;
        int tik = 0;
        double[] output = new double[400];
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("Could not find the file!");
            System.exit(-1);
        }

        java.io.File file = fileChooser.getSelectedFile();
        input = new Scanner(file);

        while (input.hasNext()) {
            inputString = input.nextLine();
            output[tik] = Double.parseDouble(inputString);
            // System.out.println(output[tik]);
            tik++;
        }
        double[][] preShear,
                finalShear;
        // preShear = createArr(test, test.length);

        preShear = createArr(output, tik);
        finalShear = doTheSort(preShear, preShear.length);
        System.out.println("\nSORTED ARRAY:");
        toString(finalShear);

        // END OF PROGRAM MESSAGE
        System.out.println("");
        System.out.println("-----------------");
        System.out.println("All done for now.");
        System.out.println("-----------------");
        System.out.println("");
        System.exit(0);
    }

    // creates the two dimensional array from the data collected in main, with the
    public static double[][] createArr(double[] data, int length) {
        int k = 0, // increments through the 1D array 'data' to fill the 2D array 'unSheared'
                arrSize;// The length of the 2D array 'unSheared'
        double transfer;// used to calculate 'arrSize'
        final double MAX_VALUE = 1000.0;// placeholder for empty squares
        double[][] sheared = new double[length][10];// 2D array created to be sorted in 'doTheSort()' with the
                                                    // length
                                                    // 'length'
        transfer = Math.sqrt(length);
        arrSize = (int) Math.ceil(transfer);
        // System.out.println(arrSize);
        // setting hte
        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < 10; j++) {
                if (k < length) {
                    sheared[i][j] = data[k];
                } else
                    sheared[i][j] = MAX_VALUE;
                k++;
            }
        }
        return sheared;
    }

    // runs through the two sorting methods until the conditions of both are met.
    public static double[][] doTheSort(double[][] nums, int arrSize) {
        double[][] shear;
        double[][] colMove;
        double[][] rowMove;
        boolean rowCheck;
        boolean colCheck;
        final int BIG_BOI = 100000;

        rowMove = rows(nums, arrSize);
        colMove = cols(rowMove, arrSize);

        for (int i = 0; i < BIG_BOI; i++) {
            rowMove = rows(colMove, arrSize);
            colMove = cols(rowMove, arrSize);
            rowMove = rows(colMove, arrSize);
        }
        return rowMove;

    }

    // Sorts the horizontal rows in the 2D matrix. Even ascends, odd descends.
    public static double[][] rows(double[][] nums, int arrSize) {
        double[][] output;
        double temp;
        int save;
        boolean sorted;

        save = arrSize;
        output = nums;
        // EVEN ROWS ONLY
        for (int i = 0; i < nums.length; i += 2) {
            do {
                sorted = true;
                for (int j = 0; j < 9; j++) {
                    if (nums[i][j] > nums[i][j + 1]) {
                        temp = nums[i][j];
                        output[i][j] = nums[i][j + 1];
                        output[i][j + 1] = temp;
                        sorted = false;
                    }
                }

            } while (save > 1 && !sorted);
        }
        // ODD ROWS ONLY
        for (int i = 1; i < nums.length; i += 2) {
            do {
                sorted = true;
                for (int j = 0; j < 9; j++) {
                    if (nums[i][j] < nums[i][j + 1]) {
                        temp = nums[i][j];
                        output[i][j] = nums[i][j + 1];
                        output[i][j + 1] = temp;
                        sorted = false;
                    }
                }

            } while (save > 1 && !sorted);
        }
        return output;
    }

    // Sorts the vertical columns of the 2D matrix. All columns ascend.
    public static double[][] cols(double[][] nums, int arrSize) {
        double[][] output;
        double temp;
        int save;
        boolean sorted;

        save = arrSize;
        output = nums;
        for (int j = 0; j < 10; j++) {
            do {
                sorted = true;
                for (int i = 0; i < nums.length - 1; i++) {
                    if (nums[i][j] > nums[i + 1][j] && nums[i + 1][j] != 0.0) {
                        temp = nums[i][j];
                        output[i][j] = nums[i + 1][j];
                        output[i + 1][j] = temp;
                        sorted = false;
                    }
                }
            } while (save > 1 && !sorted);
        }

        return output;
    }

    // compares the result of both sorting method. only returns true when the two
    // input arrays are equal.
    public static boolean isDone(double[][] arr1, double[][] arr2) {
        boolean result = false;
        for (int c = 0; c < arr1.length - 1; c++) {
            for (int d = 0; d < 10; d++) {
                if (arr1[c][d] == arr2[c][d])
                    result = true;
                else {
                    result = false;
                    return result;
                }
            }
        }
        return result;
    }

    // Prints the sorted array in an organized and easy to read fashion.
    public static void toString(double[][] finalArr) {
        int arrSize;
        double chopTran;
        int chop;
        int chop2;
        final int CHAR_SIZE = 6;
        int allocated;
        double transfer;
        boolean oddEven;
        transfer = Math.sqrt(finalArr.length);
        arrSize = (int) Math.ceil(transfer);
        String finalPrint = "";
        String lines = "";
        for (int i = 0; i < arrSize; i++) {
            finalPrint = "";
            for (int j = 0; j < 10; j++) {
                chopTran = finalArr[i][j];
                chop = (int) chopTran;
                chop2 = Integer.toString(chop).length();
                allocated = CHAR_SIZE - chop2;
                DecimalFormat df = new DecimalFormat("#0.00");
                if (finalArr[i][j] != 1000.0 && finalArr[i][j] != 0.0)
                    finalPrint += df.format(finalArr[i][j]) + " ";

                while (allocated > 0) {
                    finalPrint += " ";
                    allocated--;
                }
            }
            System.out.println(finalPrint + "");
            for (int x = 0; x < finalPrint.length(); x++) {
                lines += "-";
            }
            // System.out.println(lines);
            lines = "";
        }
    }

}