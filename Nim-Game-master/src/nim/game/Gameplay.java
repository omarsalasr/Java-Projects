/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nim.game;

import javax.swing.JOptionPane;

/**
 *
 * @author salasrodriguez1089
 */
public class Gameplay {
    
    public Gameplay(){
        
    }
    
    public void startGame(int marbles, String difficulty){
        System.out.println("Blocks: " + marbles);
        int turn = (int) (Math.random() * 2);
        takeBlocks(marbles, difficulty, turn); 
    
    }
    
    public void checkWinner(int turn){
        if(turn == 0)
            System.out.println("You beat the Computer");
       else
            System.out.println("You lost to a Computer!!!");
    }
    
    public void takeBlocks(int marbles, String difficulty, int turn){
        boolean valid = false;
        int take = 0;
        while(marbles > 0){            
            turn = changeTurn(turn);
            if(turn == 1){
            while(!valid){
                take = Integer.parseInt(JOptionPane.showInputDialog("How many are you goona take?"));
                valid = take > 0 && take <= marbles/2;
                if(marbles == 1){
                    take = 1;
                    valid = true;
                }
            }
            marbles -= take;
            System.out.println("You took: " + take);
            System.out.println("Blocks: " + marbles);
            }
            else if(turn == 0){
            marbles = bot(marbles, difficulty);
            }
            valid = false;
        }
        checkWinner(turn);
    }
    
    public int bot(int marbles, String difficulty){
        int take = 0;
        if(marbles > 0){
            if(difficulty.equalsIgnoreCase("easy")){
                take = (int) (1 + Math.random() * (marbles/2));
                marbles -= take;
                System.out.println("Bot takes out: " + take);
            }
            else{
                int magicNum = (int) (Math.pow(2, (int) (Math.log(marbles+1)/Math.log(2))))-1;
                if(marbles == magicNum){
                    take = (int) (1 + Math.random() * (marbles/2));
                    marbles -= take;
                    System.out.println("Bot takes out: " + take);
                }
                else{
                System.out.println("Bot takes out: " + (marbles - magicNum));
                marbles -= (marbles - magicNum); 
                }
            }
        }
        System.out.println("Blocks: " + marbles);
        return marbles;
    }
    
    public int changeTurn(int turn){
        turn++;
        if (turn%2 == 1){
            System.out.println("It's your turn");
            return 1;
        }
        else    
            return 0;
    }
    
    
//    private int numBlocks,turn;
    
}
