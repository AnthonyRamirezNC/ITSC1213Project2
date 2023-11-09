import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Generator class represents a generic resource generating item in the game.
 * Generators have a name, a construction cost, and a resource production rate.
 */
abstract class Generator {
    private String name;
    private ArrayList<Resource> constructionCost;

    private ArrayList<Resource> levelTwoContructionCost;

    private ArrayList<Resource> levelThreeContructionCost;


    private int resourceProductionRate;
    private int numberConstructed;
    private Resource product;

    public int currentLevel;

    /**
     * Creates a new Generator with the given name, construction cost, and resource production rate.
     *
     * @param name                  the name of the Generator
     * @param constructionCost      the cost in resources required to construct the Generator
     * @param resourceProductionRate the rate at which the Generator produces resources per unit of time
     * @param numberConstructed     the number of units of this generator constructed at this time
     * @param product               the type of resource this generator produces
     */
    public Generator(String name, ArrayList<Resource> constructionCost, ArrayList<Resource> levelTwoContructionCost, ArrayList<Resource> levelThreeContructionCost, int resourceProductionRate, int numberConstructed, Resource product, int currentLevel) {
        this.name = name;
        this.constructionCost = constructionCost;
        this.levelTwoContructionCost = levelTwoContructionCost;
        this.levelThreeContructionCost = levelThreeContructionCost;
        this.resourceProductionRate = resourceProductionRate;
        this.numberConstructed = numberConstructed;
        this.product = product;
        this.currentLevel = currentLevel;
    }

    /**
     * Gets the name of the Generator.
     *
     * @return the name of the Generator
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the construction cost of the Generator.
     *
     * @return the construction cost of the Generator
     */
    public ArrayList<Resource> getConstructionCost() {
        return constructionCost;
    }

    /**
     * Gets the resource production rate of the Generator.
     *
     * @return the resource production rate of the Generator
     */
    public int getResourceProductionRate() {
        return resourceProductionRate;
    }

    /**
     * Sets the resource production rate of the Generator.
     */
    public void setResourceProductionRate(int newProductionRate) {
        this.resourceProductionRate = newProductionRate;
    }

    /**
     * Gets the number of units constructed of this Generator.
     *
     * @return the number of units constructed of the generator
     */
    public int getNumberConstructed() {
        return numberConstructed;
    }

    public String toString(){
        return name + " lvl " + this.getCurrentLevel() + "\nProduction Rate: " + this.getResourceProductionRate();
    }

    public void upgrade(ArrayList<Resource> currentResources){
        this.currentLevel++;
        System.out.println("upgrading, no logic");
    }

    public ArrayList<Resource> getUpgradeCost(){
        if(this.currentLevel == 1){
            return this.levelTwoContructionCost;
        }
        else if(this.currentLevel == 2){
            return this.levelThreeContructionCost;
        }
        else System.out.println("no more upgrades can be done");
        return new ArrayList<Resource>();
    }

    public int getCurrentLevel(){
        return this.currentLevel;
    }

    public Resource getProduct(){return this.product;}
}