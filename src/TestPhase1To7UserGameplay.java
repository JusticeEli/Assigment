import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TestPhase1To7UserGameplay {
  
  static final char UP = 'w';
  static final char DOWN = 's';
  static final char LEFT = 'a';
  static final char RIGHT = 'd';
  
  public static void main(String [] parms) {
    
    String userInput = null;
    Scanner kbd = new Scanner(System.in);
    
    BigCity game = null;
    try {
      // Test other *GoodData* text files if you will.

      //game = new BigCity("CityGrid_GoodData1.txt");
      game = new BigCity("C:\\Users\\user\\Documents\\hello.txt");

      System.out.println("This should print BigCity without errors");
      System.out.println(game); 
      System.out.println("-----------------------------------------\n"); 
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    
    List<String> validInput = new ArrayList<>();
    validInput.add("er"); validInput.add("ER");
    validInput.add("ec"); validInput.add("EC");
    validInput.add("ma"); validInput.add("MA");
    validInput.add("md"); validInput.add("MD");
    validInput.add("mw"); validInput.add("MW");
    validInput.add("ms"); validInput.add("MS");
    validInput.add("u");  validInput.add("U");
    validInput.add("q");  validInput.add("Q");

    
    String menu = "Pick a feature to test.\n[er] - extractRow()\t\t[ec] - extractColumn\n" +
      "[q]  - Quit at any time\t[u]  - undo\n------------------Movements:------------------\n" +
      "[ma] - move left\t\t[md] - move right\n[mw] - move up\t\t[ms] - move down";
    
    game: 
      do {
      System.out.println("\n" + game); 
      System.out.println(menu);
      userInput = kbd.nextLine();
      
      
      if (validInput.contains(userInput)) {
        
        switch(userInput) {
          case "u":
          case "U":
            System.out.println("\nUndo\n");
            game.undo();
            break;
          case "ma":
          case "MA":
            System.out.println("move left");
            try {
              game.move(LEFT);
            } catch (IndexOutOfBoundsException iobe) {
              System.out.println(iobe.getMessage());
            } // try/catch
            break;
          case "md":
          case "MD":
            System.out.println("move right");
            try {
              game.move(RIGHT);
            } catch (IndexOutOfBoundsException iobe) {
              System.out.println(iobe.getMessage());
            } // try/catch
            break;
          case "mw":
          case "MW":
            System.out.println("move up");
            try {
              game.move(UP);
            } catch (IndexOutOfBoundsException iobe) {
              System.out.println(iobe.getMessage());
            } // try/catch
            break;
          case "ms":
          case "MS":
            System.out.println("move down");
            try {
              game.move(DOWN);
            } catch (IndexOutOfBoundsException iobe) {
              System.out.println(iobe.getMessage());
            } // try/catch
            break;
          case "er":
          case "ER":
            try {
            System.out.println("Enter row number (index-by-zero rules): ");
            int row = kbd.nextInt();
            
            String junk = kbd.nextLine(); // Get rid of trailing '\n'
            System.out.print("Row " + row + ": ");
            printArrayNoCommas(game.extractRow(row));
          } catch (InputMismatchException ime) {
            System.out.println("Input not an integer: " + ime.getMessage());
            
          } catch (DataDoesNotExistException dne) {
            System.out.println(dne.getMessage());
          }
          break;
          case "ec":
          case "EC":
            try {
            System.out.println("Enter column number (index-by-zero rules): ");
            int col = kbd.nextInt();
            
            String junk = kbd.nextLine(); // Get rid of trailing '\n'
            System.out.print("Column " + col + ": ");
            printArrayNoCommas(game.extractColumn(col));
          } catch (InputMismatchException ime) {
            System.out.println("Input not an integer: " + ime.getMessage());
          } catch (DataDoesNotExistException dne) {
            System.out.println(dne.getMessage());
          }
          break;
          default: // must be q or Q then
            break game;
        }
        
      } else
        System.out.println("Invalid entry: " + userInput + "\n");
    } while (!userInput.equalsIgnoreCase("q") && game.isRoamingCity() );
    
    System.out.println("\n" + game + "\nThanks for playing :)");
    
  } // main 
  
  public static void printArrayNoCommas(char [] arr) {
    System.out.print("[");
    for (int i = 0; i<arr.length-1; i++)
      System.out.print(arr[i] + " ");
    System.out.println(arr[arr.length-1] + "]");
  }
} // class TestPhase4