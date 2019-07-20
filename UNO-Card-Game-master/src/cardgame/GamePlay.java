/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author R2D2
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class GamePlay{//gameplay class
 
    public void config(){//configure the game settings
        clockWise = false;
        deck.clear();//clears deck
        fillDeck();//fills the deck
        players.clear();//clers the players
        players.add(new Player("AP Java P7"));//makes new players
        players.add(new AI("Bob"));
        players.add(new AI("Rob"));
        players.add(new AI("Mob"));
        pTurn = 0;//resets pturn
        dealCards();//deals the cards
        while(deck.get(0).getColor().equalsIgnoreCase("W") || deck.get(0).getNumber() == 10//while loop to check if the top card is wild
                || deck.get(0).getNumber() == 11 || deck.get(0).getNumber() == 12)
            deck.remove(0);
        top = deck.get(0);//makes the top card equal the top card of the deck
        players.get(0).updatePlayable(top);//updates the first players cards
        deck.remove(0);//removes the top card in the deck
    }
    
    public void dealCards(){//method to deal the cards
        for(int i = 0; i < 5 ; ++i)//loop that deals 5 cards
            for(Player p: players){//goes through each player
                p.drawCard(deck.get(0));//draw a card
                deck.remove(0);//removes that card from the deck
            }
    }
    
    public void fillDeck(){//mehtod to fill the deck
        for(int i = 0;i < 250; i++)//loop that puts 250 cards
            deck.add(new Card());
    }
    
    private boolean clockWise = false;//boolean to check which way its going
    
    public void processCard(int pos){//method to process the player move
        top = players.get(0).cards.get(pos);//makes the top card equal their move
        checkSpecialMoves(pos);//checks for special cards
        players.get(0).cards.remove(pos);//removes that card from their hand
        players.get(0).updatePlayable(top);//updates their playable accoring to the new top
        if(skip){//checks if a skip was placed
            changeTurn();//changes the turn
            skip = false;//resets skip
        }
        changeTurn();//changes the turn
        if(draw){//checks if they placed a draw card
            draw(2);//draw 2 cards
            if(top.getNumber() == 13)//checks if its a wild + 4
                    draw(2);//draw 2 cards
        }
        updatePlayable();//updates the playbale
        draw = false;//makes draw false
    }
    
    public void checkSpecialMoves(int pos){//method to check special moves
        if(players.get(pTurn).cards.get(pos).getNumber() == 10)//checks for a skip
                skip = true;
        else if(players.get(pTurn).cards.get(pos).getNumber() == 11)//checks for reverse
                changeDirection();
        else if(players.get(pTurn).cards.get(pos).getNumber() == 12)//checks for a draw
                draw = true;
        else if(players.get(pTurn).cards.get(pos).getColor().equalsIgnoreCase("w")){//checks for a wild
            if(players.get(pTurn).cards.get(pos).getNumber() == 1)//checks for a wild + 4
                draw = true;
            chooseColor();
        }
    }
    
    public void chooseColor(){//method that makes the user chose a color
        String[] colors = {"R","G","Y","B"};//array of the 4 colors
        String color = "";//string for the color
        if(pTurn == 0){//checks if its the first players turn
            do{//do while loop to get a valid input 
               color = JOptionPane.showInputDialog("What color do you want to change it to?");
            }while(!color.equalsIgnoreCase("green") && !color.equalsIgnoreCase("blue") && !color.equalsIgnoreCase("yellow") && !color.equalsIgnoreCase("red"));
        }else{
            color = colors[(int) (Math.random()*4)];//choses a random color for the bots
        }
        switch(color){//switch statement to convert the color string to a single char
            case"blue":
                color = "B";
                break;
            case"red":
                color = "R";
                break;
            case"yellow":
                color = "Y";
                break;
            case"green":
                color = "G";
                break;
        }
        top = new Card(13,color);//makes top a new card with a custom number and color
    }
    
    private boolean draw;//boolean draw
    
    public void changeDirection(){//mehtod to change the direction 
        clockWise = !clockWise;//converts the direction
        if(pTurn == 0 && !clockWise)//checks for out of bounds
            pTurn = 4;
    }
    
    private boolean skip = false;//boolean skip
    
    public void botMove(){//method to process the bots move
        if(players.get(pTurn).hasPlayable()){//checks if they have a playable card
            int pos = ((AI)players.get(pTurn)).chooseCard();//gets thet position of the card
            top = players.get(pTurn).cards.get(pos);//makes the top equal that card
            checkSpecialMoves(pos);//checks for special cards
            players.get(pTurn).cards.remove(pos);//removes that card form their hand
        }else
            draw(1);//they draw 1 card
        if(skip){//checks for skip
            changeTurn();
            skip = false;
        }
        changeTurn();//changes turns
        if(draw){//checks for draw
            draw(2);//draw 2 cards
            if(top.getNumber() == 13)
                draw(2);//draw 2 cards  
        }
        updatePlayable();//updates the playable
        draw = false;
    }
    
    public void updatePlayable(){//method that updates playable for all 4 players
        for(int i = 0; i < 4; ++i)
            players.get(i).updatePlayable(top);
    }
    
    public void draw(int num){//method to draw a cards
        for(int i = 0; i < num; ++i)
            players.get(pTurn).cards.add(deck.remove(0));
    }
    
    public boolean checkCard(int pos){//method that checks the cards
        if(players.get(0).playable.contains(players.get(0).cards.get(pos)))//checks the cards
            return true;
        return false;
    }
    
    public Card getCard(int player, int index){//returns the card
        return players.get(player).cards.get(index);
    }
    
    public int getHandSize(int player){//gets the hand size of the player
        return players.get(player).cards.size();
    }
    
    public Card getTop(){//returns the top card
        return top;
    }
    
    Card top = new Card();//top card
    
    public void changeTurn(){//method that changes the player turn
        if(clockWise)//checks which direction its going at
            pTurn --;//decrements
        else
            pTurn ++;//increments
        if(pTurn >= 4)//checks for limit
            pTurn = 0;
        else if(pTurn <= -1)
            pTurn = 3;
    }    
    
    ArrayList<Card> deck = new ArrayList();//array list for the deck
    ArrayList<Player> players = new ArrayList();//array list for the players
    private int pTurn;//int for the player turn
    
    public int getTurn(){//returns the player turn
        return pTurn;
    }
    
    public int getIn(int num){//returns the ai intervals
        return ((AI)players.get(num)).getInterval();
    }
    
}
