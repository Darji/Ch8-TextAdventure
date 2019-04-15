import java.util.ArrayList;
/**
 * Player class to create players to play the game
 * A player has a name, age and an array of objects where he can carry multiple objects to use in the game 
 * @author  Akash Darji
 * @version Apr 2 2019
 */
public class Player {
    private String name;
    private int age;
    private ArrayList<String> objects;
    private int health;
    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.health = 100;
        objects = new ArrayList<>();
    }// end of Player
    
    public void addObject(String obj){
        objects.add(obj);
    }// end of addObject
    public String carries(){
        String res = "Carries: ";
        if(objects.isEmpty()){
            res += "nothing";
        }// end of if
        else{
            for (String obj : objects) {
                res += obj+", ";
            }// end for
        }// end of else
        return res;
    }// end of carries
    
    public void decreaseHealthBy(int by){
        this.health -= by;
        if(health < 0)
            health = 0;
    }// end of decreaseHealthBy
    public int getHealth(){
        return health;
    }// end of getHealth
    public String getName() {
        return name;
    }// end of getName

    public void setName(String name) {
        this.name = name;
    }// end of setName

    public int getAge() {
        return age;
    }// end of getAge

    public void setAge(int age) {
        this.age = age;
    }// end of setAge 

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }// end of toString
    
}// end of Class Player
