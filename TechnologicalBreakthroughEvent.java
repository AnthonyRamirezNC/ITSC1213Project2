import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TechnologicalBreakthroughEvent extends Event{

    public static String descriptionOfEvent = "Research advancements allow you to upgrade a generator for free or at a reduced cost";

    /**
     * Creates a new Event with the given name
     *
     */
    public TechnologicalBreakthroughEvent() {
        super("Technological breakthrough event", descriptionOfEvent);
    }

    @Override
    public void triggerEvent(TextManagementGame textManagementGame){
        if(textManagementGame.getGenerators().size() > 0){
            super.triggerEvent(textManagementGame);
            System.out.println(descriptionOfEvent);
            int randomGeneratorNum = ((int) Math.random() * textManagementGame.getGenerators().size());
            ArrayList<Generator> generators = textManagementGame.getGenerators();
            Generator randomGenerator = generators.get(randomGeneratorNum);
            randomGenerator.upgrade(new ArrayList<Resource>(Arrays.asList(new Gold(999999), new Stone(999999), new Wood(999999))));
            if(randomGenerator.currentLevel <3) {
                System.out.println(randomGenerator.getName() + " upgraded to level " + randomGenerator.getCurrentLevel());
            } else System.out.println("random generator already at max level");
        } else System.out.println(descriptionOfEvent + "\nNo generators present to upgrade");
    }
}
