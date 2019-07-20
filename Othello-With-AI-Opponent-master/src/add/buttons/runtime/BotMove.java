/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add.buttons.runtime;

/**
 *
 * @author R2D2
 */
public class BotMove {
   private int x,y; 
   
   public BotMove(int row, int col){
       x = row;
       y = col;
   }
   
   public int getX(){
       return x;
   }
   
   public int getY(){
       return y;
   }
    
}
