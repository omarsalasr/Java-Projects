/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveDirection subclass that is used for a "use" command where
*              the given character uses a key in the current places
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveUse extends Move{

    private Character charac;
    private Place place;
    private String artifact;

    // Constructor that takes in an artifact, character, and place
    public MoveUse(String artifact, Character c, Place pl) {
        this.artifact = artifact;
        charac = c;
        place = pl;
    }

    // Execute method that uses the given key in the current place
    @Override
    public void execute(){
        IO io = charac.getIO();
        // Temporarilly remove the key from the inventory
        Artifact useable = charac.removeArtifactByName(artifact);
        if(useable == null)// Artifact doesn't exist
            return;
        if(io != null)
            io.display(charac.getName() + " is trying to use " + artifact + "\n");
        useable.use(charac, place);// Use the key
        charac.addArtifact(useable);// Give the key back to the character
    }

}