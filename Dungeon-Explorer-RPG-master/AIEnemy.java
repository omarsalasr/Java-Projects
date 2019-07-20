/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: AIEnemy decision maker class used by ENEMY which implements the 
*              DecisionMaker interface used for returning an attack object
*              where the enemy attacks a random player in the current place
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class AIEnemy implements DecisionMaker{
    
    // getMove method implementation for a Enemy
    @Override
    public Move getMove(Character attacker, Place place){
        Character defender = place.getRandomPlayer();
        if(defender != null){ // There exists an attackable player
            return new MoveAttack(attacker, defender, place);
        }
        return null;
    }
}