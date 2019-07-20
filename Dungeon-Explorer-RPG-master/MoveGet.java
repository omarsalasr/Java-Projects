/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveDirection subclass that is used for a "get" command where
*              the given character picks up an available item from the ground
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveGet extends Move{

    private Character charac;
    private Place place;
    private String artifact;

    //Constructor that takes in an artifact, character, and current place
    public MoveGet(String artifact, Character c, Place pl) {
        this.artifact = artifact;
        charac = c;
        place = pl;
    }

    // Execute method removes an artifact from the current place and adds it to the player's inventory
    @Override
    public void execute(){
        IO io = charac.getIO();
        Artifact item = place.getArtifact(artifact, charac.getIO());// Get artifact to be added to inventory
        if(item != null){
            if(io != null)
                io.display(charac.getName() + " aquired " + artifact + "\n");
            charac.addArtifact(item);
        }
    }

}