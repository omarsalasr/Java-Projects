/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: Playful class which inherites from the NPC class. The additional data that this
*              class has is the type of decision maker. Playful NPC's have the ability 
*              to steal from other players
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class Playful extends NPC{
    
    private AIPlayful AIDecisionMaker;

    // Constructor for the Playful NPC which calls the super constructor
    public Playful(int id, String name, String desc, int strength, int health){
        super(id, name, desc, strength, health);
        AIDecisionMaker = new AIPlayful();// New AIPlayful decision maker
    }

    // Decision maker implementation which calls the AI RNG decision maker
    @Override
    public void makeMove(){
        move = AIDecisionMaker.getMove(this, current);
        if(move != null)// Invalid move, do nothing for the NPC
            move.execute();
    }

}