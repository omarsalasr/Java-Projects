/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: 
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public class IO{
    public static final int TEXT = 0;
    public static final int GUI_OMAR = 1;
    public static final int GUI_DOM = 2;
    public static final int GUI_RAHUL = 3;
    private UserInterface implementor;
    private String name;
    private int id;

    public IO(String name, int id){
        this.id = id;
        this.name = name;
        implementor = new TextInterface(this.name, this);
    }

    public IO(String name, int gui, int id){
        this.id = id;
        this.name = name;
        if(gui == TEXT)
            implementor = new TextInterface(this.name, this);
        else if(gui == GUI_OMAR)
            implementor = new GUIOmar(this.name, this, id);
        else if(gui == GUI_DOM)
            implementor = new GUIDom(this.name, this);
        else if(gui == GUI_RAHUL)
            implementor = new GUIRahul(this.name, this);
    }    

    public void display(String str){
        implementor.display(str);
    }

    public String getLine(){
        return implementor.getLine();
    }

    public void selectInterface(int num){
        if(num < 0 || num > 3){
            System.out.println("Invalid number, please choose a number between 0 and 3");
        }
        else if(num == TEXT){
            implementor = new TextInterface("Text Interface", this);
        }
        else if(num == GUI_OMAR){
            implementor = new GUIOmar("Omar", this, id);
        }
        else if(num == GUI_DOM){
            implementor = new GUIDom(name, this);
        }
        else{
            implementor = new GUIRahul(name, this);
        }
    }
}