//Sally Zhao
//18 December 2020
//This is a program that opens and reads a file inputted by the user and takes in other values. 
//It then runs a simulation of the evolution of cells. 

import java.util.*;
import java.awt.*;
import java.io.*;

public class Life
{
   /**   
   * The class constant PIXEL sets the pixel size for the cells
   */
   public static final int PIXEL = 10;
   
   /**
   * The main method first prints the introductory information.
   * Creates a Scanner and then a 2D String array using method createArray.
   * Calls method runSimulation using the Scanner and array as parameters.
   *
   * @param args console input
   */
   public static void main(String args[]) {
      System.out.println("This program runs Conway's Game of Life");
      Scanner console = new Scanner(System.in);
      String[][] array = createArray(console);
      runSimulation(console, array.length, array[0].length, array);
   }
   
   /**
   * This is a method to create a 2D String array from data in a file.
   * Calls MyUtils.getFileScanner to get the necessary data from existing files.
   *
   * @param one Scanner object to be used in the getFileScanner method
   * 
   * @return returns a 2D String array
   */
   public static String[][] createArray(Scanner console) {
      Scanner input = MyUtils.getFileScanner(console, "SummerLife/", "Input file name? ");
      int rows = input.nextInt();
      int cols = input.nextInt();
      String[][] newArray = new String[rows][cols];
      input.nextLine();
      try {
         for (int i = 0; i < rows; i++) {
            String line = input.nextLine();
            for (int j = 0; j < cols; j++) {
               char state = line.charAt(j);
               newArray[i][j] = Character.toString(state);
            }
         }
         return newArray;
      }
      catch (Exception e) { //if array dimensions do not match the data set
         newArray = new String[rows][cols];
         return newArray;
      }
   }
   
   /**
   * This is a method to run one step of the cell evolution and produce a new array.
   * Takes in the original array and adjusts each element based on the cell rules. 
   *
   * @param one String array for the initial state of the cells
   *
   * @return 2D String array
   */
   public static String[][] nextStep(String[][] array) {
      int rows = array.length;
      int cols = array[0].length;
      String[][] nextArray = new String[rows][cols];
      for (int i = 0; i < rows; i++) { //first two for loops to check each array element
         for (int j = 0; j < cols; j++) {
            String[] boxArray = new String[8];
            int live = 0;
            int k = 0;
            for (int m = -1; m < 2; m++) { //next two for loops to look at all surrounding cells
               for (int n = -1; n < 2; n++) {
                  int addR = i + m;
                  int addC = j + n;
                  if (m == 0 && n == 0) { //center cell does not need to be counted
                  }
                  else {
                     try { //checks if a surrounding cell exists
                        boxArray[k] = array[addR][addC];
                     }
                     catch (Exception e) {
                        boxArray[k] = null;
                     }
                     if (boxArray[k] == null) { //if-statements count the number of live cells
                        live = live;
                     }
                     else if (boxArray[k].equals("x")) {
                        live = live + 1;
                     }
                     else {
                        live = live;
                     }
                  }
                  k = k++;
               }
            }
            //rules for cell life or death in next step
            if (array[i][j].equals(".") && live == 3) {
               nextArray[i][j] = ("x");
            }           
            else if (array[i][j].equals(".") && live != 3) {
               nextArray[i][j] = (".");
            }
            else if (array[i][j].equals("x") && live == 2) {
               nextArray[i][j] = ("x");
            }
            else if (array[i][j].equals("x") && live == 3) {
               nextArray[i][j] = ("x");
            }
            else {
               nextArray[i][j] = (".");
            }          
         }
      }
      return nextArray;   
   }
   
   /**
   * This is a method to fill a new file with the data from the array. 
   *
   * @param one PrintStream object for the output file
   * @param one String array from which the data is taken
   */
   public static void fillOutput(PrintStream p, String[][] array) {
      for (int row = 0; row < array.length; row++) {
         for (int col = 0; col < array[0].length; col++) {
            p.print(array[row][col]);
         }
         p.println();
      }
   }
      
   /**
   * This is a method to run the DrawingPanel simulation of cells.
   * Calls MyUtils.getOutputStream to create a file for the final state of the cells.
   * Takes in user input for the number of frames and time between steps.
   * Calls methods nextStep and fillOutput.
   *
   * @param one Scanner object to be used in the getOutputStream method
   * @param two int values for the size of the panel and number of cells
   * @param one String array for the initial state of the cells
   */
   public static void runSimulation(Scanner console, int rows, int cols, String[][] array) {
      System.out.print("Output file name? ");
      PrintStream outFile = MyUtils.getOutputStream(console);
      String prompt = ("Number of frames to run the simulation (0-5000): ");
      int frames = MyUtils.getNumber(console, prompt, 0, 5000);
      String prompt2 = ("Time between steps in ms (1-5000): ");
      int time = MyUtils.getNumber(console, prompt2, 1, 5000);
      
      try {
         DrawingPanel panel = new DrawingPanel(rows*PIXEL, cols*PIXEL);
         panel.setBackground(Color.WHITE);
         Graphics g = panel.getGraphics();
         for (int i = 0; i < frames - 1; i++) {
            for (int j = 0; j < rows; j++) {
               for (int k = 0; k < cols; k++) {
                  if (array[j][k].equals("x")) {
                     g.setColor(Color.BLACK);
                     g.fillRect(j*PIXEL, k*PIXEL, PIXEL, PIXEL);
                  }
                  else {
                     g.setColor(Color.WHITE);
                     g.fillRect(j*PIXEL, k*PIXEL, PIXEL, PIXEL);
                  }
               }
            }
            panel.sleep(1000 / time);
            array = nextStep(array);
         }
         System.out.println("Simulation successful!");
         fillOutput(outFile, array);
      }
      catch (Exception e) { //if array elements are all null
         System.out.println("Error found in the input file. Halting simulation.");
      }
   } 
   
} 