/**
 * Item class to represent an item in the room
 * it has item description and item's weight
 * Item is placed in a room, each room can contain multiple items
 * so an array of items is in the room class so that a room can create multiple items
 * @author  Akash Darji
 * @version Apr 2 2019
 */
public class Item {
    
    private String desc;
    private double weight;

    public Item(String desc, double weight) {
        this.desc = desc;
        this.weight = weight;
    }// end of Item

    public String getDesc() {
        return desc;
    }// end of getDics

    public void setDesc(String desc) {
        this.desc = desc;
    }// end of setDesc

    public double getWeight() {
        return weight;
    }// end of getWeight

    public void setWeight(double weight) {
        this.weight = weight;
    }// end of setWeight

    @Override
    public String toString() {
        return "Desc: " + desc + ", Weight: " + weight;
    }// end of toString
    
    
}// end of Item 
