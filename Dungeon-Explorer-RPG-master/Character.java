/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: Character class that represents a single character in the game. This is the parent class
*              to the Player and NPC classes. Each character has unique attributes like:
*              - ID, name, description
*              - Current place to keep track where the character is located
*              - Collection of all the characters in the game
*              - Player inventory
*              - Move object used for the decision making
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class Character{
    private int ID;
    private boolean isDead;
    private String name, description;
    private static HashMap<Integer, Character> characters = new HashMap<>();
    protected Place current;
    private ArrayList<Artifact> inventory;
    protected Move move;
    protected int health, strength, maxHealth;
    protected IO io;

    // Constructor for the class that takes in a scanner and a version number
    public Character(Scanner sc, int version){
        int placeID = 0;
        int id = 0;
        int numPlayers = 0;
        int nCharacters, nDescLines;
        String name, desc, line, type;
        Scanner lineSc;
        if(version == 51){// Version 4.0 has a section of characters in the data file
            nCharacters = Game.getNumIterations(sc, "CHARACTERS", false);
            for(int i = 0; i < nCharacters; i++){// Create all the characters in the data file
                line = CleanLineScanner.getCleanLine(sc);
                lineSc = new Scanner(line);
                type = lineSc.next();
                line = CleanLineScanner.getCleanLine(sc);
                lineSc = new Scanner(line);
                placeID = lineSc.nextInt();
                line = CleanLineScanner.getCleanLine(sc);
                lineSc = new Scanner(line);
                id = lineSc.nextInt();
                name = lineSc.nextLine().trim();
                line = CleanLineScanner.getCleanLine(sc);
                lineSc = new Scanner(line);
                strength = lineSc.nextInt();
                health = lineSc.nextInt();
                line = CleanLineScanner.getCleanLine(sc);
                lineSc = new Scanner(line);
                nDescLines = lineSc.nextInt();
                desc = "";
                for(int j = 0; j < nDescLines; j++){
                    if(j + 1 == nDescLines)
                        desc += CleanLineScanner.getCleanLine(sc);
                    else
                        desc += CleanLineScanner.getCleanLine(sc) + "\n";
                }
                if(characters.get(id) != null)// Check if the character already exists
                    System.out.println("Error: Character already exists.");
                else{
                    // Check what type of character it is, Player or NPC
                    if(type.equalsIgnoreCase("PLAYER")){
                        characters.put(id, new Player(id, name, desc, strength, health));
                        numPlayers++;
                    }else if(type.equalsIgnoreCase("NPC"))
                        characters.put(id, new NPC(id, name, desc, strength, health));
                    else if(type.equalsIgnoreCase("ENEMY")){
                        NPC temp = new NPC(id, name, desc, strength, health);
                        characters.put(id, temp);
                        Place.getPlaceByID(placeID).addBoss(this);
                    }
                    else if(type.equalsIgnoreCase("ALLY"))
                        characters.put(id, new NPC(id, name, desc, strength, health));
                    else if(type.equalsIgnoreCase("PLAYFUL"))
                        characters.put(id, new NPC(id, name, desc, strength, health));
                    // Check if there character is going to a random location
                    if(placeID == 0)
                        placeID = Place.getRandPlaceID();
                    // Modify the current place, add character to place, and add character to the game collection
                    characters.get(id).current = Place.getPlaceByID(placeID);
                    Place.getPlaceByID(placeID).addCharacter(characters.get(id));
                    Game.addCharacter(characters.get(id));
                }
            }
        }else{
            numPlayers++;
            // Add at least 1 character
            characters.put(0, new Player(0, "Player 1", "The lone wolf.", 10, 100));
            characters.get(0).current = Place.getPlaceByID(Place.getStartingID());
            Place.getPlaceByID(Place.getStartingID()).addCharacter(characters.get(0));
            Game.addCharacter(characters.get(0));
        }
        // Add any additional characters specified by the second argument when executing
        if(numPlayers < GameTester.getNumPlayers()){
            int location = 0;
            for(int i = numPlayers + 1; i <= GameTester.getNumPlayers(); i++){
                location = Place.getRandPlaceID();
                characters.put(99+i, new Player(99+i, "Player " + i, "Nothing special", 10, 100));
                characters.get(99+i).current = Place.getPlaceByID(location);
                Place.getPlaceByID(location).addCharacter(characters.get(99+i));
                Game.addCharacter(characters.get(99+i));
            }
        }
    }

    // Constructor for the attributes used by the subclasses
    public Character(int ID, String name, String desc, int strength, int health){
        if(ID < 0)
            ID *= -1;
        this.ID = ID;
        this.name = name;
        this.description = desc;
        this.strength = strength;
        this.health = health;
        maxHealth = health;
        current = null;
        isDead = false;
        inventory = new ArrayList<Artifact>();
        
    }

    // Return a character by a given ID
    public static Character getCharacterByID(int id) { return characters.get(id); }
    
    // Method which the subclasses will implement
    public void makeMove() { }

    public void display(){
        io.display(name + "'s Inventory:\n");
        for(int i = 0; i < inventory.size();i++)
            inventory.get(i).display(io);
        io.display("\n");
    }

    public void displayName() { io.display("- " + name + "\n"); }

    public String getName() { return name; }

    // Update the current place utilized by the decision maker
    public void updateCurrent(Place newPlace){
        if(newPlace != null)// Check if the new place exists
            current = newPlace;
    }

    public void print(){
        System.out.println("ID: " + ID);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.print("Inventory:\n");
        for(int i = 0; i < inventory.size();i++)
            inventory.get(i).print();
        System.out.println("\n");
    }

    // Check if two characters are equal by ID
    private boolean equals(Character charac){
        if(this.ID == charac.ID)
            return true;
        return false;
    }

    // Add artiffact to a characters inventory
    public void addArtifact(Artifact item) {
        if(item == null)
            return;
        inventory.add(item);
    }

    // Remove an artifact from the player's inventory by a given name
    public Artifact removeArtifactByName(String name){
        int i;
        for(i = 0; i < inventory.size();i++)//get the index of the artifact that will be removed
            if(inventory.get(i).match(name))
                break;
        if(i == inventory.size()){
            io.display("Artifact isn't in your inventory. Try again...\n");
            return null;
        }
        return inventory.remove(i);// Return the removed artifact
    }

    // Method added by Omar
    // Player or enemy may attack others
    public int attack(Character defender){
        io.display(name + " attacked " + defender.getName() + "\n");
        return defender.defend(strength);
    }

    // Method added by Omar
    // Player or enemy may defend against attacks
    // Return the hit points to be taken away
    public int defend(int attackerStrength){
        Random r = new Random();
        int random = r.nextInt(2);
        if(random == 0){ // Attack landed
            // io.display(" and dealt " + attackerStrength + " damage!\n");
            health -= attackerStrength;
            if(health <= 0)
                return 1;
        }
            // io.display(", but missed!\n");
        return 0;
    }

    // Method added by Omar
    public boolean isCharDead(){
        return isDead;
    }

    // Returns random artifact name from a characters inventory
    public String getRandArtifact(){
        if(inventory.size() == 0){
            return "";
        }
        Random rand = new Random();
        int item = rand.nextInt(inventory.size());
        return inventory.get(item).name();
    }

    public void heal(){
        if(health < maxHealth)
            health = maxHealth;
    }

    public void equipArtifact(weapons item){
        strength += item.getDamage();
    }

    // Return a character by a given name
    public static Character getCharacterByName(String name) { 
        for (int key : characters.keySet()){
            if(characters.get(key).name.equalsIgnoreCase(name))
                return characters.get(key);
        }
        return null;
    }

    public IO getIO(){
        return io;
    }

    public ArrayList<Artifact> getInventory() { return inventory; }

    public int getHealth() { return health; }

    public int getMaxhealth() { return maxHealth; }

    public int getStrength() { return strength; }
}