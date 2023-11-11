import java.util.*;
public class Mine extends Generator{
    public static ArrayList<Resource> contructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(100)));

    public static ArrayList<Resource> levelTwoContructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(300), new Stone(200)));

    public static ArrayList<Resource> levelThreeContructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(500), new Stone(500), new Wood(500)));

    public static String description = "The Mine generates stone every round";


    Mine(){
        super("mine", contructionCost, levelTwoContructionCost, levelThreeContructionCost, 50, 1, new Stone(1), 1);
    }

    @Override
    public void upgrade(ArrayList<Resource> currentResources){
        boolean canUpgrade = false;
        if(this.currentLevel == 1){
            //check if enough resources to upgrade
            canUpgrade = Helper.canConstruct(levelTwoContructionCost, currentResources);
            if(canUpgrade) {
                //consume resources and upgrade level and corresponding values
                Helper.consumeConstructionCosts(levelTwoContructionCost, currentResources);
                this.currentLevel = 2;
                this.setResourceProductionRate(100);
            } else System.out.println("Not enough resources to upgrade");
        }
        else if(this.currentLevel == 2){
            canUpgrade = Helper.canConstruct(levelThreeContructionCost, currentResources);
            if(canUpgrade) {
                Helper.consumeConstructionCosts(levelThreeContructionCost, currentResources);
                this.currentLevel = 3;
                this.setResourceProductionRate(200);
            } else System.out.println("Not enough resources to upgrade");
        }
        else System.out.println("no more upgrades can be done");
    }

    @Override
    public ArrayList<Resource> getConstructionCost(){
        if(this.currentLevel == 1){
            return contructionCost;
        }
        else if(this.currentLevel == 2){
            return levelTwoContructionCost;
        }
        else return levelThreeContructionCost;
    }

    @Override
    public String toString(){
        return this.getName() + " lvl " + this.getCurrentLevel() + "\nProduction Rate: " + this.getResourceProductionRate();
    }

    @Override
    public void printDescription(){
        System.out.println(description);
    }
}