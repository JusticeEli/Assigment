import java.io.IOException;

public class TestPhase3 {
  public static void main(String [] parms) {
    testMoveHappyPath();
    testMoveUnhappyPath();
  }
  
  public static void testMoveHappyPath() {
    System.out.println("Testing processMove() method...\n\n");
    
    try {
      System.out.println("This should print BigCity without errors");
      
      BigCity game = new BigCity("CityGrid_GoodData1.txt");
      System.out.println(game);
      
      System.out.println("move('d') - Move right one step");
      game.move('d');
      System.out.println("move('d') - Move right another step");
      game.move('d');
      
      System.out.print("Display grid. ");
      String expectedGrid = ". . s . .\n" + ". . b . .\n" + ". . . . .\n" +
        "b b . . .\n" + ". . . . b\n" + ". . . . .\n";
      System.out.println("This should print:\n" + expectedGrid);
      System.out.println(game);
      
      System.out.println("move('s') - Move down one step to open box. Box contains cheese.");
      game.move('s');
      
      System.out.print("Display grid. ");
      expectedGrid = ". . . . .\n" + ". . s . .\n" + ". . . . .\n" +
        "b b . . .\n" + ". . . . b\n" + ". . . . .\n";
      System.out.println("This should print:\n" + expectedGrid);
      System.out.println(game);
      
      
      System.out.println("move('s'), move('s'), move('w'), move('s'), move('a'). \n" +
                         "2 down, 1 up, 1 down and 1 left to open box with trap.");
      game.move('s');
      game.move('s');
      game.move('w');
      game.move('s');
      game.move('a');
      
      System.out.print("Display grid. ");
      expectedGrid = ". . . . .\n" + ". . . . .\n" + ". . . . .\n" +
        "b s . . .\n" + ". . . . b\n" + ". . . . .\n";
      System.out.println("This should print:\n" + expectedGrid);
      System.out.println(game);
      
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    } catch (IndexOutOfBoundsException iobe) {
      System.out.println(iobe.getMessage());
    }
    
  } // testMoveHappyPath
  
  public static void testMoveUnhappyPath() {
    System.out.println("Testing fault tolerance of processMove() method...\n\n");
    
    BigCity game = null;
    String expectedGrid = null;
    
    try {
      game = new BigCity("CityGrid_GoodData1.txt");
      
      System.out.println("This should print BigCity without errors");
      System.out.println(game); 
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    try {      
      System.out.println("move('w') - Move up to trigger an IndexOutOfBoundsException");      
      System.out.println("This should print: This move takes Suzie out of the grid.");
      game.move('w');
    } catch (IndexOutOfBoundsException iobe) {
      System.out.println(iobe.getMessage());
    }
    
    try {
      System.out.println("\nmove('a') - Move left to trigger an IndexOutOfBoundsException");
      System.out.println("This should print: This move takes Suzie out of the grid.");
      game.move('a');
    } catch (IndexOutOfBoundsException iobe) {
      System.out.println(iobe.getMessage());
    }
    
    System.out.println("Print BigCity again without errors");
    System.out.println(game); 
    
    try {
      System.out.println("\nmove('s') move('s') move('s') move('s') - Move down 4 times.");
      game.move('s');
      game.move('s');
      game.move('s');
      game.move('s');
      expectedGrid = ". . . . .\n" + ". . b . .\n" + ". . . . .\n" +
        ". b . . .\n" + "s . . . b\n" + ". . . . .\n";
      System.out.println("This should print:\n" + expectedGrid);
      System.out.println(game);
      
      System.out.println("\nmove('s') move('s') - Move down 2 more times to trigger an IndexOutOfBoundsException"); 
      System.out.println("This should print: This move takes Suzie out of the grid.");
      game.move('s');
      game.move('s');
    } catch (IndexOutOfBoundsException iobe) {
      System.out.println(iobe.getMessage());
    }
    
    System.out.println("Print BigCity showing Suzie at the bottom left corner.");
    expectedGrid = ". . . . .\n" + ". . b . .\n" + ". . . . .\n" +
      ". b . . .\n" + ". . . . b\n" + "s . . . .\n";
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game);
    
    try {
      System.out.println("\nmove('d'), move('d'), move('d'), move('d'), move('d') - Move right 5 times" +
                         " to trigger an IndexOutOfBoundsException");
      game.move('d');
      game.move('d');
      game.move('d');
      game.move('d');
      game.move('d');
      
      System.out.println("This should print: This move takes Suzie out of the grid.");
    } catch (IndexOutOfBoundsException iobe) {
      System.out.println(iobe.getMessage());
    }
    
    System.out.println("Print BigCity showing Suzie at the bottom right corner.");
    expectedGrid = ". . . . .\n" + ". . b . .\n" + ". . . . .\n" +
      ". b . . .\n" + ". . . . b\n" + ". . . . s\n";
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game);
    
  } // testMoveUnhappyPath
}