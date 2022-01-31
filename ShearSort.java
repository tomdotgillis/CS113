
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
        // double[] yaya = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
        // 25 };
        double[] test = { 25, 24, 23, 22, 21, 16, 17, 18, 19, 20, 15, 14, 13, 12, 11, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5 };
        double[][] preShear,
                finalShear;
        // preShear = createArr(test, test.length);

        preShear = createArr(output, tik);
        // finalShear = doTheSort(preShear, preShear.length);
        // System.out.println("\n");
        // toString(finalShear);
        finalShear = doTheSort(preShear, preShear.length);
        // doTheSort2(preShear, preShear.length);
        toString(doTheSort(finalShear, finalShear.length));
        System.out.print("\n----------------------------------------------------------\n");
        // toString(rows(preShear, preShear.length));
        // sheared = createArr(output, yaya.length);

        // toString(doTheSort(sheared, sheared.length));

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
        double[][] sheared = new double[length][length];// 2D array created to be sorted in 'doTheSort()' with the
                                                        // length
                                                        // 'length'
        transfer = Math.sqrt(length);
        arrSize = (int) Math.ceil(transfer);
        // System.out.println(arrSize);
        // setting hte
        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < arrSize; j++) {
                if (k < length) {
                    sheared[i][j] = data[k];
                } else
                    sheared[i][j] = 1000.0;
                k++;
            }
        }
        // System.out.println(arrSize);
        return sheared;
    }

    // runs through the two sorting methods until the conditions of both are met.
    public static double[][] doTheSort(double[][] nums, int arrSize) {
        double[][] shear;
        double[][] colMove;
        double[][] rowMove;
        boolean rowCheck;
        boolean colCheck;

        rowMove = rows(nums, arrSize);
        colMove = cols(rowMove, arrSize);
        do {

            rowMove = rows(colMove, arrSize);
            colMove = cols(rowMove, arrSize);
            rowMove = rows(colMove, arrSize);
            rowCheck = isDone(rowMove, colMove);

        } while (rowCheck == false);
        // rowMove = rows(shear, arrSize);
        // rowMove = cols(shear, arrSize);
        int temp = 1;
        for (int i = arrSize; i > 0; i--) {
            temp = temp * i;
        }
        // System.out.println(temp);
        int i = 0;
        rowMove = colMove;
        while (rowMove != colMove) {
            rowMove = rows(colMove, arrSize);
            colMove = cols(rowMove, arrSize);
            isDone(rowMove, colMove);
            // rowMove = rows(colMove, arrSize);
            // shear = rowMove;
        }
        // for (int i = 0; i < temp; i++) {
        // colMove = cols(shear, arrSize);
        // shear = rows(colMove, arrSize);
        // }
        // return rowMove;
        return rowMove;

    }

    // Sorts the horizontal rows in the 2D matrix. Even ascends, odd descends.
    public static double[][] rows(double[][] nums, int numsLength) {

        double temp,
                transfer;
        int i,
                arrSize,
                j = 0;
        int save = numsLength;
        boolean sorted;
        double[][] newNums = nums;
        // System.out.println("NUMSLENGTH: " + numsLength);
        transfer = Math.sqrt(numsLength);
        arrSize = (int) Math.ceil(transfer);
        // System.out.println("ARRSIZE: " + arrSize);

        // sheared = new double[arrSize][arrSize];

        // EVEN ROWS
        for (i = 0; i < arrSize; i++) {
            do {
                sorted = true;
                for (j = 0; j < arrSize - 1; j++) {
                    if (nums[i][j] > nums[i][j + 1]) {
                        temp = nums[i][j];
                        newNums[i][j] = nums[i][j + 1];
                        newNums[i][j + 1] = temp;
                        sorted = false;
                        // rowCheck = false;
                    } // else
                      // rowCheck = true;
                }
            } while (save > 1 && !sorted);
        }

        // ODD ROWS
        for (i = 1; i < arrSize; i += 2) {
            do {
                sorted = true;
                for (j = 0; j < arrSize - 1; j++) {
                    if (nums[i][j] < nums[i][j + 1]) {
                        temp = nums[i][j];
                        newNums[i][j] = nums[i][j + 1];
                        newNums[i][j + 1] = temp;
                        sorted = false;

                        // rowCheck = false;
                    } // else
                      // rowCheck = true;
                      // System.out.println("I = " + i + " J = " + j);
                      // System.out.println("1: " + nums[i][j] + "2: " + nums[i][j + 1]);
                }
            } while (save > 1 && !sorted);
        }
        return newNums;
    }

    // Sorts the vertical columns of the 2D matrix. All columns ascend.
    public static double[][] cols(double[][] nums, int numsLength) {
        double temp,
                transfer;
        int i,
                arrSize,
                j = 0;
        int save = numsLength;
        boolean sorted;
        // System.out.println("NUMSLENGTH: " + numsLength);
        transfer = Math.sqrt(numsLength);
        arrSize = (int) Math.ceil(transfer);
        // System.out.println("ARRSIZE: " + arrSize);

        // sheared = new double[arrSize][arrSize];

        // EVEN ROWS
        for (j = 0; j < arrSize + 1; j++) {
            do {
                sorted = true;
                for (i = 0; i < arrSize - 1; i++) {
                    if (nums[i][j] > nums[i + 1][j] && nums[i][j + 1] != 0.0) {
                        temp = nums[i][j];
                        nums[i][j] = nums[i + 1][j];
                        nums[i + 1][j] = temp;
                        sorted = false;
                        // rowCheck = false;
                    } // else
                      // rowCheck = true;
                }
            } while (save > 1 && !sorted);
        }
        return nums;
    }

    // compares the result of both sorting method. only returns true when the two
    // input arrays are equal.
    public static boolean isDone(double[][] arr1, double[][] arr2) {

        for (int c = 0; c < arr1.length; c++) {
            for (int d = 0; d < arr1.length; d++) {
                if (arr1[c][d] == arr2[c][d] && arr1[d][c] == arr2[d][c])
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    // Prints the sorted array in an organized and easy to read fashion.
    public static void toString(double[][] finalArr) {
        int arrSize;
        double chopTran;
        int chop;
        int chop2;
        int CHAR_SIZE = 6;
        int allocated;
        double transfer;
        boolean oddEven;
        transfer = Math.sqrt(finalArr.length);
        arrSize = (int) Math.ceil(transfer);
        String finalPrint = "";
        String lines = "";
        for (int i = 0; i < arrSize; i++) {
            finalPrint = "";
            for (int j = 0; j < arrSize - 1; j++) {
                chopTran = finalArr[i][j];
                chop = (int) chopTran;
                chop2 = Integer.toString(chop).length();
                allocated = CHAR_SIZE - chop2;

                // if (allocated > 0) {
                // for (i = allocated; i > 0; i--) {
                // finalPrint += "_";
                // }
                // }
                DecimalFormat df = new DecimalFormat("#0.00");
                if (finalArr[i][j] != 1000.0 && finalArr[i][j] != 0.0)
                    finalPrint += df.format(finalArr[i][j]) + " ";
                else {
                    finalPrint += "NA" + " ";
                }
                while (allocated > 0) {
                    finalPrint += " ";
                    allocated--;
                }
            }
            System.out.println(finalPrint + "");
            for (int x = 0; x < finalPrint.length(); x++) {
                lines += "-";
            }
            System.out.println(lines);
            lines = "";
        }
    }
}
