/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: The Game class is the main driver for the game since it's the class that calls the
*              move function for each character in the game, keeps track of all the characters in
*              the game, and also does an initial check if the GDF is correct
*              
*
*/
import java.io.*;
import java.util.*;

public class Game{

    private String gameName;
    private static ArrayList<Character> characters;
    private boolean playing;
    // private IO io;

    public Game(Scanner sc){// Scanner constructor to parse information
        gameName = "";
        playing = true;
        String line = CleanLineScanner.getCleanLine(sc);
        Scanner lineSc = new Scanner(line);
        String fileType = lineSc.next();
        // Check if the GDF is correct
        if(!fileType.equalsIgnoreCase("GDF")){
            System.out.println("Error: File format mismatch, must be of type \"GDF\"");
            System.exit(0);
        }
        // Make the version an integer for the other constructors
        int version = (int) (lineSc.nextDouble() * 10);
        if(version != 50 && version != 51){
            System.out.println("Error: File version mismatch, must be version 3.1 or 4.0");
            System.exit(0);
        }
        
        gameName = lineSc.nextLine().trim();
        // Add all the places to the game
        String type;
        int nPlaces = Game.getNumIterations(sc, "PLACES", true);
        for(int i = 0; i < nPlaces; i++){
            if(version == 50 || version == 51)
                type = CleanLineScanner.getCleanLine(sc);
            else
                type = "PLACE";
            if(type.equalsIgnoreCase("PLACE"))
                new Place(sc, version);
            else if(type.equalsIgnoreCase("HEALROOM"))
                new HealRoom(sc);
            else if(type.equalsIgnoreCase("BOSSROOM"))
                new BossRoom(sc);
            else if(type.equalsIgnoreCase("DARKROOM"))
                new DarkRoom(sc);
            else
                throw new RuntimeException("Invalid Place Type in gdf");
        }
        //add exit and nothing places
        new Place(0, "Nothing", "");
        new Place(1, "Exit", "");
        // Add all the directions to each place
        int nDirections = Game.getNumIterations(sc, "DIRECTIONS", true);
        for(int i = 0; i < nDirections; i++){
            new Direction(sc, version);
        }
        // Add all the characters to the game, character collection, and place
        characters = new ArrayList<Character>();
        new Character(sc, version);
        // Add all the artifacts to the places or characters
        int nArtifacts = Game.getNumIterations(sc, "ARTIFACTS", true);
        for(int i = 0; i < nArtifacts; i++){
            //Edited by Rahul Chatterjee
            if((i != nArtifacts) && (sc.hasNextLine()))
                new Artifact(sc, version);
        }

        System.out.println("** Welcome to " + gameName + "! **\n");
        try{
            play();
        }
        catch(NullPointerException e){
            e.printStackTrace();
            System.exit(2);
        }
    }

    // Adds a character to the collection of characters in the game
    public static void addCharacter(Character charac) { characters.add(charac); }

    // Removes a character from the collection of characters in the game
    public static void removeCharacter(Character charac) {
        for(int i = 0; i < characters.size(); i++){
            if(characters.get(i).getName().equalsIgnoreCase(charac.getName())){
                characters.remove(i);
                return;
            }
        }
    }

    // Main game method used for character move calling
    public void play(){
        boolean noPlayers = false;
        while(playing){
            noPlayers = true;
            for(int i = 0; i < characters.size(); i++){
                characters.get(i).makeMove();
                if(characters.get(i) instanceof Player)
                    noPlayers = false;
            }
            if(noPlayers){
                System.out.println("No more players are left, thank you for playing!");
                System.exit(0);
            }
        }
    }

    // Return the number of iterations for a given data field type and checks if it's supposed to be nonzero
    public static int getNumIterations(Scanner input, String type, boolean nonZero){
        String line = CleanLineScanner.getCleanLine(input);
        Scanner lineSc = new Scanner(line);
        String lineType = lineSc.next();
        if(!lineType.equalsIgnoreCase(type)){
            System.out.println("Error: Couldn't find \"" + type + "\" keyword.");
            System.exit(0);
        }
        int numIterations = lineSc.nextInt();
        if(nonZero){
            if(numIterations <= 0){
                System.out.println("Error: Number of \"" + type + "\" is invalid, must be greater than 0.");
                System.exit(0);
            }
        }else{
            if(numIterations < 0){
                System.out.println("Error: Number of \"" + type + "\" is invalid, must be nonzero.");
                System.exit(0);
            }
        }
        lineSc.close();
        return numIterations;
    }

}
