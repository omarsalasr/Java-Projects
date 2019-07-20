/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: AIAlly decision maker class used by ALLY which implements the 
*              DecisionMaker interface used for returning a random move
*              that the ALLY can make. This AI differs from AI because an ALLY
*              has the ability to attack and help a other players
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class AIAlly implements DecisionMaker{
    
   // getMove method implementation for a Ally NPC
   @Override
   public Move getMove(Character character, Place place){
       String artifact = "";
       String direction = "";
       String move = "";
       Random rand = new Random();// Choose a random number for the possible commands
       int decision = rand.nextInt(5);
       if(decision == 0)// Choose the command to be executed
           move = "Get";
       else if(decision == 1)
           move = "Drop";
       else if(decision == 2)
           move = "Go";
       else if(decision == 3)
           move = "Use";
       else if(decision == 4)
           move = "Attack";
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
       else if(move.equalsIgnoreCase("Attack")){
           Character enemy = place.getRandomEnemy();
           if(enemy != null){//no emeny in the current room
               return new MoveAttack(character, enemy, place);
           }
       }
       return null;
   }

}   