//Sally Zhao
//18 December 2020
//This is a program to have the user input a number within bounds or the name of an existing file.
//If the user inputs an int outside of the bounds or not an int, there is an error message.
//If the user inputs a String that is not a file that can be found, there is an error message.

import java.util.*;
import java.io.*;

//functions that are reusable across assignments

public class MyUtils
{
   /**
   * This main method is for testing purposes only.
   * Consider using it to make sure your getNumber method works correctly.
   * You can remove main or leave it when you turn in this file with Assignment 5 - either is fine
   *
   * @param args console input
   */
   public static void main(String[] args)
   {
      Scanner console = new Scanner(System.in);
     // int number = getNumber(console, "Input a number between 1 and 10: ", 1, 10);
     // System.out.println("Your int was " + number);
      
     // Scanner newFileScanner = getFileScanner(console, "data/", "Input a String: ");
     // PrintStrseam newFile = getOutputStream(console);
   }
   
   /**
   * This is a method to get an int from the user and determine whether or not it can be used.
   * The user input must be an int with certain bounds.
   * If the user input is not a valid int there is a specific error message printed. 
   *
   * @param one Scanner object, one String for the prompt to the user, two ints for the bounds
   * @return the valid int from the user
   */
   public static int getNumber(Scanner console, String prompt, int min, int max) {
      while (true) { //infinite loop
         System.out.print(prompt);
         try {
            int number = console.nextInt();
            console.nextLine();
            if (number < min || number > max) { //if within the bounds
               System.out.println("Your number needs to be between " + min + " and " + max + ".");
            }
            else { //if outside bounds
               return number; //end loop
            }
         }
         catch (Exception e) { //inputs that are not an int
            System.out.println("You must enter an *integer* between " + min + " and " + max + ".");
            console.nextLine();
         }
      }
   }
   
   public static Scanner getFileScanner(Scanner console, String directory, String prompt) {
      System.out.print(prompt);
      while (true) {
         String input = console.next();
         String name = directory + input;
         File f = new File(name);
         try {
            Scanner fileScanner = new Scanner(f);
            return fileScanner;
         }
         catch (Exception e) {
            System.out.print("File not found. Try again: ");
         }
      }
   }
   
   public static PrintStream getOutputStream(String a, int level, String b) {
      while (true) {
         //String input = console.next();
         try {
            //slightly modified to fit Dragon assignment
            PrintStream ps = new PrintStream(new File(a + level + "." + b));
            return ps;
         }
         catch (Exception e) {
            return null;
         }
      }
   }
}