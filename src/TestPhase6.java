import java.io.IOException;

public class TestPhase6 {
  public static void main(String [] parms) {

    System.out.println("Testing updated toString() method...\n\n");
    
    BigCity game1 = null;
    BigCity game2 = null;
    String expectedGrid = null;
    
    try {
      System.out.println("This should print BigCity without errors");
      
      game1 = new BigCity("CityGrid_GoodData5.txt");
      game2 = new BigCity("CityGrid_GoodData5.txt");
      
      System.out.println(game1); 
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    System.out.println("End the game by collecting all the cheese");
    game1.move('s');
    game1.move('d');
    expectedGrid = ". b\n" + ". s\n" +
      "Suzie outsmarted the exterminators and got away.\nmoves - 2\ncheese collected - 2" +
      "\ntraps escaped - 1\n";
    
    System.out.println("\nThis should print:\n" + expectedGrid);
    System.out.println(game1);
    
    
    System.out.println("\nLet's try to end the game early this time");
    game2.move('d');
    expectedGrid = ". s\n" + "b b\n" +
      "Suzie’s reign of terror came to an end when she met a trap instead of cheese.\nmoves - 1" +
      "\ncheese collected - 0\n";
    
    System.out.println("\nThis should print:\n" + expectedGrid+"\n");
    System.out.println(game2);
    
  } // main 
} // class TestPhase4