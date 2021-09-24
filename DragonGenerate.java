//Sally Zhao
//14 January 2021
//This is a program that generates a Dragon Curve fractal.  
//It takes user input for the level of the fractal and size of the screen.

import java.util.*;
import java.io.*;

public class DragonGenerate
{
   /**   
   * The class constant MIN sets a minimum value for the fractal level
   */
   public static final int MIN = 1;
   /**   
   * The class constant MAX sets a maximum value for the fractal level
   */
   public static final int MAX = 25;
   /**   
   * The class constant MIN sets a minimum value for the screen size
   */
   public static final int S_MIN = 100;
   /**   
   * The class constant MAX sets a maximum value for the screen size
   */
   public static final int S_MAX = 2000;
   /**   
   * The class constant BASE sets the base name for the output file
   */
   public static final String BASE = "dragon";
   /**   
   * The class constant EXT sets the extension name for the output file
   */
   public static final String EXT = "txt";
   
   /**
   * The main method first prints the introductory information.
   * Then creates a Scanner and runs method drawDragon with the Scanner as a parameter.
   *
   * @param args console input
   */
   public static void main(String args[]) {
      printIntro();
      Scanner console = new Scanner(System.in);
      drawDragon(console);
   }
   
   /**
   * This is a method to print the introductory information to the screen.
   */
   public static void printIntro() {
      System.out.println("This program will generate a fractal called the Dragon Curve");
      System.out.println("first explored by John Heighway, Bruce Banks, and William Harter");
      System.out.println("at NASA in the 1960's");
      System.out.println();
   }
   
   /**
   * This is a method to take user input and draw the dragon fractal.
   * Calls methods getNumber and getOutputStream from class MyUtils.
   * Calls method generateString and prints the string to the created PrintStream.
   * Calls methods drawCurve from class DragonDraw to draw the fractal.
   *
   * @param one Scanner object to be used in the getNumber method
   */
   public static void drawDragon(Scanner console) {
      String a = "Enter the level of the fractal you'd like to see (" + MIN + "-" + MAX + "): ";
      int level = MyUtils.getNumber(console, a, MIN, MAX);
      String b = "Enter the size of your drawing panel, in pixels (" + S_MIN + "-" + S_MAX + "): ";
      int size = MyUtils.getNumber(console, b, S_MIN, S_MAX);
      
      String pattern = generateString(level, "R");
      System.out.println("Path generated, writing to file " + BASE + level + "." + EXT + "...");
      PrintStream dragonFile = MyUtils.getOutputStream(BASE, level, EXT);
      dragonFile.println(pattern);
      
      DragonDraw dd = new DragonDraw(size);
      System.out.println("Drawing curve...");
      dd.drawCurve(BASE + level + "." + EXT, level);
   }   
      
   /**
   * This is a method to generate a String for the fractal pattern. 
   * Uses recursion to build the pattern to the desired level. 
   *
   * @param one int level to be reached with the pattern
   * @param one String object for the starting pattern
   *
   * @return the completed pattern String
   */
   public static String generateString(int level, String pattern) {
      if (level == 1) {
         return pattern;
      }
      else {
         pattern = pattern + "R" + stringComplement(pattern);
         return generateString(level - 1, pattern);
      }
   }
   
   /**
   * This is a method to generate the complement of a given String. 
   *
   * @param one String object from which the complement is found
   *
   * @return the new complement String
   */
   public static String stringComplement(String original) {
      String newString = "";
      for (int i = 1; i <= original.length(); i++) {
         String sub = original.substring(original.length() - i, original.length() - i + 1);
         if (sub.equals("R")) {
            newString = newString + "L";
         }
         else {
            newString = newString + "R";
         }
      }
      return newString;
   }
}