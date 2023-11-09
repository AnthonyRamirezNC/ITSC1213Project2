import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Lumberjacks extends Generator{

    public static ArrayList<Resource> contructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(100)));

    public static ArrayList<Resource> levelTwoContructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(200), new Wood(100), new Stone(100)));

    public static ArrayList<Resource> levelThreeContructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(500), new Wood(300), new Stone(300)));


    /**
     * Creates a new Generator with the given name, construction cost, and resource production rate.
     */
    public Lumberjacks() {
        super("lumberjacks", contructionCost, levelTwoContructionCost, levelThreeContructionCost, 50, 1, new Wood(1), 1);
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
        else System.out.println("Generator at max level");
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
}
