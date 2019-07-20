/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveDirection subclass that is used for a "go" command where
*              the given character moves to a valid destination
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveDirection extends Move{

    private Character charac;
    private Place place;
    private String direction;

    // Constructor that takes in a direction, character, and current place
    public MoveDirection(String direction, Character c, Place pl) {
        this.direction = direction;
        charac = c;
        place = pl;
    }

    // Execute method follows the direction and modifies the collection in each class for the character and place
    @Override
    public void execute(){
        IO io = charac.getIO();
        Place toPlace = place.followDirection(direction, charac.getIO());// Get the destination
        if(toPlace != null){
            if(toPlace != place){
                // Modify collections
                place.removeCharacter(charac);
                toPlace.addCharacter(charac);
                charac.updateCurrent(toPlace);
                return;
            }else if(toPlace == place)
                return;
        }
        if(io != null){
            charac.getIO().display("Please try a valid direction...\n");
        }
    }

}