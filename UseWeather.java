
///////////////////////////////////////////////////////
// Name: Thomas Gillis
// 
// Date: 1/18/2
//
// Methods: 
//      main(String[] args) - void - utilizes the Weather 
//                            class to find various data 
//                            about the weather in Burlington, 
//                            Vermont over a specific time.
// 
//
// 
//
///////////////////////////////////////////////////////

import javax.swing.JFileChooser;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;

public class UseWeather {
        public static void main(String[] args) throws Exception {
                double[] monthlyRain, monthlySnow, yearlyRain, yearlySnow;
                int start, end, month;
                String inputString;
                int input;
                Weather weather = new Weather();
                String[] monthStrings = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST",
                                "SEPTEMBER",
                                "OCTOBER", "NOVEMBER", "DECEMBER" };
                inputString = JOptionPane.showInputDialog(null,
                                "Would you like to:\n[1] Monthly Rain Totals\n[2] MonthlySnowfall\n[3] Yearly Rainfall\n Yearly Snowfall\n[4] Yearly Temperature Difference",
                                "Weather Data", JOptionPane.QUESTION_MESSAGE);
                input = Integer.parseInt(inputString);

                // Monthly Rainfall Data
                if (input == 1) {
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to start?",
                                        "Weather Data", JOptionPane.QUESTION_MESSAGE);
                        start = Integer.parseInt(inputString);

                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to end?",
                                        "Weather Data", JOptionPane.QUESTION_MESSAGE);
                        end = Integer.parseInt(inputString);
                        inputString = JOptionPane.showInputDialog(null,
                                        "What month to look at?\n[1-12] Jan-Dec",
                                        "Weather Data", JOptionPane.QUESTION_MESSAGE);
                        month = Integer.parseInt(inputString);
                        monthlyRain = weather.monthlyRainfall(start, end, month);
                        System.out.println("- -\n" + monthStrings[month - 1] + "\n- -");
                        for (int i = 0; i < monthlyRain.length; i++) {
                                System.out.println("RAIN AVERAGE FOR " + monthStrings[month - 1] + " IN " + (start + i)
                                                + ": "
                                                + String.format("%.3f", monthlyRain[i]));
                        }
                } // end monthlyRain possible loop.

                // Monthly Snowfall Data
                else if (input == 2) {
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to start?",
                                        "SNOWFALL Data", JOptionPane.QUESTION_MESSAGE);
                        start = Integer.parseInt(inputString);

                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to end?",
                                        "SNOWFALL Data", JOptionPane.QUESTION_MESSAGE);
                        end = Integer.parseInt(inputString);
                        inputString = JOptionPane.showInputDialog(null,
                                        "What month to look at?\n[1-12] Jan-Dec",
                                        "SNOWFALL Data", JOptionPane.QUESTION_MESSAGE);
                        month = Integer.parseInt(inputString);
                        monthlySnow = weather.monthlySnowfall(start, end, month);
                        for (int i = 0; i < monthlySnow.length; i++) {
                                System.out.println("SNOW AVERAGE FOR " + (start + i) + ": "
                                                + String.format("%.3f", monthlySnow[i]));
                        }
                } // end monthlyRain possible loop

                // Yearly Rainfall Data
                else if (input == 3) {
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to start?",
                                        "YEARLY RAINFALL Data", JOptionPane.QUESTION_MESSAGE);
                        start = Integer.parseInt(inputString);

                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to end?",
                                        "YEARLY RAINFALL Data", JOptionPane.QUESTION_MESSAGE);
                        end = Integer.parseInt(inputString);
                        yearlyRain = weather.yearlyRainfall(start, end);
                        for (int i = 0; i < yearlyRain.length; i++) {
                                System.out.println("YEARLY RAINFALL AVERAGE FOR " + (start + i) + ": "
                                                + String.format("%.3f", yearlyRain[i]));
                        }

                } // end yearlyRainfall possible loop

                // Yearly Snowfall Data
                else if (input == 4) {
                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to start?",
                                        "YEARLY SNOWFALL DATA", JOptionPane.QUESTION_MESSAGE);
                        start = Integer.parseInt(inputString);

                        inputString = JOptionPane.showInputDialog(null,
                                        "What year to end?",
                                        "YEARLY SNOWFALL DATA", JOptionPane.QUESTION_MESSAGE);
                        end = Integer.parseInt(inputString);
                        yearlySnow = weather.yearlySnowfall(start, end);
                        for (int i = 0; i < yearlySnow.length; i++) {
                                System.out.println("YEARLY SNOWFALL AVERAGE FOR " + (start + i) + ": "
                                                + String.format("%.3f", yearlySnow[i]));
                        }

                } // end yearlySnowfall possible loop
                System.out.println("");
                System.out.println("-----------------");
                System.out.println("All done for now.");
                System.out.println("-----------------");
                System.out.println("");
                System.exit(0);
        }

}
