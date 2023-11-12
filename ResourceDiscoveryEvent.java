import java.util.ArrayList;

public class ResourceDiscoveryEvent extends Event{

    public static String descriptionOfEvent = "You've discovered an abundance of resources, you have gained 10% of your resources";
    /**
     * Creates a new Event with the given name
     *
     */
    public ResourceDiscoveryEvent() {
        super("resource discovery event", descriptionOfEvent);
    }

    @Override
    public void triggerEvent(TextManagementGame textManagementGame){
        super.triggerEvent(textManagementGame);
        System.out.println(descriptionOfEvent);
        ArrayList<Resource> resourceList = textManagementGame.getResources();
        for(Resource resource : resourceList){
            resource.add((int)(resource.getQuantity() * .1));
        }
        System.out.println("New Resource Amount:");
        textManagementGame.viewResources();
    }

}
