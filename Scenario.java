import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author  Akash Darji 
 * @version Apr 22, 2019
 */
public class Scenario
{
    private List<Room> rooms;     // List of all rooms in the game
    private Room startRoom;
    private Random random;

    /**
     * Create a complete scenario (with all rooms).
     */
    public Scenario()
    {
        Room outside, theatre, pub, lab, office, transporter;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        transporter = new TransporterRoom("in a strange dark void", this);
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);
        theatre.setExit("north", transporter);

        transporter.setExit("south", theatre);
        
        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        startRoom = outside;  // start game outside
        
        rooms = new ArrayList<>();
        rooms.add(outside);
        rooms.add(theatre);
        rooms.add(pub);
        rooms.add(lab);
        rooms.add(office);
        rooms.add(transporter);
        
        random = new Random();
    }// end of Public Senario

    /**
     * Return the room where players start the game.
     */
    public Room getStartRoom()
    {
        return startRoom;
    }// end of getStartRoom
    
    /**
     * Return a random room in this game.
     */
    public Room getRandomRoom()
    {
        return rooms.get(random.nextInt(rooms.size()));
    }// end of getRandomRoom
}// end of public class
