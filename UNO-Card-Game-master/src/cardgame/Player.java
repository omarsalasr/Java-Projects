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
public class Player {//player class
    private String name;//string name
    ArrayList<Card> cards = new ArrayList<>();
    //array list to hold each players hand
    public String getName(){//method that returns the name
        return name;
    }
    
    public Player(String n){//constructor that sets the name
        name = n;
    }
    
    public void drawCard(Card crd){//method that draws the top card of the deck
        cards.add(crd);
    }
    
    public boolean hasPlayable(){//method to check if they have a playable card
        if(playable.isEmpty())
            return false;
        return true;
    }
    
    public void updatePlayable(Card top){//method to update the playable list according to the top card
        playable.clear();//clears the list
        for(int i = 0; i < cards.size(); i++)//loop that goes through each card in hand
            if(cards.get(i).getColor().equalsIgnoreCase("W") || cards.get(i).getColor().equalsIgnoreCase(top.getColor()) || cards.get(i).getNumber() == top.getNumber())
                playable.add(cards.get(i));//adds that card to playable     
    }
    //array list to hold the players playable cards
    ArrayList<Card> playable = new ArrayList<>();
}