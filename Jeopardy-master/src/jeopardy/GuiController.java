/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardy;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import javafx.scene.control.MenuItem;

/**
 *
 * @author rcortez
 */
public class GuiController implements Initializable {
    
    
    private boolean canBuzz, dDob;//booleans for buzzing in and for dailyDouble
    private int pTurn, numInc, numPoints, dailyD, cPlayer;
    //integers for player turn, number of incorrect answers, number of points to add/sub, daily double id, and current player
    private int cQuestID, dDPoints;
    //integers for the current question id and daily double points
    private int tQuestions;//integer for the total questions
    ArrayList<ProcessPlayers> players = new ArrayList();//array of the players class
    ArrayList<ProcessQuestion> questions = new ArrayList();//array of the questions class
    ArrayList<ProcessQuestion> tempArray = new ArrayList();//temp array to store questions
    @FXML
    private Label lblName1, lblName2, lblName3, lblScore1, lblScore2, lblScore3;//labels to display names and scores
    @FXML
    private Label lblQuestion, lblMessages, lblKPIns;//labels for questions, messages, and key press instructions
    @FXML
    private Label lblSW, lblSchool, lblMusic, lblCS, lblGG;//labels for the categories
    @FXML
    private Button btnC1L1,btnC2L1,btnC3L1,btnC4L1,btnC5L1,//all the question buttons
                   btnC1L2,btnC2L2,btnC3L2,btnC4L2,btnC5L2,
                   btnC1L3,btnC2L3,btnC3L3,btnC4L3,btnC5L3,
                   btnC1L4,btnC2L4,btnC3L4,btnC4L4,btnC5L4,
                   btnC1L5,btnC2L5,btnC3L5,btnC4L5,btnC5L5;
    
    @FXML
    private MenuItem menuStart, menuReset;//menu bar buttons
    
    private ArrayList<Button> btns = new ArrayList();//array of buttons

    public void addPlayers(){//method to add the players to the game
        players.clear();//clears the player array
        for(int i = 0;i < 3; i++){//loop to run 3 times
            players.add(new ProcessPlayers(JOptionPane.showInputDialog("What is your name player " + (i+1)),i));
            //asks the user for thhe player names
        }
    }
    
    public void popQuest(){//method to populate the questions
        tempArray.add(new ProcessQuestion("How many star wars movies does Chewbacca appear in?", "5",0));
        tempArray.add(new ProcessQuestion("Was is the name of the newest star wars movie?", "The Force Awakens",1));
        tempArray.add(new ProcessQuestion("What is the name of the weapon jedis use?", "Lightsaber",2));
        tempArray.add(new ProcessQuestion("What is the name of the famous droid in the star wars films?", "R2D2",3));
        tempArray.add(new ProcessQuestion("What is the color of Luke Skywalker's lightsaber?", "Green",4));       
        tempArray.add(new ProcessQuestion("Who is the AP Java teacher at Conant?", "Cortez",5));
        tempArray.add(new ProcessQuestion("What is the name of the school you are in?", "Conant",6));
        tempArray.add(new ProcessQuestion("What is the name of the app teachers use to manipulate iPads?", "Casper",7));
        tempArray.add(new ProcessQuestion("What is the mascot for Conant?", "Cougar",8));
        tempArray.add(new ProcessQuestion("When was James B. Conant High School founded?", "1964",9));        
        tempArray.add(new ProcessQuestion("How many Rock band games are there?", "4",10));
        tempArray.add(new ProcessQuestion("What is the name of the singer for Guns N' Roses?", "Axl Rose",11));
        tempArray.add(new ProcessQuestion("What is the name of the drummer for the band Rush?", "Neil Peart",12));
        tempArray.add(new ProcessQuestion("Who started Metallica?", "James Hetfield",13));
        tempArray.add(new ProcessQuestion("Which band played Stairway to Heaven?", "Led Zeppelin",14));       
        tempArray.add(new ProcessQuestion("What is the language we are coding in?", "Java",15));
        tempArray.add(new ProcessQuestion("What is the OS that most people use for their computer?", "Windows",16));
        tempArray.add(new ProcessQuestion("What is it called when you have the same name to a method with different parameters?", "Overloading",17));
        tempArray.add(new ProcessQuestion("What is the IDE we use for Java?", "NetBeans",18));
        tempArray.add(new ProcessQuestion("\"for()\", \"while()\", \"do{} while()\" are all examples of a...", "loop",19));                       
        tempArray.add(new ProcessQuestion("Who is the Greek God of the sea?", "Poseidon",20));        
        tempArray.add(new ProcessQuestion("Who is the Greek God of the underworld?", "Hades",21));        
        tempArray.add(new ProcessQuestion("Who is the Greek God of the Gods?", "Zeus",22));       
        tempArray.add(new ProcessQuestion("Who is the Greek Goddess of love?", "Aphrodite",23));
        tempArray.add(new ProcessQuestion("Who is the Greek God of music?", "Apollo",24));  
        //25 questions all into categories
        questions.clear();//clears the question array
        for(int i = 4;i >= 0;i--){//loop to remove the questions from the temp array
            for(int j = 4;j >= 0;j--){//and adds them into the question array
                questions.add(tempArray.remove((int) (Math.random()*(j+1))));//and randomizes the questions in each category
            }
        }
        dailyD = (int) (Math.random()*25);//chooses a random number for daily double
    }

