/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: MoveAttack subclass that is used for an "attack" command where
*              the given player gives an enemy's name in the same room
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class MoveAttack extends Move{

    private Character attacker, defender;
    private Place place;

    // Constructor that takes in a character, character, and place
    public MoveAttack(Character attacker, Character defender, Place place) {
        this.attacker = attacker;
        this.defender = defender;
        this.place = place;
    }

    // Execute method that a character attacks another and checks if the damage was enough to kill them
    @Override
    public void execute(){
        int dead = attacker.attack(defender);
        if(dead == 1){
            defender.getIO().display(defender.getName() + " has died.\n");
            place.removeCharacter(defender);
            Game.removeCharacter(defender);
        }
    }

}