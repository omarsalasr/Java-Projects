/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: Enemy class which inherites from the NPC class. The additional data that this
*              class has is the type of decision maker. Enemies attack players or allies
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class Enemy extends NPC{
    
    private AIEnemy AIDecisionMaker;
    private boolean boss;

    // Constructor for the Enemy which calls the super constructor
    public Enemy(int id, String name, String desc, int strength, int health){
        super(id, name, desc, strength, health);
        boss = false;
        if(id < 0)
            boss = true;
        AIDecisionMaker = new AIEnemy();// New AI decision maker
    }

    // Decision maker implementation which calls the AIEnemy decision maker
    @Override
    public void makeMove(){
        move = AIDecisionMaker.getMove(this, current);
        if(move != null)// Invalid move, do nothing for the NPC
            move.execute();
    }

    public boolean isBoss(){
        return boss;
    }

}