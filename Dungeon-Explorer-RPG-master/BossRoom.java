/*
 *   Team Members: Omar Salas-Rodriguez (osalas3)
 *                 Dominykas Sipelis    (dsipel2)
 *                 Rahul Chatterjee     (rchatt6)
 *
 * Name: Dominykas Sipelis
 * netID: dsipel2
 * Description: Room with a boss which cannot be left until boss is defeated
 * functions:
 *      - addBoss(Character c)
 *      - hasBoss()
 *      - followDirection(String dir)
 */
import java.util.Scanner;

class BossRoom extends Place{
    private Character boss;

    //Scanner constructor
    BossRoom(Scanner sc){
        super(sc);
    }

    //constructor with args
    BossRoom(int ID, String name, String desc){
        super(ID, name, desc);
    }

    //put a boss into the room
    void addBoss(Character c){
        if(boss == null){
            boss = c;
        }
    }

    //return true if boss present and alive
    boolean hasBoss(){
        if(boss != null){
            if(!boss.isCharDead()){
                return true;
            }
        }
        return false;
    }

    //only allow player movement if boss is dead
    Place followDirection(String dir, IO io){
        if(!hasBoss()){
            return super.followDirection(dir, io);
        }
        if(io != null)
            io.display("You must defeat the boss first!");
        return this;
    }
}