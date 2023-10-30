import java.util.ArrayList;
import java.util.Arrays;

public class Lumberjacks extends Generator{

    public static ArrayList<Resource> contructionCost = new ArrayList<Resource>(Arrays.asList(new Gold(100)));
    /**
     * Creates a new Generator with the given name, construction cost, and resource production rate.
     *
     * @param name                   the name of the Generator
     * @param constructionCost       the cost in resources required to construct the Generator
     * @param resourceProductionRate the rate at which the Generator produces resources per unit of time
     * @param numberConstructed      the number of units of this generator constructed at this time
     * @param product                the type of resource this generator produces
     */
    public Lumberjacks(String name, ArrayList<Resource> constructionCost, int resourceProductionRate, int numberConstructed, Resource product) {
        super("butcher", contructionCost, 50, 1, new Wood());
    }
}
