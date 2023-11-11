import java.util.ArrayList;

public class StrikeEvent extends Event{
    public static String eventDescription = "Your civilians feel as though they are not being treated properly, they are on strike";

    /**
     * Creates a new Event with the given name
     *
     */
    public StrikeEvent() {
        super("strike event", eventDescription);
    }

    @Override
    public void triggerEvent(TextManagementGame textManagementGame){
        super.triggerEvent(textManagementGame);
        ArrayList<Generator> generators = textManagementGame.getGenerators();
        if(generators.size() > 0){
            System.out.println(eventDescription);
            //activate strike effects so no resources are collected from generators
            TextManagementGame.ableToCollect = false;
        } else {
            System.out.println(eventDescription);
            System.out.println("There are no generators, so the strike dissipates quickly");
        }
    }
}
