/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: Ally class which inherites from the NPC class. The additional data that this
*              class has is the type of decision maker. Allies have an RNG decision maker
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class Ally extends NPC{
    
    private AIAlly AIDecisionMaker;
    private boolean following;

    // Constructor for the NPC which calls the super constructor
    public Ally(int id, String name, String desc, int strength, int health){
        super(id, name, desc, strength, health);
        AIDecisionMaker = new AIAlly();// New AI decision maker
    }

    // Decision maker implementation which calls the AIAlly RNG decision maker
    @Override
    public void makeMove(){
        move = AIDecisionMaker.getMove(this, current);
        if(move != null)// Invalid move, do nothing for the NPC
            move.execute();
    }

}