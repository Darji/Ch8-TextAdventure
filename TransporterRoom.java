
/**
 * @author  Akash Darji
 * @version Apr 22, 2019
 */
public class TransporterRoom extends Room
{
    private Scenario scene;

    /**
     * Create a room 
     */
    public TransporterRoom(String description, Scenario scene)
    {
        super(description);
        this.scene = scene;
    }// end of TransporterRoom

    /**
     * Describe the exits.
     */
    public String getExitString()
    {
        return "You feel like crap. Something is not right.\n" +
               "You cannot see any of the exits...";
    }// end of getExitString

    /**
     * If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        return scene.getRandomRoom();
    }// end of getExit

}// end of public class TransporterRoom
