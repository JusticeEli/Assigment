import java.io.IOException;

public class TestPhase4 {
  public static void main(String [] parms) {
    System.out.println("Testing endTerror() and isRoamingCity() methods...\n\n");
    
    BigCity game = null;
    String expectedGrid = null;
    
    try {
      System.out.println("This should print BigCity without errors");
      game = new BigCity("CityGrid_GoodData4.txt");
      System.out.println(game); 
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    System.out.println("This should print true");
    System.out.println(game.isRoamingCity());
    
    System.out.println("\nAny valid move should end game. Go right.");
    game.move('d');
    
    System.out.println("\nGame is ended so this should print false");
    System.out.println(game.isRoamingCity());
    
    System.out.println("\nPrint BigCity again without errors Showing Suzie at top right corner");
    expectedGrid = ". s\n" + "b b\n";
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game);
  } // main 
} // class TestPhase4