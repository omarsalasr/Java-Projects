/*
 *   Team Members: Omar Salas-Rodriguez (osalas3)
 *                 Dominykas Sipelis    (dsipel2)
 *                 Rahul Chatterjee     (rchatt6)
 *
 * Name: Dominykas Sipelis
 * netID: dsipel2
 * Description: dark room which needs to be lit up to look
 * functions:
 *      - light()
 *      - darken()
 *      - useWand(Wand a)
 *      - display()
 */
import java.util.Scanner;

class DarkRoom extends Place{
    boolean lit;

    //Scanner constructor
    DarkRoom(Scanner sc){
        super(sc);
        lit = false;
    }

    //constructor with args
    DarkRoom(int ID, String name, String desc){
        super(ID, name, desc);
        lit = false;
    }

    //light the room
    void light(){
        lit = true;
    }

    //darken room
    void darken(){
        lit = false;
    }

    void useWand(wand a){
        if(a.isLight()){
            light();
        }
    }

    //display only if lit
    void display(IO io){
        if(lit == false){
            io.display("It is to dark in here+\n");
        }
        else{
            super.display(io);
        }
    }
}