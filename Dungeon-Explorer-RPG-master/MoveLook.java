/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveDirection subclass that is used for a "look" command where
*              the game displays the place information for the current place of a character
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveLook extends Move{

    private Place pl;

    // Constructor that takes in a place
    public MoveLook(Place place) {
        pl = place;
    }

    // Execute method that displays the place information
    @Override
    public void execute(){
        pl.display(null);
    }

}