    public GuiController() {//constructor of the program
        JOptionPane.showMessageDialog(null, "Welcome to Jeopardy, there are 5 categories\n3players compete to see who is\nthe smartest");
        
    }
    
    public void setDisable(){//method to disable all the buttons
        for(Button but: btns)//loop to disable all the buttons
            but.setDisable(true); 
    }
    
    public void setEnable(){//method to enable all the buttons
        for(Button but: btns)//loop to enable all the buttons
            but.setDisable(false); 
    }
    
    @FXML
    private void handleStart()//button to start the game
    {
        addPlayers();//adds the player
        lblKPIns.setText(players.get(0).getName() + " press the A Key to Buzz in.\n" + players.get(1).getName() + " press the B Key to Buzz in.\n" + players.get(2).getName() + " press any other Key to buzz in.");
        //displays the label for the key press
        beginGame();//begins the game
        menuStart.setText("Restart with new Players");//changes the menu button text
        menuReset.setVisible(true);//makes the second reset button visible
        
    }
    
    public void beginGame(){//method to begin the game
        for(ProcessPlayers play : players)//resets the points for each player
            play.resPoints();
        updateLbl();//get player names and display in labels their names and points
        tQuestions = 0;//total questions is 0
        popButtons();//populates the button array
        popQuest();//populates the questions array
        lblMessages.setText(null);//displays nothing in the labels
        lblQuestion.setText(null);
        setEnable();//enables the buttons
        pTurn = (int) (Math.random()*3);//picks a random player to go
        setTurnLabel();//sets the label color to blue for the player turn
    }
    
    private void changeturn(){//method to change turn
        pTurn++;//adds 1 to the player turn
        if(pTurn%3 == 1){
            pTurn = 0;
            //player 1 turn
        }
        else if(pTurn%2 == 2){
            pTurn = 1;
            //player 2 turn
        }
        else
            pTurn = 2;
            //player 3 turn
    }

    private void popButtons(){//populates the array of buttons
        btns.clear();
        btns.add(btnC3L1);
        btns.add(btnC4L1);
        btns.add(btnC5L1);
        btns.add(btnC1L1);
        btns.add(btnC1L2);
        btns.add(btnC1L3);
        btns.add(btnC1L4);
        btns.add(btnC1L5);
        btns.add(btnC2L1);
        btns.add(btnC2L2);
        btns.add(btnC2L3);
        btns.add(btnC3L2);
        btns.add(btnC3L3);
        btns.add(btnC2L4);
        btns.add(btnC4L2);
        btns.add(btnC5L2);
        btns.add(btnC4L3);
        btns.add(btnC5L3);
        btns.add(btnC3L4);
        btns.add(btnC4L4);
        btns.add(btnC5L4);
        btns.add(btnC3L5);
        btns.add(btnC4L5);
        btns.add(btnC5L5);
        btns.add(btnC2L5);
    }
    
