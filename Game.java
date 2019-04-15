
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Akash Darji
 * @version Apr 2 2019
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private ArrayList<String> backs = new ArrayList<>();
    
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }// end of Game

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, auditorium, biolab, 
                cafe, parking, washroom, kitchen, garden, foodstreet, library, courtyard, trap;
        
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        theater.addItem(new Item("Projector",23));
        theater.addItem(new Item("Desk",75));
        
        pub = new Room("in the campus pub");
        pub.addItem(new Item("Chair",65));
        
        lab = new Room("in a computing lab");
        lab.addItem(new Item("PC",75));
        lab.addItem(new Item("Chair",65));
        lab.addItem(new Item("Printer",25));
        
        office = new Room("in the computing admin office");
        office.addItem(new Item("Table",10));
        office.addItem(new Item("Pen",2));
        office.addItem(new Item("Pad",4));
        office.addItem(new Item("Lamp",6));
        
        auditorium = new Room("in the auditorium");
        auditorium.addItem(new Item("Seat",15));
        auditorium.addItem(new Item("Projector",25));
        auditorium.addItem(new Item("Speaker",35));
        auditorium.addItem(new Item("Mic",15));
        
        biolab = new Room("in the biology lab");
        biolab.addItem(new Item("Flask",15));
        biolab.addItem(new Item("Beaker",20));
        biolab.addItem(new Item("Test Tube",10));
        
        cafe = new Room("in the cafe");
        cafe.addItem(new Item("Desk",15));
        cafe.addItem(new Item("Cake",35));
        cafe.addItem(new Item("Biscuit",10));
        
        parking = new Room("in the car parking");
        parking.addItem(new Item("Car",170));
        parking.addItem(new Item("Bike",100));
        
        washroom = new Room("in the washroom");
        washroom.addItem(new Item("Mirror",25));
        
        foodstreet = new Room("in the food street");
        foodstreet.addItem(new Item("Fruit",24));
        foodstreet.addItem(new Item("Burger",10));
        foodstreet.addItem(new Item("Pizza",12));
        
        courtyard = new Room("in the courtyard");
        courtyard.addItem(new Item("Dust bin",25));
        
        garden = new Room("in the garden");
        garden.addItem(new Item("Grass Cutter",35));
        
        kitchen = new Room("in the kitchen");
        kitchen.addItem(new Item("Knife",5));
        kitchen.addItem(new Item("Basket",20));
        kitchen.addItem(new Item("Oven",60));
        kitchen.addItem(new Item("Burner",17));
        
        library = new Room("in the library");
        library.addItem(new Item("Book",20));
        library.addItem(new Item("Notes",20));
        library.addItem(new Item("Guide",30));
        
        trap = new Room("you're trapped");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("north", lab);
        outside.setExit("west", pub);
        outside.setExit("south", parking);

        theater.setExit("west", outside);
        theater.setExit("east", auditorium);

        pub.setExit("east", outside);

        lab.setExit("south", outside);
        lab.setExit("east", office);
        lab.setExit("west", biolab);
        lab.setExit("north", foodstreet);

        office.setExit("west", lab);
        office.setExit("north", garden);
        
        auditorium.setExit("west", theater);
        auditorium.setExit("south", courtyard);
        
        biolab.setExit("east", lab);
        biolab.setExit("south", cafe);
        biolab.setExit("west", library);
        
        cafe.setExit("north", biolab);
        cafe.setExit("south", washroom);
        cafe.setExit("east", trap);
        
        parking.setExit("north", outside);
        parking.setExit("east", washroom);
        parking.setExit("west", foodstreet);
        
        washroom.setExit("west", parking);
        washroom.setExit("north", cafe);
        
        foodstreet.setExit("east", parking);
        foodstreet.setExit("south", lab);
        foodstreet.setExit("west", kitchen);
        
        courtyard.setExit("north", auditorium);
        courtyard.setExit("east", garden);
        
        garden.setExit("west",courtyard);
        garden.setExit("east",kitchen);
        garden.setExit("south",office);
        garden.setExit("north",library);
        
        library.setExit("south", garden);
        library.setExit("east", biolab);
        library.setExit("west", trap);
        
        kitchen.setExit("west", garden);
        kitchen.setExit("east", foodstreet);
        
        //set up a player
        player = new Player("Krito",21);
        player.addObject("Sword");
        player.addObject("Gun");
        player.addObject("Energy Drink");

        currentRoom = outside;  // start game outside
    }// end of create rooms

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {
        TimerTask task = new TimerTask(){
            public void run(){
                System.out.println("Time's Up\nGAME OVER");
                System.exit(0);
            }// end of run
        };
        printWelcome();
        
        // enter the main command loop. here we repeatedly read commands and execute them until the game is over
        Timer timer = new Timer();
        timer.schedule(task,6*10000);
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }// end of while
        System.out.println("Thank you for playing.  Good bye.");
        timer.cancel();
    }// end of play

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println("Player's Information: "+player+"\n"+player.carries());
        System.out.println(currentRoom.getLongDescription());
    }// end of printWelcome

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case BACK:
                getBackCommand(command);
                break;
                
            case EAT:
                System.out.println("You must be hungry. Go eat something!");
                break;
                
            case LOOK:
                System.out.println(currentRoom.getAllItems());
                break;
        }// end of switch
        return wantToQuit;
    }// end of processcommand

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }// end of printHelp

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }// end of if

        String direction = command.getSecondWord();

        // try to leave current room
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }// end of if
        else {
            backs.add(direction);
            System.out.println("Added "+direction);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if(currentRoom.getShortDescription().equalsIgnoreCase("you're trapped")){
                System.out.println("You cannot continue the game!");
                System.exit(0);
            }// end of if
        }// end of else
    }// end of goRoom

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }// end of if
        else {
            return true;  // signal that we want to quit
        }// end of else
    }// end of quit

    private void getBackCommand(Command command) {
        boolean onePosition = false;
        int indexes = 0;
        
        if(command.hasSecondWord()) {
            String direction = "";
            onePosition = true;
            direction = command.getSecondWord();
            indexes = Integer.parseInt(direction);
        }// end of if
        else{
            indexes = 1;
        }// end of else
        String prevCommand = "";
        for (int a = 1; a <= indexes; a++) {
            if(backs.size() == 0){
                prevCommand = "";
            }// end of if
            else{
                prevCommand = backs.get((backs.size())-a);
                backs.remove((backs.size())-a);
            }// end of else
            System.out.println("Executing: "+prevCommand);
            if(prevCommand.equals("")){
                System.out.println("Can't move back");
            }// end of if
            else if(prevCommand.equals("south")){
                Room nextRoom = currentRoom.getExit("north");
                if (nextRoom == null) {
                    System.out.println("There is no door!");
                }// end of if
                else {
                    currentRoom = nextRoom;
                    System.out.println(currentRoom.getLongDescription());
                }// end of else if
            }// end of else
            else if(prevCommand.equals("east")){
                Room nextRoom = currentRoom.getExit("west");
                if (nextRoom == null) {
                    System.out.println("There is no door!");
                }// end of if
                else {
                    currentRoom = nextRoom;
                    System.out.println(currentRoom.getLongDescription());
                    //backs.remove((backs.size()-1)-a);
                }// end of else
            }// end of else if
            else if(prevCommand.equals("west")){
                Room nextRoom = currentRoom.getExit("east");
                if (nextRoom == null) {
                    System.out.println("There is no door!");
                }// end of if
                else {
                    currentRoom = nextRoom;
                    System.out.println(currentRoom.getLongDescription());
                    //backs.remove((backs.size()-1)-a);
                }// end of else
            }// end of else if
            else if(prevCommand.equals("north")){
                Room nextRoom = currentRoom.getExit("south");
                if (nextRoom == null) {
                    System.out.println("There is no door!");
                }// end of if
                else {
                    currentRoom = nextRoom;
                    System.out.println(currentRoom.getLongDescription());
                    //backs.remove((backs.size()-1)-a);  
                }// end of else
            }// end of else if
        }// end of for
    }// end of getbackCommand
}// end of class game
