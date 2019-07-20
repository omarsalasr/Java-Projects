/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rock.papers.scissors;

import java.util.ArrayList;

/**
 *
 * @author salasrodriguez1089
 */
public class Gameplay {
    
    private String diff, aIMove;//strings to store the difficulty and the comptuer move
    private int wins, losses, ties;//intigers to store wins, losses, and ties
    
    public Gameplay(){//constructor for the class
        for(int i = 0;i<1000;i++){//loop to run 100 times
            int temp = (int) (Math.random()*3);//picks a random number from 0-2
            pTMoves.add(getMove(temp));//populates the array list with moves that were generated
        }
        for(int i = pTMoves.size()-6;i<pTMoves.size();i++){//loop to run 5 times
            p5Moves.add(pTMoves.get(i));//populates the last 5 moves of the user from the last 5 moves from the total moves
        }
        
    }
    
    public void setDiff(String difficulty){//method to set the difficulty
        this.diff = difficulty;//sets the instance field = to the parameter
    }
    
    public void playGame(String pMove){//method to start the game
        if(diff.equals("novice")){//condition to check the diff
            aIMove = getEasyMove();//sets the computer move to the easy move accesor
        }
        else{
            aIMove = getHardMove();//sets the computer move to the hard move accesor
        }
        checkWinner(pMove,aIMove);//checks who the winner is
        pTMoves.add(pMove);//adds the move to the total moves array list
        p5Moves.remove(0);//removes the first move from the last 5 moves array list
        p5Moves.add(pMove);//adds the new move to the end of the last 5 moves array list
    }
    
    public String getCounter(String counter){//method to get the counter to a move
        if(counter.equals("R"))//condition to check for the move wanting to counter
            return "P";//returns the counter to rock
        else if(counter.equals("P"))
            return "S";//returns the counter to paper
        else
            return "R";//returns the counter to scissors
    }
    
    public String getMove(int temp){//method to get the move according a number value
        if(temp == 0)//condition to check which number it is
            return "R";//returns rock
        else if(temp == 1)
            return "P";//returns paper
        else
            return "S";//returns scissors
    }
    
    public String getHardMove(){//method to get the hard mode move
        for(int i = 0;i < pTMoves.size()-6;i++){//loop to happen as many times as needed according to the total moves array
            int temp = i;//temp variable to store i
            boolean noMatch = false;//boolean to assume there has been a match
            for(int j = 0;j < 5;j++){//loop to compare the 5 moves from the 2 arrays
                if(!pTMoves.get(i).equals(p5Moves.get(j)))//condition to check if there isnt a match
                   noMatch = true;//no match is true
                i++;//increment i so it gets compared respectively
            }
            if(!noMatch)//condition to check if there has been 5 consecutive matches
                return getCounter(pTMoves.get(i+1));//returns the counter of the next move that the player will choose
            i = temp;//sets i = to temp
        }        
        return getEasyMove();//returns the easymode move if there is no match
    }
    
    public String getEasyMove(){//method to generate a random move
        int temp = (int) (Math.random()*3);//gets a random number between 0-2
        return getMove(temp);//returns the move
    }
    
    
    
    public void checkWinner(String pMove, String aIMove){//method to check who the wunner is
        if((pMove.equals("R") && aIMove.equals("R")) || (pMove.equals("P") && aIMove.equals("P")) || (pMove.equals("S") && aIMove.equals("S")))
            //condition to check when the user has tied with the computer
            ties ++;
            //adds 1 to total ties
        
        else if((pMove.equals("R") && aIMove.equals("S")) || (pMove.equals("P") && aIMove.equals("R")) || (pMove.equals("S") && aIMove.equals("P")))
            //condition to check when the user has beaten the computer
            wins ++;
            //adds 1 to the total wins
        
        else
            //default for when the user losses against the computer
            losses ++;
            //adds 1 to the total losses
        
            
    }
    
    public String getAIMove(){//accessor to return the computer move
        return aIMove;//returns the computer move
    }
    
    public int getWins(){//accessor to return the total wins
        return wins;//returns the total wins
    }
    
    public int getTies(){//accessor to return the total ties
        return ties;//returns the total ties
    }
    
    public int getLosses(){//accessor to return the total losses
        return losses;//returns the losses
    }
    ArrayList<String> pTMoves = new ArrayList<>();//array list to store the total moves chosenby the user
    ArrayList<String> p5Moves = new ArrayList<>();//array list to store the last 5 moves that the user chose
    
    
}