    private void setTurnLabel()//changes the color of the label for the players
    {
        lblName1.setTextFill(Color.BLACK);
        lblName2.setTextFill(Color.BLACK);
        lblName3.setTextFill(Color.BLACK);
        //set all the labels = default
        if(pTurn == 0)
            //change background color of player 1
            lblName1.setTextFill(Color.BLUE);
        else if(pTurn == 1)
            lblName2.setTextFill(Color.BLUE);
                //change background color of player 2
        else
            lblName3.setTextFill(Color.BLUE);
        //changes the background color of player 3
    }
    @FXML
    private void handleReset() 
    {
        //resets the game 
        beginGame();//starts the game again
    }
    @FXML
    private void handleKey(KeyEvent event)//key code
    { 
        if(canBuzz){//checks if can Buzz is true
            if(event.getCode() == KeyCode.A){//player 1 is A
                if(players.get(0).getBuzz()){//checks if the player has buzzed in already
                    cPlayer = 0;//current player is 0 or player 1
                    doQuest();//does the question
                }
                else//already buzzed in
                    JOptionPane.showMessageDialog(null, "You already buzzed.");
            }
            else if (event.getCode() == KeyCode.B){//player 2 is B
                if(players.get(1).getBuzz()){//checks if the player has buzzed in already
                    cPlayer = 1;//current player is 1 or player 2
                    doQuest();//soes the question
                }
                else//already buzzed in
                    JOptionPane.showMessageDialog(null, "You already buzzed.");
            }
            else{//player 3 is any other key
                if(players.get(2).getBuzz()){//checks if the player has buzzed in already
                    cPlayer = 2;//current player is 2 or player 3
                    doQuest();
                }
                else//already buzzed in
                    JOptionPane.showMessageDialog(null, "You already buzzed.");
            }

        }
        
    }
    
    public void updateLbl(){//updates the labels for names and score
        lblName1.setText(players.get(0).getName());//player 1 name
        lblScore1.setText(players.get(0).getPoints()+"");//player 1 score
        lblName2.setText(players.get(1).getName());//player 2 namme
        lblScore2.setText(players.get(1).getPoints()+"");//player 2 score
        lblName3.setText(players.get(2).getName());//player 3 name
        lblScore3.setText(players.get(2).getPoints()+"");//player 3 score
        lblSW.setVisible(true);//label for the categories are set to visible
        lblSchool.setVisible(true);
        lblMusic.setVisible(true);
        lblCS.setVisible(true);
        lblGG.setVisible(true);
    }
    
    private void doQuest(){//question method
        if(dDob)//checks if daily double is true
            return;//stops the mathod
        String response = JOptionPane.showInputDialog("What is the answer " + players.get(cPlayer).getName() + "?");//gets the use answer
        if(response.equalsIgnoreCase(questions.get(cQuestID).getAns())){//checks if they are correct
            lblMessages.setText("Correct!");//displays correct
            players.get(cPlayer).addPoints(numPoints);//adds the points to the player
            updateLbl();//uupdates the score
            pTurn = cPlayer;//makes the pTurn = the current player
            lblQuestion.setText(null);//sets the question label to null
            setTurnLabel();//sets turn label for new chooser
            setEnable();//enables the buttons
            canBuzz = false;//buzzing is set to false
            checkW();//checks for the winner
        }
        else{//if the player is wrong
            lblMessages.setText("Incorrect");//display incorrect
            players.get(cPlayer).subPoints(numPoints);//subtracts the player points
            players.get(cPlayer).cantBuzz();//current player cant buzz in anymore
            numInc += 1;//number incorrect is added  by 1
            updateLbl();//updates the scores
            checkW();//checks for a winner
        }
        if(numInc == 3){//checks to see if all 3 players didnt answer it
            lblMessages.setText("The correct answer is " + questions.get(cQuestID).getAns());//displays the answer
            lblQuestion.setText(null);//sets the question label to null
            setEnable();//enables the buttons
            canBuzz = false;//buzzing is set to false
            return;//stops the method
        }
    }
    
