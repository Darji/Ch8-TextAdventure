import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Akash Darji
 * @version Apr 2 2019
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<>();
    }// end of room

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }// end of setExit

    public void addItem(Item item){
        items.add(item);
    }// end of end of add Item
    public String getItems(){
        String result = "Items: ";
        for (int a = 0; a < items.size(); a++) {
            result += items.get(a).getDesc()+", ";
        }// end of for
        return result;
    }// end of getItems
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }// end of getShortDiscp

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n"+getItems()+"\n" + getExitString();
    }// end of getLongDiscp
    public String getAllItems()
    {
        String result = "Items In This Room:\n";
        for (int a = 0; a < items.size(); a++) {
            result += items.get(a)+"\n";
        }// end of for
        return result;
    }// end of getAllItems
    

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }// end of for
        return returnString;
    }// end of getExitString

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }// end of getExit
}// end of class room

