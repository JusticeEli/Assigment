import java.io.IOException;
import java.util.Arrays;

public class TestPhase5 {
  public static void main(String [] parms) {
    System.out.println("Testing extractRow() and extractColumn() methods...\n\n");
    testHappyPath();
    testUnhappyPath();
  } // main 
  
  public static void testHappyPath() {   
    System.out.println(" *************\n testHappyPath\n *************");
    BigCity game = null;
    String expectedGrid = null;
    
    try {
      System.out.println("This should print BigCity without errors");
      game = new BigCity("CityGrid_GoodData3.txt");
      System.out.println(game); 
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    try {
      System.out.println("Should match:");
      for (int i = 0; i < 6; i++)
        printArrayNoCommas(game.extractRow(i));
      
      System.out.println("Should be the board reflected on the x=y axis");
      for (int i = 0; i < 6; i++)
        printArrayNoCommas(game.extractColumn(i));
    } catch(DataDoesNotExistException dne) {
      System.out.println(dne.getMessage());
    }
    
    try {
      System.out.println(game);
      
      System.out.println("Move 1 step down:");
      game.move('s');
      
      System.out.println(game);
      System.out.print("Row 0 should be: [. . . . . .].\nRow 0: ");
      printArrayNoCommas(game.extractRow(0));
      
      System.out.print("\nColumn 0 should be: [. s . b . .].\nColumn 0: ");
      printArrayNoCommas(game.extractColumn(0));
      
    } catch(DataDoesNotExistException dne) {
      System.out.println(dne.getMessage());
    }
    
  } // testHappyPath
  
  public static void testUnhappyPath() {
    System.out.println("\n\n ***************\n testUnhappyPath\n ***************");
    BigCity game = null;
    String expectedGrid = null;
    
    try {
      System.out.println("This should print BigCity without errors");
      game = new BigCity("CityGrid_GoodData3.txt");
      System.out.println(game); 
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    try {
      System.out.println("Extract row -1 to trigger DataDoesNotExistException.\nShould print: " +
                         "BigCity grid does not have a row index of -1");
      
      game.extractRow(-1);
    } catch(DataDoesNotExistException dne) {
      System.out.println(dne.getMessage());
    }
    
    try {
      System.out.println("\nExtract row 6 to trigger DataDoesNotExistException.\nShould print: " +
                         "BigCity grid does not have a row index of 6");
      
      game.extractRow(6);
    } catch(DataDoesNotExistException dne) {
      System.out.println(dne.getMessage());
    }
    
    try {
      System.out.println("\nExtract column -1 to trigger DataDoesNotExistException.\nShould print: " +
                         "BigCity grid does not have a column index of -1");
      
      game.extractColumn(-1);
    } catch(DataDoesNotExistException dne) {
      System.out.println(dne.getMessage());
    }
    
    try {
      System.out.println("\nExtract column 6 to trigger DataDoesNotExistException.\nShould print: " +
                         "BigCity grid does not have a column index of 6");
      
      game.extractColumn(6);
    } catch(DataDoesNotExistException dne) {
      System.out.println(dne.getMessage());
    }
  } // testUnhappyPath
  
  public static void printArrayNoCommas(char [] arr) {
    System.out.print("[");
    for (int i = 0; i<arr.length-1; i++)
      System.out.print(arr[i] + " ");
    System.out.println(arr[arr.length-1] + "]");
  }
} // class TestPhase5