    public void checkW(){
        if(tQuestions == 25){
            //person with the most points wins
            if((players.get(0).getPoints() > players.get(1).getPoints()) && (players.get(0).getPoints() > players.get(2).getPoints()))
                //player 1 wins
                JOptionPane.showMessageDialog(null, players.get(0).getName() + " WINS JEOPARDY!!!");
            else if((players.get(1).getPoints() > players.get(0).getPoints()) && (players.get(1).getPoints() > players.get(2).getPoints()))    
                //player 2 wins
                JOptionPane.showMessageDialog(null, players.get(1).getName() + " WINS JEOPARDY!!!");
            else
                //player 3 wins
                JOptionPane.showMessageDialog(null, players.get(2).getName() + " WINS JEOPARDY!!!");
        }
    }
    
    public void doButton(Button btn, int value){//method that is called in every button
        String response;//response for daily double
        lblMessages.setText(null);//label to display a message set to null
        tQuestions++;//total questions ++
        canBuzz = true;//they can all buzz in
        numInc = 0;//number incorrect is reset
        for(ProcessPlayers play: players)//loop to let all the players be able to buzz in
            play.canBuzz();//buzz in boolean
        setDisable();//disables all the buttons
        btns.remove(btn);//removes the button clicked
        if(dailyD == questions.get(cQuestID).getID()){//checks if daily double is there
            dDob = true;//daily double is true
            if(players.get(pTurn).getPoints() <= 0){//checks if the player has less than 0 points
                do//loop to get their input
                    dDPoints = Integer.parseInt(JOptionPane.showInputDialog("DAILY DOUBLE!\n" + players.get(pTurn).getName() + " since you have insufficient points,\nyou can bet from 100 points up to 500."));            
                while(dDPoints > 500 || dDPoints <100);//checks their amount input
                response = JOptionPane.showInputDialog(questions.get(cQuestID).getQuest());//displays the question
                if(response.equalsIgnoreCase(questions.get(cQuestID).getAns())){//checks if they got it right
                    lblMessages.setText("Correct!");//label set to correct
                    players.get(pTurn).addPoints(dDPoints);//adds the points from daily double
                    lblQuestion.setText(null);//question is set to null
                    updateLbl();//updates the score
                    setTurnLabel();//changes the current player turn
                    setEnable();//enables all the buttons
                    checkW();//checks the winner
                    canBuzz = false; //cant buzz  
                }
                else{
                    lblMessages.setText("Incorrect");//outputs incorrect
                    players.get(pTurn).subPoints(dDPoints);//subtracts points
                    checkW();//checks if their is a winner
                    updateLbl();//updates the score
                    canBuzz = false;//can buzz is false
                }
            }
            else{
                do//loop to get their input
                    dDPoints = Integer.parseInt(JOptionPane.showInputDialog("DAILY DOUBLE!\n" + players.get(pTurn).getName() + " how much do you want to bet?\nThe bounds are between 100 to all in."));            
                while(dDPoints > players.get(pTurn).getPoints() || dDPoints <100);//checks if their input is between 100 and total poitns
                response = JOptionPane.showInputDialog(questions.get(cQuestID).getQuest());//displays the question
                if(response.equalsIgnoreCase(questions.get(cQuestID).getAns())){//checks if they got it right
                    lblMessages.setText("Correct!");//label set to correct
                    players.get(pTurn).addPoints(dDPoints);//adds the points from daily double
                    lblQuestion.setText(null);//question is set to null
                    updateLbl();//updates the score
                    setTurnLabel();//changes the current player turn
                    setEnable();//enables all the buttons
                    checkW();//checks the winner
                    canBuzz = false; //cant buzz  
                }
                else{
                    lblMessages.setText("Incorrect");//outputs incorrect
                    players.get(pTurn).subPoints(dDPoints);//subtracts points
                    checkW();//checks if their is a winner
                    updateLbl();//updates the score
                    canBuzz = false;//can buzz is false
                }
            }
            return;//stops the method from continuing
        }
        numPoints = value;//sets the current points to the value of the button
        lblQuestion.setText(questions.get(cQuestID).getQuest());//displays the question
        dDob = false;//daily double is false
    }
    
    
    @FXML   
    private void handleC1L1() //method for the button being clicked
    {
        cQuestID = 0;//passes the current question ID
        doButton(btnC1L1, 100);//calls the method and passes the button name and the score the question is worth

    }
    @FXML
    private void handleC1L2() //method for the button being clicked
    {
        cQuestID = 1;//passes the current question ID
        doButton(btnC1L2,200);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC1L3() //method for the button being clicked
    {
        cQuestID = 2;//passes the current question ID
        doButton(btnC1L3,300);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC1L4() //method for the button being clicked
    {
        cQuestID = 3;//passes the current question ID
        doButton(btnC1L4,400);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC1L5() //method for the button being clicked
    {
        cQuestID = 4;//passes the current question ID
        doButton(btnC1L5,500);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC2L1() //method for the button being clicked
    {
        cQuestID = 5;//passes the current question ID
        doButton(btnC2L1,100);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC2L2() //method for the button being clicked
    {
        cQuestID = 6;//passes the current question ID
        doButton(btnC2L2,200);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC2L3() //method for the button being clicked
    {
        cQuestID = 7;//passes the current question ID
        doButton(btnC2L3,300);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC2L4() //method for the button being clicked
    {
        cQuestID = 8;//passes the current question ID
        doButton(btnC2L4,400);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC2L5() //method for the button being clicked
    {
        cQuestID = 9;//passes the current question ID
        doButton(btnC2L5,500);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC3L1() //method for the button being clicked
    {
        cQuestID = 10;//passes the current question ID
        doButton(btnC3L1,100);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC3L2() //method for the button being clicked
    {
        cQuestID = 11;//passes the current question ID
        doButton(btnC3L2,200);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC3L3() //method for the button being clicked
    {
        cQuestID = 12;//passes the current question ID
        doButton(btnC3L3,300);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC3L4() //method for the button being clicked
    {
        cQuestID = 13;//passes the current question ID
        doButton(btnC3L4,400);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC3L5() //method for the button being clicked
    {
        cQuestID = 14;//passes the current question ID
        doButton(btnC3L5,500);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC4L1() //method for the button being clicked
    {
        cQuestID = 15;//passes the current question ID
        doButton(btnC4L1,100);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC4L2() //method for the button being clicked
    {
        cQuestID = 16;//passes the current question ID
        doButton(btnC4L2,200);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC4L3() //method for the button being clicked
    {
        cQuestID = 17;//passes the current question ID
        doButton(btnC4L3,300);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC4L4() //method for the button being clicked
    {
        cQuestID = 18;//passes the current question ID
        doButton(btnC4L4,400);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC4L5() //method for the button being clicked
    {
        cQuestID = 19;//passes the current question ID
        doButton(btnC4L5,500);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC5L1() //method for the button being clicked
    {
        cQuestID = 20;//passes the current question ID
        doButton(btnC5L1,100);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC5L2() //method for the button being clicked
    {
        cQuestID = 21;//passes the current question ID
        doButton(btnC5L2,200);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC5L3() //method for the button being clicked
    {
        cQuestID = 22;//passes the current question ID
        doButton(btnC5L3,300);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC5L4() //method for the button being clicked
    {
        cQuestID = 23;//passes the current question ID
        doButton(btnC5L4,400);//calls the method and passes the button name and the score the question is worth
    }
    @FXML
    private void handleC5L5() //method for the button being clicked
    {
        cQuestID = 24;//passes the current question ID
        doButton(btnC5L5,500);//calls the method and passes the button name and the score the question is worth
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
