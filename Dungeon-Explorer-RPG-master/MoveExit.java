/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveDirection subclass that is used for a "exit" or "quit" command where
*              the game stops execution and the game is over
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveExit extends Move{

    // Constructor
    public MoveExit() { }

    //Execute method that exits the game with a message
    @Override
    public void execute(){
        System.out.println("Exiting Game!");
        System.exit(0);
    }

}