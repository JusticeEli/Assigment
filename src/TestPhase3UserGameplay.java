import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This helps users test Phases 2 & 3 while being able to
 * move as much as they want
 */
public class TestPhase3UserGameplay {
  public static void main(String [] parms) {
    String userInput = null;
    Scanner kbd = new Scanner(System.in);
    
    BigCity game = null;
    try {
      // Test other GoodData text files if you will.
      game = new BigCity("CityGrid_GoodData1.txt");
      
      System.out.println("This should print BigCity without errors");
      System.out.println(game); 
      System.out.println("-----------------------------------------\n"); 
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    List<String> validInput = new ArrayList<>();
      validInput.add("a"); validInput.add("d");
      validInput.add("w"); validInput.add("s");
      validInput.add("q"); validInput.add("A");
      validInput.add("D"); validInput.add("W");
      validInput.add("S"); validInput.add("Q");
      
      String prompt = "Make a move:\na - left\td - right\nw - up \ts - down\nEnter q to Quit.";
    
    do {
      System.out.println("\n" + game); 
      System.out.println(prompt);
      userInput = kbd.nextLine();
      
      if (validInput.contains(userInput)) {
        try {
          game.move(userInput.charAt(0));
        } catch (IndexOutOfBoundsException iobe) {
          System.out.println(iobe.getMessage());
        } // try/catch
      } else
        System.out.println("Invalid entry: " + userInput + "\n");
    } while (!userInput.equalsIgnoreCase("q"));
    
    System.out.println("\n" + game + "Thanks for playing :)"); 
  } // main
}
