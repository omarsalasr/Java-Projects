/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: AI decision maker class used by NPC which implements the 
*              DecisionMaker interface used for returning a random move
*              that the NPC will be taking
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class AI implements DecisionMaker{
    
    // getMove method implementation for a NPC
    @Override
    public Move getMove(Character character, Place place){
        String artifact = "";
        String direction = "";
        String move = "";
        Random rand = new Random();// Choose a random number for the possible commands
        int decision = rand.nextInt(4);
        if(decision == 0)// Choose the command to be executed
            move = "Get";
        else if(decision == 1)
            move = "Drop";
        else if(decision == 2)
            move = "Go";
        else if(decision == 3)
            move = "Use";
        artifact = character.getRandArtifact();
        // Process the move
        if(move.equalsIgnoreCase("Go")){
            direction = Direction.getRandDirection();
            return new MoveDirection(direction, character, place);
        }else if(artifact.length() == 0)
            return null;
        else if(move.equalsIgnoreCase("Get"))
            return new MoveGet(artifact, character, place);
        else if(move.equalsIgnoreCase("Drop"))
            return new MoveDrop(artifact, character, place);
        else if(move.equalsIgnoreCase("Use"))
            return new MoveUse(artifact, character, place);
        return null;
    }

}