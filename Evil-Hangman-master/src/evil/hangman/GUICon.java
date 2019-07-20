/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evil.hangman;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author R2D2
 */
public class GUICon implements Initializable {
    
    GamePlay game = new GamePlay();//instance of gameplay
    List<String> picts = new ArrayList<>();//array of strings for the pictures
    @FXML
    private ListView topList;//list view for the top scores
    @FXML
    private Label lblWord,lblWL, lblName, lblScore, lblGuesses;//labels
    @FXML
    private ImageView imgHang;//image
    @FXML
    private Button btnGuess, btnGuessW;//buttons for guessing
    @FXML
    private MenuItem btnStart;//menu button
    private boolean started;//started match
    @FXML
    private void handleStart(ActionEvent event)
    {
        boolean isGood = true;//boolean to check if the user wants to restart
        if(!game.checkEnd() && started){//condition to check for restart
            int selection = JOptionPane.showConfirmDialog(null, "The game is in progress, do you wish to restart?");//confirm dialog to confirm restart
            if(selection == JOptionPane.NO_OPTION || selection == JOptionPane.CANCEL_OPTION || selection == JOptionPane.CLOSED_OPTION)//choices for no restart
                isGood = false;//doesnt want to restart
        }
        if(isGood){//condition if they want to restart
            picts.clear();//clears pic array                                  
            picts.add("resources/hangman0.png");//adds
            picts.add("resources/hangman1.png");//the 
            picts.add("resources/hangman2.png");//pics
            picts.add("resources/hangman3.png");//for
            picts.add("resources/hangman4.png");//the
            picts.add("resources/hangman5.png");//hang
            picts.add("resources/hangman6.png");//man
            picts.add("resources/hangman7.png");
            picts.add("resources/hangman8.png");
            picts.add("resources/hangman9.png");
            game.config((int) (2+Math.random() * 9));//calls the config method passing a random length for a word
            lblWord.setText(game.getBWord());//sets the word to underscores
            btnGuess.setDisable(false);//modifies
            btnGuess.setVisible(true);//the 
            btnGuessW.setVisible(false);//buttons
            btnGuessW.setDisable(true);//for guessing
            btnStart.setText("Restart Game");//changes the name of the button
            lblGuesses.setText("");//puts nothing into the guesses label
            do//do while to make sure they dont input null
                lblName.setText("Player: " + JOptionPane.showInputDialog("What is your name?"));//gets the player name
            while(lblName.getText() == null);
            lblScore.setText("Score: " + game.getScore());//displays their score
            imgHang.setImage(null);//resets the image
            lblWL.setText(null);//resets the win/loss label
            started = true;//started once is true
            displayScores();//displays the top scores
        }
    }
    
    private ObservableList displayList = FXCollections.observableArrayList();
    //list to display the scores
    
    public void displayScores(){//method to display the top scores    
        displayList.clear();//clears the list
        ArrayList<String> temp = new ArrayList(game.loadScores());//copies the list from the loaded score from a txt file
        for(int i = 0; i < 5; i++)//copies the scores onto the displaylist
           displayList.add(temp.get(i));
        topList.setItems(displayList);//display the score onto a list view     
    }
    
    @FXML
    private void handleGuessWord(ActionEvent event){//method to handle the word guessing
        String guess;//string to get the word
        guess = JOptionPane.showInputDialog("What word do you want to guess?");//gets the word
        guess = guess.toUpperCase();//converts it to upper case
        game.processWGuess(guess);//passes the word to the gameplay class
        updateScreen();//update screen method
    }
    
    @FXML
    private void handleGuess(ActionEvent event)//method to handle the letter guess
    { 
        String guess;//string to store the guess
        do{//loop to check that they input one character at a time, no spaces, no numbers, or no previous guess
            guess = JOptionPane.showInputDialog("What letter do you want to guess?");//gets the guess
            try{
                int temp = Integer.parseInt(guess);//converts to a number
                guess += " ";//if no error making it an integer, the size increases by 1 making the while happen again
            }catch(Exception e){}//if there is an error, its a string
        }while(guess.length()>1 || guess.contains(" ") || !game.validGuess(guess));
        lblGuesses.setText(lblGuesses.getText() + guess + "  ");//displays the guesses
        game.setGuessL(guess);//adds to the total guesses
        game.processLGuess();//method to process the guess
        updateScreen();//updates the screen
    }
    
    public void updateScreen(){//method to update the screen
        imgHang.setImage(new Image("resources/hangman" + (game.getNumL()-1) + ".png"));//sets the image according num loses
        lblScore.setText("Score: " + game.getScore());//updates the score
        if(game.checkChosen()){//checks if a word has been chosen
            lblWord.setText(game.getBWord());//sets the label to the built word with underscores and letters
            btnGuessW.setVisible(true);//modifies the 
            btnGuessW.setDisable(false);//buttons
        }
        String temp = game.checkWinner();//gets the message for end game and checks if it ended
        if(game.checkEnd()){//checks if the game ended
            game.addScore(lblName.getText());//adds the player and score to the txt file
            displayScores();//displays the top scores to check if they got top 5
            btnGuess.setDisable(true);//modifies the buttons
            btnGuess.setVisible(false);
            btnGuessW.setVisible(false);
            btnGuessW.setDisable(true);
            if(game.checkHS()){//checks if he got the hich score
                imgHang.setImage(new Image("resources/Congrats.gif"));//congrats image
                lblWL.setText("High Score!");//modifies the label
                lblScore.setText("Score: " + game.getScore());//updates the score
            }else if(game.getNumL() == 10){//checks if he got 10 mistakes
                imgHang.setImage(new Image("resources/Loser.gif"));//loser image
                lblWL.setText("Try Again");//modifies the label
            }else{
                lblWL.setText(temp);//gets the original message
                lblScore.setText("Score: " + game.getScore());
            }
            lblWord.setText(game.getBWord());//displays the word chosen win or lose
        }
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}