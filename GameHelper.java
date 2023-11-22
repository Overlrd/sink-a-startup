import java.util.*;

public class GameHelper {
    private static final String ALPHABET = "abcdefg";
    private static final int GRID_LENGTH = 7;
    private static final int GRID_SIZE = 49;
    private static final int MAX_ATTEMPTS = 200;
    static final int HORIZONTAL_INCREMENT = 1;
    static final int VERTICAL_INCREMENT = GRID_LENGTH;

    private final int[] grid = new int[GRID_SIZE];
    private final Random random = new Random();
    private int startupCount = 0;

    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    public ArrayList<String> placeStartup(int startupSize) {
        // holds the index to grid (0 - 48)
        int [] startupCoords = new int[startupSize]; // current candidate coordinates
        int attempts = 0;                            // current attemps counter
        boolean success = false;                     // flag - found a good location ?

        startupCount++;                              // nth startup to place
        int increment = getIncrement();              // alternate vert & horiz alignment

        while(!success & attempts++ < MAX_ATTEMPTS) {
            int location = random.nextInt(GRID_SIZE);               // get random starting point 

            for (int i = 0; i < startupCoords.length; i++) {        // create array of proposed coords
                startupCoords[i] = location;                        // put current location in array
                location += increment;                              // calculate the next location
            }
            if (startupFits(startupCoords, increment)) {             // startup fits the grid ?
                success = coordsAvailable(startupCoords);            // ... and location aren't taken ?
            }
        }  
        savePositionToGrid(startupCoords);                          
        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
        return alphaCells;
    }

    private boolean startupFits(int[] startupCoords, int increment) {
        int finalLocation = startupCoords[startupCoords.length - 1];
        if (increment == HORIZONTAL_INCREMENT) {
            // check en is on the same row as start
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
        } else {
            // check isn't off the bottom
            return finalLocation < GRID_SIZE;
        }
    }
    private boolean coordsAvailable(int[] startupCoords) {
        for (int coord: startupCoords) {                            // check all the positions
            if (grid[coord] != 0) {                                 // this position is already taken
                return false;
            }
        }
        return true;                                                // there were no clashes , coords available
    }
    private void savePositionToGrid(int[] startupCoords) {
        for (int index: startupCoords) {
            grid[index] = 1;
        }
    }
    private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        for (int index: startupCoords) {
            String alphaCoords = getAlphaCoordsFromIndex(index);
            alphaCells.add(alphaCoords);
        }
        return alphaCells;
    }
    private String getAlphaCoordsFromIndex(int index) {
        int row = calcRowFromIndex(index);                          // get row value
        int column = index % GRID_LENGTH;                           // column is the modulo of the GRID_LENGTH by the index
        String letter = ALPHABET.substring(column, column + 1);     // convert to letter
        return letter + row;
    }
    private int calcRowFromIndex(int index) {
        return index / GRID_LENGTH;
    }
    private int getIncrement() {
        if (startupCount % 2 == 0) {
            return HORIZONTAL_INCREMENT;
        } else {
            return VERTICAL_INCREMENT;
        }
    }
}
