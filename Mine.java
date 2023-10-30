import java.util.*;
public class Mine extends Generator{
    public static ArrayList<Resource> contructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(100)));
    Mine(){
        super("mine", contructionCost, 5, 1, new Stone());
    }
}