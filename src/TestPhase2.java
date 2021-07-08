import java.io.IOException;

public class TestPhase2 {
  public static void main(String [] parms) {
    testGoodInput();
    
    testBadInput();
  }
  
  public static void testGoodInput() {
    System.out.println("Testing good input data...\n\n");
    
    try {
      System.out.println("This should print BigCity without errors");
      
      BigCity game = new BigCity("CityGrid_GoodData1.txt");
      System.out.println(game);
      
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    try {
      System.out.println("This should print BigCity without errors");
      
      BigCity game = new BigCity("CityGrid_GoodData2.txt");
      System.out.println(game);
      
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    try {
      System.out.println("This should print BigCity without errors");
      
      BigCity game = new BigCity("CityGrid_GoodData3.txt");
      System.out.println(game);
      
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    try {
      System.out.println("This should print BigCity without errors");
      
      BigCity game = new BigCity("CityGrid_GoodData4.txt");
      System.out.println(game);
      
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    try {
      System.out.println("This should print BigCity without errors");
      
      BigCity game = new BigCity("CityGrid_GoodData5.txt");
      System.out.println(game);
      
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
  } // testGoodInput
  
  public static void testBadInput() {
    System.out.println("\n\nTesting bad input data...\n\n");
    
    try {
      System.out.println("This should print: The file NoSuchFile.txt was not found");
      
      BigCity game = new BigCity("NoSuchFile.txt");
      System.out.println(game);
      
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage() + "\n");
    }
    
    try {
      System.out.println("This should print: No dimension to read");
      BigCity game2 = new BigCity("CityGrid_BadData1.txt");
      
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage() + "\n");
    }
    
    try {
      System.out.println("This should print: " +
                         "No grid to read");
      BigCity game3 = new BigCity("CityGrid_BadData5.txt");
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage() + "\n");
    }
    
    try {
      System.out.println("This should print: " +
                         "No boxes on grid");
      BigCity game3 = new BigCity("CityGrid_BadData6.txt");
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage() + "\n");
    }
    
    try {
      System.out.println("This should print: " +
                         "Inaccurate number of rows of cheese positions in the file. Saw 0 expected 1");
      BigCity game3 = new BigCity("CityGrid_BadData2.txt");
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage() + "\n");
    }
    
    try {
      System.out.println("This should print: " +
                         "Inaccurate number of columns of cheese in the file. Saw 4 expected 5");
      BigCity game4 = new BigCity("CityGrid_BadData3.txt");
      
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage() + "\n");
    }
    
    try {
      System.out.println("This should print: " +
                         "Inaccurate number of columns of cheese in the file. Saw 1 expected 3");
      BigCity game3 = new BigCity("CityGrid_BadData4.txt");
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage() + "\n");
    }
    
  }
}