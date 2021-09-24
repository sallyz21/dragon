//Sally Zhao
//8 December 2020
//This is a program that draws an image chosen by the user.
//It can draw a state and its counties, the continental US, and the US with counties.

import java.util.*;
import java.io.*;
import java.awt.*;

public class Outline 
{
   /**   
   * The class constant WINDOW sets a scaling factor for the DrawingPanel window
   */
   public static final int WINDOW = 30;
   /**   
   * The class constant DIRECTORY as a String to find the locations of files
   */
   public static final String DIRECTORY = "data/"; 
   //I was trying this with "data\\" and it wouldn't work but "data/" does; maybe a Mac thing?
   
   /**
   * The main method first prints the introductory information.
   * Then creates a Scanner and calls method multipleStates with appropriate parameters.
   *
   * @param args console input
   */
   public static void main(String args[]) {
      printIntro();
      Scanner console = new Scanner(System.in);
      multipleStates(console, DIRECTORY, "What state would you like to see? ");
   }
   
   /**
   * This is a method to print the introductory information to the screen.
   */
   public static void printIntro() {
      System.out.println("This program draws a visualization of");
      System.out.println("data from a given file. Enter");
      System.out.println("- the 2 letter abbreviation for each state");
      System.out.println("- USA for all of the US by state, or");
      System.out.println("- USA-county for all of the US by county.");
      System.out.println();
   }
   
   /**
   * This is a method to draw the outline of a single state.
   * Calls MyUtils.getFileScanner to get the necessary data from existing files.
   *
   * @param one Scanner object to be used in the getFileScanner method
   * @param a String directory for the getFileScanner method
   * @param a String prompt for the getFileScanner method
   * 
   * @return returns a Polygon2D with the drawn image of a single state
   */
   public static Polygon2D oneOutline(Scanner console, String directory, String prompt) {
      Scanner input = MyUtils.getFileScanner(console, directory, prompt);
      double xMin = input.nextDouble();
      double yMin = input.nextDouble();
      double xMax = input.nextDouble();
      double yMax = input.nextDouble();
      double xSize = xMax - xMin;
      double ySize = yMax - yMin;
      DrawingPanelPlus window = new DrawingPanelPlus((int) (xSize*WINDOW), (int) (ySize*WINDOW));
      window.setXscale(xMin, xMax);
      window.setYscale(yMin, yMax);
      
      int regions = input.nextInt();
      Polygon2D pd = new Polygon2D();
      for (int i = 0; i < regions; i++) { //to draw each region of a state
         input.nextLine();
         input.nextLine();
         input.nextLine();
         input.nextLine();
         int points = input.nextInt();
         input.nextLine();
         pd = new Polygon2D(points); 

         for (int j = 0; j < points; j++) { //to draw each point in the outline of a region
            double xCoord = input.nextDouble();
            double yCoord = input.nextDouble();;  
            pd.addPoint(xCoord, yCoord);      
         }
         window.polygon(pd);      
      }
      return pd;
   }
   
   /**
   * This is a method to draw the outline of as many states as the user wants.
   * Calls method oneOutline to draw one state outline at a time. 
   * Uses user input to either draw more states or end the program. 
   *
   * @param one Scanner object to be used in the getFileScanner method in method oneOutline
   * @param a String directory for oneOutline
   * @param a String prompt for oneOutline
   */
   public static void multipleStates(Scanner console, String directory, String prompt) {
      Polygon2D shape = oneOutline(console, directory, prompt);
      System.out.print("Do you want to draw something else? ");
      String answer = console.next();
      
      while (answer.startsWith("y") || answer.startsWith("Y")) {
         shape.reset();
         shape = oneOutline(console, directory, prompt);
         System.out.print("Do you want to draw something else? ");
         answer = console.next();
      }
   }
   
}