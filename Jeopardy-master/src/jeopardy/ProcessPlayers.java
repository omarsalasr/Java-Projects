/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardy;

/**
 *
 * @author rcortez
 */
public class ProcessPlayers 
{
    private String name;
    private int points, id;
    private boolean ableBuzz;
    
    public ProcessPlayers(String n, int i){
       name = n;
       points = 0;
       id = i;
       ableBuzz = true;
    }
    
    public String getName(){
        return name;
    }
    
   public void addPoints(int value){
       points += value;
   }
   
   public void subPoints(int value){
       points -= value;
   }
    
    public int getPoints(){
        return points;
    }
    
    public int getId(){
        return id;
    }
    
    public void resPoints(){
        points = 0;
    }
    
    public void cantBuzz(){
        ableBuzz = false;
    }
    
    public void canBuzz(){
        ableBuzz = true;
    }
    
    public boolean getBuzz(){
        return ableBuzz;
    }
}
