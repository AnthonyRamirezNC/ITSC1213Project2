import java.util.ArrayList;

public class DestroyGeneratorEvent extends Event{
    public static String eventDescription = "There has been a disease outbreak at one of your generators, it must be shut down";

    /**
     * Creates a new Event with the given name
     *
     */
    public DestroyGeneratorEvent() {
        super("destroy generator", eventDescription);
    }

    @Override
    public void triggerEvent(TextManagementGame textManagementGame){
        super.triggerEvent(textManagementGame);
        ArrayList<Generator> generators = textManagementGame.getGenerators();
        if(generators.size() > 0){
            System.out.println(eventDescription);
            //destroy random generator
            int randomNum = (int)(Math.random() * generators.size());
            Generator removedGenerator = generators.get(randomNum);
            generators.remove(randomNum);
            System.out.println("Your " + removedGenerator.getName() + " has been quarantined, it is no longer operational");
        } else System.out.println("There was a disease outbreak, however there are no generators so no quarantine needed");
    }
}

