/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveEquip subclass that is used for a "equip" command where
*              the given character can equip a weapon to increase their strength
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveEquip extends Move{

    private Character charac;
    private String artifact;

    //Constructor that takes in an artifact, character
    public MoveEquip(String artifact, Character c) {
        this.artifact = artifact;
        charac = c;
    }

    // Execute method removes an artifact from the player inventory to equip it
    @Override
    public void execute(){
        Artifact item = charac.removeArtifactByName(artifact);// Get artifact to be equiped
        if(item != null){
            if(item instanceof weapons){
                charac.getIO().display(charac.getName() + " equiped " + artifact + "\n");
                charac.equipArtifact(((weapons)item));
                return;
            }
            charac.addArtifact(item);
        }
    }

}