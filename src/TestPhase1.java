public class TestPhase1 {
  public static void main(String [] parms) {
    testGenerateGrid();
  }
  
  public static void testGenerateGrid() {
    
    int [][] cheeseGridOne = {{3, 1}, {0, 3}}; 
    int [][] cheeseGridTwo = {{2, 2}}; 
    int [][] cheeseGridThree = {{3, 1}, {0, 2}, {4, 3}, {1, 0}};
    int [][] cheeseGridFour = {{4, 3}};
    
    
    BigCity game1 = new BigCity(4, 5, 3, 2, cheeseGridOne);
    System.out.println("This should print BigCity with:\n" +
                       "s at (0,0), \n" +
                       "b at (0,3), (3,1), with one random b:\n");
    
    System.out.println(game1);
    
    
    BigCity game2 = new BigCity(3, 3, 1, 1, cheeseGridTwo);
    System.out.println("This should print BigCity with:\n" +
                       "s at (0,0), \n" +
                       "b at (2,2)\n" +
                       "and no extra b's\n");
    
    System.out.println(game2);
    
    
    BigCity game3 = new BigCity(7, 5, 8, 4, cheeseGridThree);
    System.out.println("This should print BigCity with:\n" +
                       "s at (0,0), \n" +
                       "b at (0,2), (1,0), (3,1), (4,3)\n" +
                       "and 4 random b's\n");
    
    System.out.println(game3);
    
    
    BigCity game4 = new BigCity(6, 6, 6, 1, cheeseGridFour);
    System.out.println("This should print BigCity with:\n" +
                       "s at (0,0), \n" +
                       "b at (4,3)\n" + 
                       "and 5 random b's\n");
    
    System.out.println(game4);
  }
}