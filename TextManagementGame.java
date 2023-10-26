import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * The TextManagementGame class represents a text-based management game where the player manages resources and resource generators.
 */
public class TextManagementGame {
    // Define game variables
    private int round;
    private ArrayList<Resource> resources = new ArrayList<Resource>();
    private ArrayList<Generator> generators = new ArrayList<Generator>();

    // Define a Scanner for user input
    private Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new TextManagementGame instance with initial resource and time values.
     * TODO : Add starting resources
     */
    public TextManagementGame() {
        round = 1;       // Start at time 1
    }

    /**
     * Check if a method should run with a 1 in number chance.
     *
     * @return true if the method should run, false otherwise
     */
    public boolean haveEventThisTurn(int number) {
        Random random = new Random();
        int chance = random.nextInt(number); // Generates a random number between 0 (inclusive) and number (exclusive)
        return chance == 0; // Returns true with a 1 in number chance
    }

    /**
    * Prints the list of resources
    */
    public void viewResources(){
        for(Resource r : resources){
            System.out.println(r);
        }
    }

    /**
    * Prints the list of Generators
    */
    public void viewGenerators(){
        for(Generator b : generators){
            System.out.println(b);
        }
    }

    /**
     * Checks if a Generator can be constructed and then adds it to the list of Generators
     * TODO : ADD LOGIC
     */
    public void constructGenerator(){
        
    }

    /** 
     * Increments the time counter and then adds more resources based on what generators are present
     * TODO : Add calculations to generate resources for the next turn
     */
    public void endRound(){
        round++;
    }

    /**
     * Adds a Generator object to the ArrayList of Generators.
     *
     * @param generator the Generator object to add
     */
    public void addGenerator(Generator generator) {
        generators.add(generator);
    }

    /**
     * Adds a Resource object to the ArrayList of resources.
     *
     * @param resource the Resource object to add
     */
    public void addResource(Resource resource) {
        resources.add(resource);
    }

    /**
     * Checks if we are out of any critical resources
     *
     * @return returns true if we are out of any critical resources returns false otherwise
    */
    public boolean isCriticalResourceEmpty(){
        for(Resource r : resources){
            if(r.isCritical() && r.getQuantity() == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Starts the game and manages the game loop.
     */
    public void start() {
        System.out.println("Welcome to the Text Management Game!"); //TODO: Change Text
        System.out.println("Your goal is to build a thriving city! You will start with 200");

        int oddsOfRandomEvent = 4; //a 4 is a 25% chance of a random event occuring

        // Main game loop
        while (!isCriticalResourceEmpty()) {
            System.out.println("\nTime " + round);
            if(haveEventThisTurn(oddsOfRandomEvent)){
                //TODO add logic for random events
                System.out.println("A random event happened!");
            }
            System.out.println("Options:");
            System.out.println("1. Collect resources");
            System.out.println("2. Manage resources");
            System.out.println("3. Add a new Generator");
            System.out.println("4. End round");
            System.out.println("5. Quit game");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewResources();
                    break;
                case 2:
                    viewGenerators();
                    break;
                case 3: 
                    constructGenerator();
                    break;
                case 4:
                    endRound();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Game Over! You ran out of resources.");
        System.out.println("You played for " + round + " rounds");
    }

    /**
     * Main method to run the game
     *
     * @param args the command-line arguments (not used in this game)
     */
    public static void main(String[] args) {
        TextManagementGame game = new TextManagementGame();
        game.start();
    }
}