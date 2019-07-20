/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: Player class which inherites from the Character class. The additional data
*              that this class has is the type of decision maker. Players have the ability
*              to read in user input from the keyboard and process the command
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class Player extends Character{

    private UI userDecisionMaker;

    // Constructor for the Player which calls the super constructor 
    public Player(int id, String name, String desc, int strength, int health){
        super(id, name, desc, strength, health);
        userDecisionMaker = new UI();// New UI decision maker
        io = new IO(name, id);
    }

    // Decision maker implementation whic calls the UI user input decision maker
    @Override
    public void makeMove(){
        current.display(io);
        move = userDecisionMaker.getMove(this, current);
        if(move == null){// Invalid move, display error for a Player
            io.display("Invalid Command\n");
            return;
        }
        move.execute();
    }
}