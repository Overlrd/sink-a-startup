import java.util.ArrayList;

public class StartupBust {
    // Decalre and initialize the variables we will need
    private GameHelper helper = new GameHelper();
    private ArrayList<Startup> startups = new ArrayList<Startup>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        // Make three startups objects , give 'em names, and stick 'em in the ArrayList
        Startup one = new Startup();
        one.setName("pontez");
        Startup two = new Startup();
        two.setName("hacqi");
        Startup three = new Startup();
        three.setName("cabista");
        startups.add(one);
        startups.add(two);
        startups.add(three);

        // Print brief intoduction for the user 
        System.out.println("Your goal is to sink three startups,");
        System.out.println("pontez, hacqi and cabista,");
        System.out.println("Try to sink them all in the fewest number of guesses");


        // ask the helper for a startup location
        for (Startup startup: startups) {
            // repeat with each startup in the list
            ArrayList<String> newLocation = helper.placeStartup(3);
            // Call the setter method on this startup to give it the location we just got from the helper
            startup.setLocationCells(newLocation);
        }
    }
    private void startPlaying() {
        // as long as the startup list is not empty
        while (!startups.isEmpty()) {
            // get user input
            String userGuess = helper.getUserInput("Enter a guess: ");
            // Call our own checkUserGuess method
            checkUserGuess(userGuess);
        }
        // call our finish game method
        finishGame();
    }
    private void checkUserGuess(String userGuess) {
        // increment the number of guesses the user has made
        numOfGuesses++;
        // assume it's a miss unless told otherwise
        String result = "miss";

        // repeat with all the startups on the list
        for (Startup startupToTest: startups) {
            // ask the startup to check the user guess, looking for 'hit' or 'kill'
            result = startupToTest.checkYourself(userGuess);

            if (result.equals("hit")) {
                // get out of the loop early, no point in testing others
                break;
            }
            if (result.equals("kill")) {
                // this one ise dead so take it out of the startups list then get out of the loop
                startups.remove(startupToTest);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("All startups are dead! Your stock is now worthless");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            // Print a message telling the user how they did in the game
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options");
        }
    }

    public static void main (String[] args) {
        // create the game objct
        StartupBust game = new StartupBust();
        // tell the game object to set up the game
        game.setUpGame();
        // Tell the game to start the main game play loop (keeps asking for user input and checking the guess)
        game.startPlaying();
    }
} 
