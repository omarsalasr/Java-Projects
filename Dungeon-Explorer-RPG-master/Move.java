/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: Move class that implements the command design patter where there are
*              multiple commands that inherite from this class and implement the execute
*              method depending on the type of Move subclass
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;

public abstract class Move{

    // Method that will be overriden by each subclass
    public abstract void execute();

}