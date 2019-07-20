/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveDirection subclass that is used for a "inven" or "inventory" command where
*              the game displays the current player's inventory
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveInventory extends Move{

    private Character charac;

    // Constructor that takes in a characters
    public MoveInventory(Character c) {
        charac = c;
    }

    // Execute method that displays the character's inventory
    @Override
    public void execute(){
        charac.display();
    }

}