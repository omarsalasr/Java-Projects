/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: DecisionMaker interface implemented by the AI and UI classes
*              which contain the unique implementation for the getMove method
*
*/

public interface DecisionMaker{

    public Move getMove(Character character, Place place);

}