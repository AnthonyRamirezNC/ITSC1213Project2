import java.util.ArrayList;
import java.util.Arrays;

public class Village extends Generator{

    public static ArrayList<Resource> contructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(100), new Stone(50), new Wood(50)));
    Village(){
        super("village", contructionCost, 30, 1, new Gold());
    }

    /**
     * allowed to revive after every 10 rounds
    */
    public void setRevivable(){
        if(TextManagementGame.round % 10 == 0){
            TextManagementGame.revivable = true;
        }
    }


}