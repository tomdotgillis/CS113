
///////////////////////////////////////////////////////
// Name: Thomas Gillis
// 
// Date: 2/15/22
//
// Methods: 
//      main(String[]) : void
//
// 
//
// 
//
///////////////////////////////////////////////////////

import javax.swing.JOptionPane;
import java.util.Random;
import javax.swing.JFileChooser;
import java.util.Scanner;

public class UseTestAverages {
    public static TestAverages testScores = new TestAverages();// object of type TestAverages
    private static int realLength = 0;// actual count of how many elements are in the array.

    public static void main(String[] args) throws Exception {
        // TODO: Add in the filechooser section to be able to input the data file.
        Random rd = new Random(); // creating Random object
        int[] testRun;
        int testTotal = 0;
        double mean;
        Scanner input;
        String inputString;
        int tik = 0;
        int[] output = new int[2000];
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("Could not find the file!");
            System.exit(-1);
        }

        java.io.File file = fileChooser.getSelectedFile();
        input = new Scanner(file);

        while (input.hasNext()) {
            output[tik] = Integer.parseInt(input.nextLine());
            // System.out.println(output[tik]);
            tik++;
        }
        for (int i = 0; i < tik; i++) {
            testScores.addScore(output[i]);

        }
        mean = Math.round(testScores.computeMean() * 1000.00) / 1000.0;
        System.out.println("\n\n--------------------------------------");
        System.out.println("Calculated Mean............... " + mean);
        System.out.println("Calculated Median............. " + testScores.computeMedian());
        System.out.println("Calculated Mode............... " + testScores.computeMode());
        System.out.println("Number of valid inputs........ " + testScores.getValidCount());
        System.out.println("Number of invalid inputs is... " + testScores.getInvalidCount());
        // end of program statement
        // System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("All done for now.");
        System.out.println("-----------------");
        System.out.println("");
        System.exit(0);
    }

}
