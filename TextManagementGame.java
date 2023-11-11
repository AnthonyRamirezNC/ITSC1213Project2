import java.util.*;

/**
 * The TextManagementGame class represents a text-based management game where the player manages resources and resource generators.
 */
public class TextManagementGame {
    // Define game variables
    public static int round;
    private ArrayList<Resource> resources = new ArrayList<Resource>(Arrays.asList(new Gold(0), new Wood(0), new Stone(0)));
    private ArrayList<Generator> generators = new ArrayList<Generator>();

    private ArrayList<Event> events = new ArrayList<Event>(Arrays.asList(new StrikeEvent(), new DestroyGeneratorEvent(), new MisplacedResourcesEvent()));

    public int oddsOfRandomEvent = 4; //ex: 0 = disable events, 1 = always have events, 4 = 25% chance

    public int villCount = 0;

    public static boolean revivable = false;

    public static boolean ableToCollect = true;

    public static boolean breakout = false;

    // Define a Scanner for user input
    private Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new TextManagementGame instance with initial resource and time values.
     * TODO : Add starting resources
     */
    public TextManagementGame() {
        round = 1;       // Start at time 1
        revivable = false;  //set non revivable by default
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

            System.out.println(r.toString());
        }
    }

    /**
    * Prints the list of Generators
    */
    public void viewGenerators(){
        System.out.println("Current Generators:");
        Collections.sort(generators);
        for(Generator b : generators){
            System.out.println(b + "\n");
        }
    }

    /**
     * Checks if a Generator can be constructed and then adds it to the list of Generators
     * TODO : ADD LOGIC
     */
    public void constructGenerator(){
        GenCreateMenu();
    }

    /** 
     * Increments the time counter and then adds more resources based on what generators are present
     * TODO : Add calculations to generate resources for the next turn
     */
    public void endRound(){
        if(oddsOfRandomEvent > 0) {
            if (haveEventThisTurn(oddsOfRandomEvent)) {
                //TODO add logic for random events
                int eventChoice = (int) (Math.random() * events.size());
                events.get(eventChoice).triggerEvent(this);
            }
        }
        if(ableToCollect) {
            Helper.collectGeneratorResources(resources, generators);
        } else {
            System.out.println("You were unable to collect resources this round your citizens are on strike");
            ableToCollect = true;
        }
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
     * Checks if we can revive
     */
    public boolean isRevivable() {
        return revivable;
    }

    /**
     * Starts the game and manages the game loop.
     */
    public void start() {
        System.out.println("Welcome to the Text Management Game!"); //TODO: Change Text
        System.out.println("Your goal is to build a thriving city! You will start with 500 gold, when you run out of gold, you lose");
        //give starting resources
        resources.get(0).add(500);

        // Main game loop
        while (!isCriticalResourceEmpty() || isRevivable()) {
            System.out.println("\nRound " + round);
            System.out.println("Options:");
            System.out.println("1. View Current Resources");
            System.out.println("2. View Current Generators");
            System.out.println("3. Add a new Generator");
            System.out.println("4. Upgrade Generators");
            System.out.println("5. Sell Resources");
            System.out.println("6. End Round");
            System.out.println("7. Cheats");
            System.out.println("8. Quit Game");
            System.out.print("Choose an Option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Printing Resources");
                    viewResources();
                    break;
                case 2:
                    viewGenerators();
                    break;
                case 3:
                    //add logic to select which generator to make
                    constructGenerator();
                    break;
                case 4:
                    //upgrade generator menu
                    genUpgradeMenu();
                    break;
                case 5:
                    //sell resources menu
                    resourceSellMenu();
                    break;
                case 6:
                    //check for village in generators
                    for(Generator generator : generators){
                        if(generator instanceof Village){
                            //Village found during check
                            if(revivable == false){
                                Village.checkRevivableStatus();
                            }
                            villCount++;
                        }
                        villCount = 0;
                    }
                    endRound();
                    break;
                case 7:
                    cheatMenu();
                    break;
                case 8:
                    System.out.println("Game Over!");
                    if(round == 1){
                        System.out.println("You played for 1 round");
                        Helper.generateScore(this);
                        System.exit(0);
                    }else {
                        System.out.println("You played for " + round + " rounds");
                        Helper.generateScore(this);
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            villCount = 0;
            for(Generator generator : generators){
                if(generator instanceof Village){
                    villCount++;
                }
            }
            if(isCriticalResourceEmpty() && isRevivable() && villCount > 0){
                System.out.println("You have run out of gold but the villagers have pulled together funds for you.");
                System.out.println("You have gained 200 gold from each village");
                System.out.println("You recieved a total of " + (200 * villCount) + " gold");
                resources.get(0).add(200 * villCount);
                revivable = false;
                villCount = 0;
            }
        }

        System.out.println("Game Over! You ran out of resources.");
        System.out.println("You played for " + round + " rounds");
        Helper.generateScore(this);
    }

    /**
     *Gets user input on generator construction
     */
    public void GenCreateMenu(){
        System.out.println("Which Generator would you like to craft?");
        System.out.println("1. Mine");
        System.out.println(Mine.description);
        System.out.println("2. Village");
        System.out.println(Village.description);
        System.out.println("3. Lumberjacks");
        System.out.println(Lumberjacks.description);
        int choice = scanner.nextInt();
        boolean canCraft = false;
                switch(choice){
            case 1:
                System.out.println("This requires the following Resources:");
                for(Resource resource : Mine.contructionCost){
                    System.out.println(resource.getName()+ ": " + resource.getQuantity());
                }

                System.out.println("You have the following Resources:");
                for(Resource resource : resources){
                    System.out.println(resource.getName()+ ": " + resource.getQuantity());
                }

                canCraft = Helper.canConstruct(Mine.contructionCost, resources);
                if(canCraft){
                    System.out.println("Would you like to continue? (Y or N)");
                    scanner.nextLine();
                    String craftChoice = scanner.nextLine();
                    if(craftChoice.toLowerCase().equals("y")) {
                        //remove required resources and add generator
                        Helper.consumeConstructionCosts(Mine.contructionCost, resources);
                        addGenerator(new Mine());
                        System.out.println("You have created a Mine!");
                    }
                }
                else{
                    System.out.println("You do not have enough resources to create a Mine!");
                }
                break;

            case 2:
                System.out.println("This requires the following Resources:");
                for(Resource resource : Village.contructionCost){
                    System.out.println(resource.getName()+ ": " + resource.getQuantity());
                }

                System.out.println("You have the following Resources:");
                for(Resource resource : resources){
                    System.out.println(resource.getName()+ ": " + resource.getQuantity());
                }
                //check if construction costs are met
                canCraft = Helper.canConstruct(Village.contructionCost, resources);
                if(canCraft){
                    System.out.println("Would you like to continue? (Y or N)");
                    scanner.nextLine();
                    String craftChoice = scanner.nextLine();
                    if(craftChoice.toLowerCase().equals("y")) {
                        //remove required resources and add generator
                        Helper.consumeConstructionCosts(Village.contructionCost, resources);
                        addGenerator(new Village());
                        System.out.println("You have created a Village!");
                    }
                }
                else{
                    System.out.println("You do not have enough resources to create a Village!");
                }
                break;

            case 3:
                System.out.println("This requires the following Resources:");
                for(Resource resource : Lumberjacks.contructionCost){
                    System.out.println(resource.getName()+ ": " + resource.getQuantity());
                }

                System.out.println("You have the following Resources:");
                for(Resource resource : resources){
                    System.out.println(resource.getName()+ ": " + resource.getQuantity());
                }
                //check if construction costs are met
                canCraft = Helper.canConstruct(Lumberjacks.contructionCost, resources);
                if(canCraft){
                    System.out.println("Would you like to continue? (Y or N)");
                    scanner.nextLine();
                    String craftChoice = scanner.nextLine();
                    if(craftChoice.toLowerCase().equals("y")) {
                        //remove required resources and add generator
                        Helper.consumeConstructionCosts(Lumberjacks.contructionCost, resources);
                        addGenerator(new Lumberjacks());
                        System.out.println("You have hired Lumberjacks!");
                    }
                }
                else{
                    System.out.println("You do not have enough resources to hire Lumberjacks!");
                }
                break;
        }
    }
    /**
     *Gets user input on generator upgrade
     */
    public void genUpgradeMenu(){
        if(generators.size() == 0){
            System.out.println("No generators to upgrade");
            return;
        }
        System.out.println("Current Generators:");
        for(int i = 0; i < generators.size(); i++){
            System.out.println((i+1) + ".) " + generators.get(i).toString());
        }
        System.out.println("Which generators would you like to upgrade?");
        int choice = scanner.nextInt();
        if(choice <= generators.size()){
            ArrayList<Resource> upgradeCost = generators.get(choice - 1).getUpgradeCost();
            if(upgradeCost.size() > 0){
                System.out.println("This requires the following Resources:");
                for(Resource resource : upgradeCost){
                    System.out.println(resource.getName()+ ": " + resource.getQuantity());
                }
                System.out.println("\nYou have the following resources:");
                viewResources();

                System.out.println("Would you like to continue? (Y or N)");
                //flush buffer
                scanner.nextLine();
                String doContinue = scanner.nextLine();
                if(doContinue.toLowerCase().equals("y")){
                    System.out.println("upgrading generator");
                    generators.get(choice - 1).upgrade(resources);
                } else System.out.println("Generator not upgraded");
            }
        }else System.out.println("Invalid Choice");
    }

    /**
     *Gets user input on resource selling
     */
    public void resourceSellMenu(){
        System.out.println("Current Resources:");
        viewResources();
        int goldToAdd = 0;
        System.out.println("Which Resource do you want to sell?");
        System.out.println("1.) Wood (10 Gold Per)");
        System.out.println("2.) Stone (20 Gold Per)");
        int choice = scanner.nextInt();
        System.out.println("How much do you want to sell?");
        int sellAmount = scanner.nextInt();
        if(choice == 1){
            if(sellAmount <= resources.get(1).getQuantity()) {
                goldToAdd = sellAmount * 10;
                resources.get(0).add(goldToAdd);
                resources.get(choice).consume(sellAmount);
                System.out.println("You sold " + sellAmount + " Wood for " + goldToAdd + " gold.");
            }else System.out.println("Not enough resources to sell");

        }else if(choice ==2){
            if(sellAmount <= resources.get(2).getQuantity()) {
                goldToAdd = sellAmount * 20;
                resources.get(0).add(goldToAdd);
                resources.get(choice).consume(sellAmount);
                System.out.println("You sold " + sellAmount + " Stone for " + goldToAdd + " gold.");
            }else System.out.println("Not enough resources to sell");
        }else System.out.println("Invalid Choice");
    }

    /**
     * Open cheat menu ONLY AVAILABLE AFTER ROUND 15
     */
    public void cheatMenu(){
        System.out.println("\nopening cheat menu");
        System.out.println("Round: " + round);
        while(breakout != true){
            if (round >= 15) {
                System.out.println("Welcome to the cheat menu, what would you like to do?");
                System.out.println("1. Change Round");
                System.out.println("2. Add Resources");
                System.out.println("3. Add Generators");
                System.out.println("4. Upgrade Generators");
                System.out.println("5. Set Event Chance");
                System.out.println("6. Set Revivable");
                System.out.println("7. Exit Cheat Menu");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("What round would you like to go to?");
                        System.out.println("Current Round:" + round);
                        System.out.print("New Round: ");
                        int newRound = scanner.nextInt();
                        round = newRound;
                        break;
                    case 2:
                        System.out.println("Current Resources:");
                        viewResources();
                        System.out.println("How much gold: ");
                        int newGold = scanner.nextInt();
                        System.out.println("How much stone: ");
                        int newStone = scanner.nextInt();
                        System.out.println("How much wood: ");
                        int newWood = scanner.nextInt();
                        ArrayList<Resource> resourcesToAdd = new ArrayList<Resource>(Arrays.asList(new Gold(newGold), new Stone(newStone), new Wood(newWood)));
                        Helper.addToExistingResources(resources, resourcesToAdd);
                        System.out.println("New Current Resources:");
                        viewResources();
                        break;
                    case 3:
                        viewGenerators();
                        System.out.println("Which Generator would you like to spawn?");
                        System.out.println("1.) Mine");
                        System.out.println(Mine.description);
                        System.out.println("2.) Village");
                        System.out.println(Village.description);
                        System.out.println("3.) Lumberjacks");
                        System.out.println(Lumberjacks.description);
                        int genChoice = scanner.nextInt();
                        switch(genChoice){
                            case 1:
                                generators.add(new Mine());
                                System.out.println("Mine added");
                                break;
                            case 2:
                                generators.add(new Village());
                                System.out.println("Village added");
                                break;
                            case 3:
                                generators.add(new Lumberjacks());
                                System.out.println("Lumberjacks added");
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                        break;
                    case 4:
                        //display current generators
                        System.out.println("Current Generators:");
                        for(int i = 0; i < generators.size(); i++){
                            System.out.println((i+1) + ".) " + generators.get(i).toString());
                        }
                        System.out.println("Which generators would you like to upgrade?");
                        int genUpgradeChoice = scanner.nextInt();
                        if(genUpgradeChoice <= generators.size()) {
                            //upgrade using arraylist with infinite resources
                            generators.get(genUpgradeChoice - 1).upgrade(new ArrayList<Resource>(Arrays.asList(new Gold(999999), new Stone(999999), new Wood(999999))));
                            if(generators.get(genUpgradeChoice - 1).currentLevel <3) {
                                System.out.println(generators.get(genUpgradeChoice - 1).getName() + " upgraded to level " + generators.get(genUpgradeChoice - 1).getCurrentLevel());
                            }
                        }else System.out.println("Invalid Choice");
                        break;
                    case 5:
                        System.out.println("Current event chance: " + oddsOfRandomEvent + " (ex: 0 = disable events, 1 = always have events, 4 = 25% chance)");
                        System.out.println("New event Chance: ");
                        this.oddsOfRandomEvent = scanner.nextInt();
                        break;
                    case 6:
                        if(!isRevivable()){
                            revivable = true;
                            System.out.println("Activated Revive for 1 death (must be reactivated after death and must have a village)");
                        }else System.out.println("Already revivable");
                        break;
                    case 7:
                        breakout = true;
                        break;
                    default:
                        break;
                }
            }
            else {
                System.out.println("Cheat Menu unlocks at round 15");
                breakout = true;
            }
        }
        breakout = false;
    }
    /**
     * get generators
     */
    public ArrayList<Generator> getGenerators(){return this.generators;}

    /**
     * get resources
     */
    public ArrayList<Resource> getResources(){return this.resources;}

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