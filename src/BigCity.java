import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class BigCity {
    char grid[][];
    int numBoxes;
    int numCheese;
    int cheesePositions[][];


    int numberOfCheeseCrampsCollected;
    int movesMadeBySuzie;
    boolean suzieIsLoose = true;


    char suziesLast5Steeps[][];

    BigCity(int rows, int cols, int numBoxes, int numCheese, int[][] cheesePositions) {
        grid = new char[rows][cols];
        suziesLast5Steeps = new char[5][];

        this.numBoxes = numBoxes;
        this.numCheese = numCheese;
        this.cheesePositions = cheesePositions;

        numberOfCheeseCrampsCollected = 0;
        movesMadeBySuzie = 0;
        fillGride();
    }

    BigCity(String fileName) throws IOException {
        suziesLast5Steeps = new char[5][];

        numberOfCheeseCrampsCollected = 0;
        movesMadeBySuzie = 0;
        readFile(fileName);
        fillGride();


    }

    int row = 0;
    int column = 0;

    void move(char direction) {
        int dummyRow = row;
        int dummyColumn = column;
        switch (direction) {
            case 'w':
                dummyRow = row - 1;
                break;
            case 's':
                dummyRow = row + 1;
                break;
            case 'a':
                dummyColumn = column - 1;
                break;
            case 'd':
                dummyColumn = column + 1;
                break;
        }

        //Throw appropriate IndexOutOfBoundsException for moving off the grid
        if (dummyRow == -1 || dummyRow >= grid.length || dummyColumn == -1 || dummyColumn >= grid[0].length) {
            throw new IndexOutOfBoundsException("Suzie moved of the grid");
        }

        processMove(direction);

    }

    private void processMove(char direction) {
        // increase her move count
        movesMadeBySuzie++;

        saveGrid();

        switch (direction) {
            case 'w':
                row--;
                break;
            case 's':
                row++;
                break;
            case 'a':
                column--;
                break;
            case 'd':
                column++;
                break;
        }

        //logic for increasing  cheese numberOfCheeseCrampsCollected++; or end game by calling endTerror();
        if (isGameOver()) {
            // end game by calling endTerror ();
            endTerror();
        } else if (grid[row][column] == 'b') {
            // increase cheese
            numberOfCheeseCrampsCollected++;
        }
        //updating grid to show Suzie's movements
        switch (direction) {
            case 'w':
                grid[row + 1][column] = '.';
                break;
            case 's':
                grid[row - 1][column] = '.';

                break;
            case 'a':
                grid[row][column + 1] = '.';

                break;
            case 'd':
                grid[row][column - 1] = '.';
                break;
        }
        //set the char at the new position to ‘s’
        grid[row][column] = 's';


    }

    private boolean isGameOver() {
        boolean over = true;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] != '.') {
                    over = false;
                }
            }
        }
        for (int i = 0; i < trapList.size(); i++) {
            if (trapList.get(i).getKey() == row && trapList.get(i).getValue() == column) {
                over = true;
            }
        }

        return over;
    }

    //  changes the value of the variable indicating that Suzie is still roaming the city.
    private void endTerror() {
        suzieIsLoose = false;
    }

    //tells the outside world if Suzie is still on the loose in the Big City!
    boolean isRoamingCity() {
        return suzieIsLoose;
    }

    private void readFile(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList();
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            // Read from the stream
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                //     System.out.println(currentLine);
                lines.add(currentLine);
            }
        } catch (IOException e) {
            throw new IOException("File specified was not found");
        }


        //check dimensions
        String dimensions[] = lines.get(0).split(" ");
        if (dimensions.length < 2) {
            throw new IOException("No dimension to read");
        }
        int rows = Integer.parseInt(dimensions[0]);
        int columns = Integer.parseInt(dimensions[1]);
        grid = new char[rows][columns];

        //check grids

        for (int row = 1; row <= rows; row++) {
            //checking if column dimensions is correct
            String columnStream[] = lines.get(row).split(" ");
            if (columnStream.length != columns) {
                throw new IOException("Inaccurate number of columns of data to read");
            }
        }

        //checking if initialization of number of boxes is correct

        int startOfNumberOfBoxes = rows + 1;
        if (lines.get(startOfNumberOfBoxes).split(" ").length != 1) {
            throw new IOException("Inaccurate Initialization of box number");

        }
        numBoxes = Integer.parseInt(lines.get(startOfNumberOfBoxes).split(" ")[0]);
        //checking if initialization of number of cheese is correct

        int startOfNumberOfCheese = startOfNumberOfBoxes + 1;
        if (lines.get(startOfNumberOfCheese).split(" ").length != 1) {
            throw new IOException("Inaccurate Initialization of Cheese number");

        }

        numCheese = Integer.parseInt(lines.get(startOfNumberOfCheese).split(" ")[0]);

        //checking cheese locations
        int startOfCheeseLocations = startOfNumberOfCheese + 1;
        int j = 0;
        for (int i = startOfCheeseLocations; i < lines.size(); i++) {
            j++;
            if (lines.get(i).split(" ").length != 2) {
                throw new IOException("Inaccurate number of columns of cheese in the file");

            }
        }
        if (j != numCheese) {
            throw new IOException("Inaccurate number of cheese positions provided");

        }
        cheesePositions = new int[numCheese][2];
        j = 0;
        for (int i = startOfCheeseLocations; i < lines.size(); i++) {
            String[] line = lines.get(i).split(" ");
            cheesePositions[j][0] = Integer.parseInt(line[0]);
            cheesePositions[j][1] = Integer.parseInt(line[1]);
            if (j == numCheese) {
                break;
            }
            j++;
        }


    }

    private void fillGride() {

        grid[0][0] = 's';

        for (int rows = 0; rows < cheesePositions.length; rows++) {
            int cheese[] = cheesePositions[rows];
            grid[cheese[0]][cheese[1]] = 'b';

        }


        int numberOfTraps = numBoxes - numCheese;
        if (numberOfTraps > 0) {
            getRandomPosition(numberOfTraps, cheesePositions);
        }


        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == '\u0000') {
                    grid[row][column] = '.';
                }
            }
        }
    }



    private void getRandomPosition(int numberOfTraps, int[][] cheesePositions) {
        ArrayList<Map.Entry<Integer, Integer>> gridClone = new ArrayList<>();

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                gridClone.add(new AbstractMap.SimpleEntry(row, column));

            }
        }


        for (int i = 0; i < cheesePositions.length; i++) {
            int cheese[] = cheesePositions[i];
            try {
                gridClone.remove(gridClone.indexOf(new AbstractMap.SimpleEntry(cheese[0], cheese[1])));
            } catch (ArrayIndexOutOfBoundsException e) {
            }

        }
        try {
            gridClone.remove(gridClone.indexOf(new AbstractMap.SimpleEntry(0, 0)));
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        for (int i = 0; i < numberOfTraps; i++) {
            Random rand = new Random();
            Map.Entry<Integer, Integer> choosen = gridClone.get(rand.nextInt(gridClone.size()));
            int choosenRow = choosen.getKey();
            int chooseColumn = choosen.getValue();
            trapList.add(new AbstractMap.SimpleEntry(choosenRow, chooseColumn));
            try {
                gridClone.remove(gridClone.indexOf(new AbstractMap.SimpleEntry(choosenRow, chooseColumn)));
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            grid[choosenRow][chooseColumn] = 'b';


        }
    }



    ArrayList<Map.Entry<Integer, Integer>> trapList = new ArrayList<>();

    @Override
    public String toString() {
        String value = "";
        for (int rows = 0; rows < grid.length; rows++) {
            for (int columns = 0; columns < grid[rows].length; columns++) {
                value += grid[rows][columns] + "\t";
            }
            value += "\n";
        }
        String status = "";
        if (isRoamingCity()) {
            status = "\nSuzie outsmarted the exterminators, making " + movesMadeBySuzie + " moves and collecting all " + numberOfCheeseCrampsCollected + " cheese crumbs. She sniffed out the " + (numBoxes - numCheese) + " traps. ";
        } else {
            status = "\nSuzie’s reign of terror came to an end abruptly after just " + movesMadeBySuzie + "  moves. She was captured with " + numberOfCheeseCrampsCollected + "  cheese crumb on her person. ";
        }

        return value;
    }

    char[] extractRow(int rowNum) throws DataDoesNotExistException {
        if (rowNum < 0 || rowNum >= grid.length) {
            throw new DataDoesNotExistException(" BigCity grid does not have a row index of  " + rowNum);
        }
        return grid[rowNum];
    }

    char[] extractColumn(int colNum) throws DataDoesNotExistException {
        if (colNum < 0 || colNum >= grid[0].length) {
            throw new DataDoesNotExistException(" BigCity grid does not have a column index of  " + colNum);
        }
        char[] column = new char[grid.length];
        for (int i = 0; i < column.length; i++) {
            column[i] = grid[i][colNum];
        }
        return column;
    }

    private void saveGrid() {
        // initially set to value parameter so the first iteration, the value is replaced by it
        char prevValue[] = {(char) row, (char) column};

        // Shift all elements to the right, starting at pos
        for (int i = 0; i < suziesLast5Steeps.length; i++) {
            char[] tmp = prevValue;
            prevValue = suziesLast5Steeps[i];
            suziesLast5Steeps[i] = tmp;
        }
    }


    void undo() {
        if (suziesLast5Steeps[0][0] == '\u0000') {
            //If the partially-filled array is empty, it should do nothing.
        } else {
            row = suziesLast5Steeps[0][0];
            column = suziesLast5Steeps[0][1];

            int j = 0;

            for (int i = 0; i < suziesLast5Steeps.length; i++) {
                if (i != 0) {
                    suziesLast5Steeps[j++] = suziesLast5Steeps[i];
                }
            }
            while (j < suziesLast5Steeps.length) {
                suziesLast5Steeps[j++] = new char[2];
            }


        }
    }


}

class DataDoesNotExistException extends Exception {
    DataDoesNotExistException(String message) {
        super(message);
    }
}