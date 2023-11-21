import java.util.Random;

public class SimpleStartupGame {
    public static void main(String[] args) {
        Random rand = new Random();
        GameHelper helper = new GameHelper();
        int cellPos0 = rand.nextInt(5);
        int numOfGuesses = 0;

        SimpleStartup st = new SimpleStartup();
        boolean isAlive = true;

        int[] Locations = new int[3];
        for (int i = 0; i < 3; i++){
            Locations[i] = cellPos0 + i;
        } 
        st.setLocationCells(Locations);

        while (isAlive){
            int guess = helper.getUserInput("enter a number");
            numOfGuesses++;
            String result = st.checkYourself(guess);
            if (result.equals("kill")) {
                isAlive = false;
                System.out.println("You took " + numOfGuesses + " guesses");
            }
        }
    }
    
}
