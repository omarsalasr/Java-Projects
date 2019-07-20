/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;

/**
 *
 * @author R2D2
 */
public class AI extends Player{//ai class which extends player class
    private int interval=0;//interval for the uno part

    public AI(String n) {//constructor for super class
        super(n);
    }
    public int chooseCard(){//finds the playable card
         if(playable.size() == 1)//checks the size
            return searchCard(0);//returns the only playable card
        else{
            int temp = (int) Math.random()*playable.size();//chooses a random one
            return searchCard(temp);//returns that cards position
        }
    }
    
    public int searchCard(int index){//mehtod to seach for the card in their hand
        for(int i = 0; i < cards.size(); ++i){//loop to go through the cards
            if(cards.get(i).toString().equalsIgnoreCase(playable.get(index).toString())){//checks the index
                return i;//returns the index
            }
        }
        return index;
    }
    
    public void setInterval(int x){//sets the AI's interval
        interval = x;
    }
    
    public int getInterval(){//returns the interval
        return interval;
    }
    
}
