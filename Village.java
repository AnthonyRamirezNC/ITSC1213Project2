import java.util.ArrayList;
import java.util.Arrays;

public class Village extends Generator{

    public static ArrayList<Resource> contructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(100), new Stone(50), new Wood(50)));

    public static ArrayList<Resource> levelTwoContructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(500), new Stone(300), new Wood(300)));

    public static ArrayList<Resource> levelThreeContructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(1000), new Stone(500), new Wood(500)));


    Village(){
        super("village", contructionCost, levelTwoContructionCost, levelThreeContructionCost, 30, 1, new Gold(1), 1);
    }

    /**
     * allowed to revive after every 10 rounds
    */
    public static void checkRevivableStatus(){
        if(((TextManagementGame.round + 1) - 10) >= 0){
            //next round at or higher than 10
            if((TextManagementGame.round + 1)% 10 == 0) {
                //next round at multiple of 10
                //setting revivable to true
                TextManagementGame.revivable = true;
            }
        }
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