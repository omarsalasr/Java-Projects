/*
 *   Team Members: Omar Salas-Rodriguez (osalas3)
 *                 Dominykas Sipelis    (dsipel2)
 *                 Rahul Chatterjee     (rchatt6)
 *
 * Name: Dominykas Sipelis
 * netID: dsipel2
 * Description: Room which heals characters to full health
 * functions:
 *      - heal(Character c)
 */
import java.util.Scanner;

class HealRoom extends Place{

    //scanner constructor
    HealRoom(Scanner sc){
        super(sc);
    }

    //constructor with args
    HealRoom(int ID, String name, String desc){
        super(ID, name, desc);
    }

    //return heal amount
    void heal(Character c){
        if(c != null){
            c.heal();
        }
    }
}