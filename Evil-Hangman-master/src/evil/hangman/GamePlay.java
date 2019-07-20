/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evil.hangman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author R2D2
 */
public class GamePlay 
{
    private int length, score, numLoses = 0;//integers for word length and score
    private String word, guessL = "", builtWord = "";//strings for word and guesses
    private boolean wordChosen = false, gEnd;//boolean to check if a word has been chosen
    ArrayList<String> possibleWords = new ArrayList();//list of possible words
    ArrayList<String> tempWords = new ArrayList();//temp list of possible words
    ArrayList<String> scoreList = new ArrayList();//array that holds the score list
    
    public void config(int len){//method that configures the match
        score = 0;//resets the score
        length = len;//gets the random length
        builtWord = "";//resets the built word
        guessL = "";//resets the letters guessed
        wordChosen = false;//ressets the boolean
        word = null;//makes the word null
        gEnd = false;//game end is false
        numLoses = 0;//resets the number loses
        for(int i = 0; i < length; ++i)
            builtWord += "_ ";//adds an underscore to the word     
    }
    
    public String getBWord(){//returns the built word
        return builtWord;
    }

    public boolean checkChosen(){//returns a boolean
        return wordChosen;//if a word has been chosen
    }
    
    public int getNumL(){//returns the number of loses
        return numLoses;
    }
    
    public int getScore(){//returns the score
        return score;
    }
    
    public void setGuessL(String guess){//method to add to guesses
        guessL += guess;//adds the new guess
        guessL = guessL.toUpperCase();//makes them uppercase
    }
    
    public boolean validGuess(String guess){//method to check for calid guess
        guess = guess.toUpperCase();//makes the guess uppercase
        if(guessL.length() == 0)//checks if it has something
            return true; 
        for(int i = 0; i < guessL.length();i++)//goes through the guesses
            if(guessL.contains(guess))//checks if he input a guess already guessed
                return false;
        return true;
    }
    
    public void processWGuess(String guess){//method to process the word guessed  
        if(!guess.equalsIgnoreCase(word)){//condition to check if he didnt get it right
            score -= (6*length);//updates the score
            numLoses++;//increments to the total loses
            return;
        }
        buildWord(word);//builds the word
        score *= length;//updates the score
    }
    
    public void processLGuess(){//method to process the letter guess
        Dictionary dict = new Dictionary ();//instantiates dictionary
        tempWords.clear();//clears the temp array
        tempWords.addAll(dict.getArrayNoMatch(guessL, length));//adds the words possible
        boolean empty = false;//boolean to check if the array is empty
        if(!tempWords.isEmpty()){//checks if its not empty
            possibleWords.clear();//clears the possible words array
            possibleWords.addAll(tempWords);//adds the elements
        }else
            empty = true;//the array is empty
        chooseWord(empty);//chooses a word
        buildWord(guessL);//builds the word for display
    }
    
    public void chooseWord(boolean empty){//method to choose a word
        if(!wordChosen){//checks if the word hasent been chosen
            if(possibleWords.size() == 1){//condition to check for the last word left
                word = possibleWords.get(0);//word is equal to the array word         
                wordChosen = true;//a word has been chosen
            }else if(empty){//condition to see if the array is empty
                int temp = (int)(Math.random()*possibleWords.size());//picks a random element from the ones left
                word = possibleWords.get(temp);//gets the random element
                wordChosen = true;//a word has been chosen
            }   
        }
    }
    
    public void buildWord(String guess){//method to build the chosen word
        String temp = builtWord;//stores the unmodified word into a temp string
        if(wordChosen){//condition to check if a word has been chosen
            builtWord = "";//built word with _ or letters
            boolean match = false;//boolean to check if there is a match
            for(int i = 0; i < word.length(); ++i){//loop to go through the word
                for(int j = 0; j < guess.length(); j++)//loop to go through the letters guessed
                    if(word.charAt(i) == guess.charAt(j)){//condition to check if a letter matches
                        builtWord += word.charAt(i) + " ";//adds the letter to the built word
                        match = true;//match is true
                        break;
                    }
                if(!match)//condition to check for no match
                    builtWord += "_ ";//adds an underscore in the word
                match = false;//match reset
            }
        }
        if(temp.equalsIgnoreCase(builtWord)){//condition to check if the word was modified
            numLoses ++;//adds to num loses
            score -= (5*length);//updates the score
        }
    }
    
    public boolean checkEnd(){//returns if the game is over
        return gEnd;
    }
    
    public boolean checkHS(){//returns a boolean if there is a high score
        if(score == Integer.parseInt(scoreList.get(0).substring((scoreList.get(0).indexOf(" ") + 1), scoreList.get(0).length())))
            return true;
        return false;
    }
    
    public String checkWinner(){//returns a string to display if they lost or won
        if(!searchUS()){//condition to check if the built word has any under scores
            score *= -1;//makes their score positive
            gEnd = true; //makes the end game true
            buildWord(word);//builds the actual word
            return "You win!";//returns string
        }
        if(numLoses == 10){//checks if they lost
            gEnd = true;//end game is true
            buildWord(word);//builds the actual word
            return "You lose";//returns a string
        }
        return null;
        
    }
    
    public boolean searchUS(){//method to search the built word for any underscores
        for(int i = 0; i < builtWord.length(); ++i)//goes through the built word
            if(builtWord.charAt(i) == '_')//checks if there is an underscore
                return true;//returns true if there is 
        return false;//returns false if there isnt
    }
    
    public ArrayList loadScores(){//method to load the scores from a txt file
        scoreList.clear();//clears the array
        try{
            FileReader reader = new FileReader("src/resources/Scores.txt");//makes a filereader
            Scanner in = new Scanner(reader);//reads the file for input
            while(in.hasNext())//gets each line of the txt
                scoreList.add(in.nextLine());//adds the line into the score list
        }catch(FileNotFoundException ex){}//catches an error
        sortScores();//sorts the scores
        return scoreList;//returns the scorelist
    }
    
    public void addScore(String name){//method to add to the scores
        //adds the player and their score to the score list
        scoreList.add(name.substring((name.indexOf(" ") + 1), name.length()) + ": " + score);
        saveScores();//saves the scores to the txt file
    }
    
    public void saveScores(){//method to save the scores to the file
        String outFile = "src/resources/Scores.txt";//gest the file path
        try(PrintWriter out = new PrintWriter(outFile)) {//opens the file
            for(int i = 0; i < scoreList.size(); ++i)//goes through all the scores
                out.println(scoreList.get(i));//writes to the txt file
        }catch(FileNotFoundException ex){}
    }
    
    public void sortScores(){//method to sorts the scores in decending order
        for(int i = scoreList.size()-1; i > 0; --i)//loop through the list to get through all the elements
            for(int j = scoreList.size()-1; j > 0; --j){//loop through the list
                int firstVal = Integer.parseInt(scoreList.get(j).substring((scoreList.get(j).indexOf(":") + 2), scoreList.get(j).length()));
                //gets the first value to compare 
                int secVal = Integer.parseInt(scoreList.get(j-1).substring((scoreList.get(j-1).indexOf(":") + 2), scoreList.get(j-1).length()));
                //gets the second value to compare
                if(firstVal > secVal){//checks which value is bigger
                    //string temp to swap elemnts
                    String temp = scoreList.get(j-1);//swaps the values so
                    scoreList.set((j-1), scoreList.get(j));//the biggest value
                    scoreList.set(j, temp);//ends up at the bottom of the list
                }
            }
    }
    
}
