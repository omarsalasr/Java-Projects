/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: The GameTester class is the main class that reads in the GDF file for parsing.
*              It makes sure that the GDF exists and that it can be opened.
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class GameTester{

    private static int numPlayers = 1;

    public static void main(String[] args){
        String fileName = "";
        Game gameplay = null;
        boolean textArg = false;
        int counter = 0;
        if(args.length != 0){// Checks if there are any arguments
            for(String s: args){
                if(!textArg) // Extract file name from args
                    fileName = fileName + s + " ";
                else if(textArg){// Extract second argument
                    if(counter == 1){// Check if there is more than 2 args
                        numPlayers = 0;
                        break;
                    }
                    try{
                        numPlayers = Integer.parseInt(s);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    counter++;
                }
                if(s.contains("txt") || s.contains("gdf"))
                    textArg = true;
                
            }
            fileName = fileName.trim();
        }else{// Ask the user for the filename if no arguments were given
            System.out.print("Please input GDF: ");
            fileName = KeyboardScanner.getKeyboardScanner().nextLine();
            System.out.println("");
        }
        try{
            File input;
            while(true){
                input = new File(fileName);// Open the file
                if(input.exists())// Start reading in data if the file exists
                    break;
                // File doesn't exists and ask the user for a different file name
                System.out.print("The file you entered seems to not exist, please input a different file name: ");
                fileName = KeyboardScanner.getKeyboardScanner().nextLine();
                System.out.println("");
            }
            System.out.println("Name: Omar Salas-Rodriguez\tNetID: osalas3");
            System.out.println("Name: Dominykas Sipelis   \tNetID: dsipel2");
            System.out.println("Name: Rahul Chatterjee    \tNetID: rchatt6");
            Scanner fScn = new Scanner(input);// Scanner to process the file data
            gameplay = new Game(fScn);// Pass in scanner to game to parse the game information
        }catch(Exception e){
            System.out.println(e);
            System.exit(0);
        }
    }

    // Return the amount of players according to the second argument. Default is 1
    public static int getNumPlayers(){
        return numPlayers;
    }

}