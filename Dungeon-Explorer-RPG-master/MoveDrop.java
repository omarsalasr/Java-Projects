/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveDirection subclass that is used for a "drop" command where
*              the given character drops a given artifact from their inventory
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveDrop extends Move{

    private Character charac;
    private Place place;
    private String artifact;

    // Constructor that takes in an artifact, character, and place
    public MoveDrop(String artifact, Character c, Place pl) {
        this.artifact = artifact;
        charac = c;
        place = pl;
    }

    // Execute method that removes an item from the player's inventory and adds it to the current place
    @Override
    public void execute(){
        IO io = charac.getIO();
        Artifact item = charac.removeArtifactByName(artifact);// Get the character artifact to be removed
        if(item != null){// Check if the item exists
            place.addArtifact(item);
            if(io != null)
                io.display(charac.getName() + " dropped " + artifact + "\n");
        }
    }

}