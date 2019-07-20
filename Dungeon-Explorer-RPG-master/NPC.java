/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: NPC class which inherites from the Character class. The additional data that this
*              class has is the type of decision maker. NPC's have an RNG decision maker
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class NPC extends Character{
    
    private AI AIDecisionMaker;

    // Constructor for the NPC which calls the super constructor
    public NPC(int id, String name, String desc, int strength, int health){
        super(id, name, desc, strength, health);
        AIDecisionMaker = new AI();// New AI decision maker
    }

    // Decision maker implementation which calls the AI RNG decision maker
    @Override
    public void makeMove(){
        move = AIDecisionMaker.getMove(this, current);
        if(move != null)// Invalid move, do nothing for the NPC
            move.execute();
    }

}