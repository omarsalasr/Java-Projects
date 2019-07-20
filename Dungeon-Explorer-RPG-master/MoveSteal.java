/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveSteal subclass that is used for a "steal" command used
*              by the Playful NPC only
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveSteal extends Move{

    private Character thief, victim;
    private String artifact;

    // Constructor that takes in an artifact, character, and character
    public MoveSteal(String artifact, Character thief, Character victim) {
        this.artifact = artifact;
        this.thief = thief;
        this.victim = victim;
    }

    // Execute method that removes an item from the player's inventory and adds it to the playful NPC inventory
    @Override
    public void execute(){
        Artifact item = victim.removeArtifactByName(artifact);// Get the character artifact to be removed
        if(item != null){// Check if the item exists
            thief.addArtifact(item);
            thief.getIO().display(thief.getName() + " stole " + artifact + " from " + victim.getName() + "!\n");
        }
    }

}