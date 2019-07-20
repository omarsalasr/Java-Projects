/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: UI decision maker class used by Player which implements
*              the DecisionMaker interface use for returning a move given
*              by the user from keyboard input
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class UI implements DecisionMaker{
    
    // getMove method implementation for Player
    @Override
    public Move getMove(Character character, Place place){
        String artifact = "";
        String direction = "";
        String defenderName = "";
        IO io = character.getIO();
        String input = io.getLine().trim();// User input command
        if(input.length() == 0)// Check if they input an empty command
            return null;
        Scanner lineSc = new Scanner(input);
        String move = lineSc.next();
        // Process the move
        if(move.equalsIgnoreCase("Exit") || move.equalsIgnoreCase("Quit"))
            return new MoveExit();
        else if(move.equalsIgnoreCase("Look"))
            return new MoveLook(place);
        else if(move.equalsIgnoreCase("Inve") || move.equalsIgnoreCase("Inventory"))
            return new MoveInventory(character);
        else if(move.equalsIgnoreCase("Get")){
            if(lineSc.hasNext())
                artifact = lineSc.nextLine().trim();
            return new MoveGet(artifact, character, place);
        }else if(move.equalsIgnoreCase("Drop")){
            if(lineSc.hasNext())
                artifact = lineSc.nextLine().trim();
            return new MoveDrop(artifact, character, place);
        }else if(move.equalsIgnoreCase("Use")){
            if(lineSc.hasNext())
                artifact = lineSc.nextLine().trim();
            return new MoveUse(artifact, character, place);
        }else if(move.equalsIgnoreCase("Go")){
            if(lineSc.hasNext())
                direction = lineSc.nextLine().trim();
            return new MoveDirection(direction, character, place);
        }else if(move.equalsIgnoreCase("Equip")){
            if(lineSc.hasNext())
                artifact = lineSc.nextLine().trim();
            return new MoveEquip(artifact, character);
        }else if(move.equalsIgnoreCase("Attack")){
            if(lineSc.hasNext())
                defenderName = lineSc.nextLine().trim();
            Character defender = Character.getCharacterByName(defenderName);
            if(defender == null){
                character.getIO().display("Please specify the name of the enemy you are trying to attack\n");
                return null;
            }
            return new MoveAttack(character, defender, place);
        }
        return null;// Invalid command was given
    }
}