import java.util.ArrayList;

public class MisplacedResourcesEvent extends Event {
    public static String descriptionOfEvent = "Your resources have been misplaced, you have lost 10% of your resources";

    /**
     * Creates a new Event with the given name
     */
    public MisplacedResourcesEvent() {
        super("misplaced event", descriptionOfEvent);
    }

    @Override
    public void triggerEvent(TextManagementGame textManagementGame){
        super.triggerEvent(textManagementGame);
        System.out.println(descriptionOfEvent);
        ArrayList<Resource> resourceList = textManagementGame.getResources();
        for(Resource resource : resourceList){
            resource.consume((int)(resource.getQuantity() * .1));
        }
        System.out.println("New Resource Amount:");
        textManagementGame.viewResources();
    }
}

