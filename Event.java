/**
 * The Event class represents a generic randomly occuring event in the game.
 * Events have a name
 */
public class Event implements Score{
    private String name;

    private String descriptionOfEvent;

    public static int eventCount = 0;

    /**
     * Creates a new Event with the given name
     *
     * @param name the name of the event
     */
    public Event(String name, String descriptionOfEvent) {
        this.name = name;
        this.descriptionOfEvent = descriptionOfEvent;
    }

    /**
     * Gets the name of the event.
     *
     * @return the name of the event
     */
    public String getName() {
        return name;
    }

    public String getDescriptionOfEvent() {
        return descriptionOfEvent;
    }

    public void triggerEvent(TextManagementGame textManagementGame){
        eventCount++;
    }

    public int scoreImpact(){
        return eventCount * 200;
    }

}