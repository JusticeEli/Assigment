import java.io.IOException;

public class TestPhase7 {
  public static void main(String [] parms) {
    
    testMovesAndNumCheeseCountersAfterUndo();
    testsaveGridUsingUndo();    
  } // main
  
  public static void testMovesAndNumCheeseCountersAfterUndo() {
    
    System.out.println("Testing numMoves and numCheeseCollected counters with undo()...\n\n");
    
    BigCity game = null;
    String expectedGrid = null;
    
    try {
      System.out.println("This should print BigCity without errors");
      game = new BigCity("CityGrid_GoodData6.txt");
      
      System.out.println(game);
      System.out.println("This 3x3 grid has cheese at (0,1), (0,2), and (1,0)");
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    System.out.println();
    System.out.println("Eat cheese at (0,1) - total moves = 1, numCheeseCollected = 1\n" +
                       "Eat cheese at (0,2) - total moves = 2, numCheeseCollected = 2\n" +
                       "undo() takes s to (0,1) - total moves = 1, numCheeseCollected = 1\n" + 
                       "open trap at (1,1) to end game and print stats - total moves = 2, numCheeseCollected = 1");
    
    game.move('d');
    game.move('d');
    game.undo();
    game.move('s');
    
    expectedGrid = ". . b\n" + "b s .\n" + "b . .\n" +
      "Suzie’s reign of terror came to an end when she met a trap instead of cheese.\nmoves - 2" +
      "\ncheese collected - 1\n";
    
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game); 
    
  } // testMovesAndNumCheeseCountersAfterUndo
  
  public static void testsaveGridUsingUndo() {
     
    System.out.println("Testing saveGrid() and undo() methods...\n\n");
    
    BigCity game = null;
    String expectedGrid = null;
    
    try {
      System.out.println("This should print BigCity without errors");
      game = new BigCity("CityGrid_GoodData2.txt");
      System.out.println(game); 
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
    /***************Move once and test undo***************/
    System.out.println("/***************Move once and test undo***************/");
    System.out.println("Move right once, and undo()");
    
    // buffer will contain 1 grid after this.
    game.move('d');
    expectedGrid = ". s .\n" + ". . b\n" + ". . .\n";
    
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game); 
    
    
    // after a call to undo(), buffer should be empty
    game.undo();
    expectedGrid = "s . .\n" + ". . b\n" + ". . .\n";
    
    System.out.println("Now undo()");
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game); 
    
    
    /***************Test case when buffer is empty***************/
    System.out.println("/***************Test case when buffer is empty***************/");
    System.out.println("Buffer is empty so undo() should " +
                       "not make changes to the grid.\n" +
                       "This should print:\n" + expectedGrid);
    System.out.println(game); 
    
    /***************Move twice and test undo***************/
    System.out.println("/***************Move right twice and test undo***************/");
    expectedGrid = ". . s\n" + ". . b\n" + ". . .\n";
    
    game.move('d');
    game.move('d');
    
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game); 
    
    // after a call to undo(), buffer should contain 1 item
    game.undo();
    expectedGrid = ". s .\n" + ". . b\n" + ". . .\n";
    
    System.out.println("Now undo()");
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game); 
    
    /***************Test buffer after 6 moves***************/
    System.out.println("/***************Test buffer after 6 moves***************/");
    System.out.println("We are going to make an anti-clockwise U-turn to (0,0) - 5 moves total");
    
    expectedGrid = "s . .\n" + ". . b\n" + ". . .\n";
    
    System.out.println(game); 
    System.out.println("down");
    game.move('s');
    System.out.println(game);
    System.out.println("down");
    game.move('s');
    System.out.println(game);
    System.out.println("left");
    game.move('a');
    System.out.println(game);
    System.out.println("up");
    game.move('w');
    System.out.println(game);
    System.out.println("up");
    game.move('w');
    
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game); 
    
    // At this point the buffer is definitely full. Make one more move
    // to remove saved grid with s at (0,1).
    System.out.println("Make one more move down to get rid of the saved grid with s at (0,1)");
    System.out.println("down");
    game.move('s');
    
    expectedGrid = ". . .\n" + "s . b\n" + ". . .\n";
    System.out.println("This should print:\n" + expectedGrid);
    System.out.println(game); 
    
    // We will try to undo 6 times. We should only get to the grid where s was at 
    System.out.println("If our buffer works right, doing 5 or more undo operations should give " +
                       "us the frame with s at (1,1).\nWe will perform 6");
    
    System.out.println("\nundo #1: s at (0,0)");
    game.undo();
    System.out.println(game); 
    
    System.out.println("\nundo #2: s at (1,0)");
    game.undo();
    System.out.println(game); 
    
    System.out.println("\nundo #3: s at (2,0)");
    game.undo();
    System.out.println(game); 
    
    System.out.println("\nundo #4: s at (2, 1)");
    game.undo();
    System.out.println(game); 
    
    System.out.println("\nundo #5: s at (1,1)");
    game.undo();
    System.out.println(game); 
    
    System.out.println("Since the buffer can hold only 5 frames and we have done 5 undo's, calling undo() " +
                       "should not change our grid.");
    
    System.out.println("\nundo #6: s at (1,1)");
    game.undo();
    System.out.println(game); 
    
    System.out.println("\nundo #7: s at (1,1)");
    game.undo();
    System.out.println(game);
    
    
  }
}