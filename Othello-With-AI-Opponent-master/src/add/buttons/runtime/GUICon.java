/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package add.buttons.runtime;

import com.sun.prism.paint.Color;
import java.awt.Transparency;

import java.net.URL;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javax.swing.JOptionPane;

/**
 *
 * @author R2D2 (omar)
 */
public class GUICon implements Initializable {
    ImageView[][] btn = new ImageView[8][8];//array of image views
    int[][] tileData = new int[8][8];//array of ints to store all the data 0,1,2
    String[] picP = new String[4];//array to store the image name
    private int pTurn;//integer to keep track whos turn it is
    @FXML
    private Button btnStart;//intitial button
    @FXML
    private Label lblPlayer1, lblPlayer2, lblScore1, lblScore2, lblPlayers, lblScores, lblWinner;//labels
    @FXML
    private ImageView imgP1Turn, imgP2Turn, imgP1Tile, imgP2Tile, imgWin;//images
    @FXML
    private AnchorPane aPane;
    @FXML
    private GridPane gPane;//grid pane
    
    private boolean botMode = false;
    
    private int turns = 4;//integer to keep track of total turns
    
    public GUICon(){
        JOptionPane.showMessageDialog(null, "Welcome to othello!\nHere you try and get\n"
                                          + "as of your color tiles\non the board. The gray\n"
                                          + "spots are the only spots\nthat you can put a tile.\n"
                                          + "      Good Luck!");
    }
    @FXML
    private void handleStart(ActionEvent event) {
        String mode;
        do{//do while loop to check the difficulty
        mode = JOptionPane.showInputDialog("Do you want to go against a player or a bot?");//asks the user for the difficulty
        }
        while(!mode.equalsIgnoreCase("player") && !mode.equalsIgnoreCase("bot"));//loop if the answer isint any of the options
        if(mode.equalsIgnoreCase("bot"))
            botMode = true;
        lblPlayer1.setText(JOptionPane.showInputDialog("What is your name player 1?"));//asks the user for their name
        lblPlayer2.setText("Bender");
        if(!botMode)
            lblPlayer2.setText(JOptionPane.showInputDialog("What is your name player 2?"));
        lblPlayer1.setVisible(true);//makes 
        lblPlayer2.setVisible(true);//all 
        lblScore1.setVisible(true);//the 
        lblScore2.setVisible(true);//labels
        lblPlayers.setVisible(true);//visible
        lblScores.setVisible(true);
        imgWin.setVisible(false);
        lblWinner.setVisible(false);
        imgP1Tile.setVisible(true);
        imgP2Tile.setVisible(true);
        imgP1Tile.setImage(new Image("resources/Black.png"));
        imgP2Tile.setImage(new Image("resources/White.png"));
        turns = 4; //resets the total turns
        gPane.setGridLinesVisible(false);//sets the grid lines visible to false
        //Put the path name strings into your String array.
        picP[0] = "Black.png";
        picP[1] = "White.png";
        picP[2] = "Neutral.png";
        picP[3] = "Open.png";
        imgP1Turn.setImage(new Image("resources/Arrow.png"));//sets the image
        imgP2Turn.setImage(new Image("resources/Arrow.png"));//to show whos turn it is
        imgP1Turn.setVisible(true);
        imgP2Turn.setVisible(true);
        for(int i=0; i<btn.length; i++){
                for(int j=0; j<btn.length;j++){
                        btn[i][j] = new ImageView();//makes a new image view
                        tileData[i][j] = 2;//makes all the tiles neutral
                        tileData[3][3] = 1;//makes
                        tileData[4][4] = 1;//the
                        tileData[3][4] = 0;//intitial
                        tileData[4][3] = 0;//chips to start
                        btn[i][j].setImage(new Image("resources/" + picP[tileData[i][j]]));//sets the image in each image view
                        btn[i][j].setFitHeight(50);//sets the height
                        btn[i][j].setFitWidth(50);//sets the width
                        //adds the image views to the g Pane
                        gPane.add(btn[i][j], j, i);
                        btn[i][j].setDisable(false);
                    }
        }
        btn[3][3].setDisable(true);//makes
        btn[4][4].setDisable(true);//the
        btn[3][4].setDisable(true);//intitial
        btn[4][3].setDisable(true);//chips to start
        gPane.setGridLinesVisible(true);//makes the grid lines visible
        gPane.setVisible(true);//sets the g pane visible   
        //random player is chosen
        pTurn = (int) (Math.random()*2);//picks a random person to go first
        disSpots();//displays the spots where players can place their tiles
        if(pTurn == 0){//condition to check who is going first
            JOptionPane.showMessageDialog(null, lblPlayer1.getText() + " goes first, you are the black chips.");//displays that player 1 is going first
            imgP2Turn.setVisible(false);
        }
        else{
            imgP1Turn.setVisible(false);
            if(!botMode)
                JOptionPane.showMessageDialog(null, lblPlayer2.getText() + " goes first, you are the white chips.");//displays that player 2 is going first
            else{
                JOptionPane.showMessageDialog(null, lblPlayer2.getText() + " will go first.");
                botChoice();
            }
        }
        updateBoard();//updates the board with the images
        updateScore();//updates the score
        //event handler to be put into every button
        EventHandler z = new EventHandler<MouseEvent>() 
        {
            
            @Override
            public void handle(MouseEvent t) 
            {
                defaultSpots();//makes all the open spots to 2
                // gets the rowindex and columnindex from the button clicked
                int row = GridPane.getRowIndex(((ImageView) t.getSource()));
                int col = GridPane.getColumnIndex(((ImageView) t.getSource()));
                btn[row][col].setDisable(true);
                //call method to decide if the user clicked on a valid space.
                if(!checkMove(row, col)){
                    btn[row][col].setDisable(false);
                    counter = 0;//makes the counter 0 
                    disSpots();//displays the spots where players can place their tiles
                    return;
                }
                //change the data array using row,column to match the player turn
                tileData[row][col] = pTurn;
                //call the horizontal check method
                checkHorizontal(row, col);
                //call the vertical check method
                checkVertical(row, col);
                //call the diagonal check method here
                checkDiag(row, col);
                //adds 1 to the total turns done
                turns++;
                //call the change player method
                changeTurn();
                //updates the score
                updateScore();
                //updates the board
                updateBoard(); 
                //makes the counter 0
                counter = 0;
                if(!checkWinner()){//checks if someone has won
                    disSpots();
                }else//displays the spots where players can place their tiles
                    return;
                //updates the board
                updateBoard(); 
                if(botMode && pTurn == 1){
                    botChoice();
                }        
            }            
        };
        for(int i=0; i<btn.length; i++){//sets the mouse clicked
            for(int j=0; j<btn.length;j++){//handler to the one above
                btn[i][j].setOnMouseClicked(z);//to all the buttons
            }
        }
        btnStart.setText("Restart the game");//sets the start game button to restart
    }
    int[][] tempData = new int[8][8];//array to store temporary data
    private int counter = 0;//counter to stop a loop from happening when no moves are available
    ArrayList<BotMove> botData = new ArrayList();
    public void botChoice(){
        botData.clear();
        int temp = 0;
        if(checkAvaMoves()){
            for(int i = 0; i < tileData.length; i++)//double loop to go through the whole array
                for(int j = 0; j < tileData.length; j++)
                    if(tileData[i][j] == 3){
                        temp++;
                        botData.add(new BotMove(i,j));
                    }
            defaultSpots();//makes all the open spots to 2
            int move = ((int) (Math.random()* temp));
            int row = botData.get(move).getX();
            int col = botData.get(move).getY();
            btn[row][col].setDisable(true);
            tileData[row][col] = pTurn;
            //call the horizontal check method
            checkHorizontal(row, col);
            //call the vertical check method
            checkVertical(row, col);
            //call the diagonal check method here
            checkDiag(row, col);
            //adds 1 to the total turns done
            turns++;
            //call the change player method
            changeTurn();
            //updates the score
            updateScore();
            //makes the counter 0
            counter = 0;
            if(!checkWinner())//checks if someone has won
                disSpots();//displays the spots where players can place their tiles
            //updates the board
            updateBoard();
        }
        
    }
    
    public void disSpots(){//method that displays the spots where players can place their tiles
        for(int i = 0; i < tileData.length; i++)//double loop to go through the whole array
            for(int j = 0; j < tileData.length; j++)
                if(tileData[i][j] == 0 || tileData[i][j] == 1){}//skip over the tiles that have a color already
                else if(checkMove(i,j))//stores the data in temp array
                    tempData[i][j] = 3; 
        int temp = 0;//temp var to check i no data changed
        for(int i = 0; i < tempData.length; i++)//double loop to go through the whole array
            for(int j = 0; j < tempData.length; j++){
                if(tempData[i][j] == 3)//if there is a 3, it changes the data array
                    tileData[i][j] = 3;
                else if(tempData[i][j] == 0)//if there is a 0
                    temp++;//it adds 1 to temp 
                tempData[i][j] = 0;//sets that content to 0
            }
        if(temp == 64 && turns != 63){//condition to check if there is no moves
            if(pTurn == 0){//checks whos turn it is
                JOptionPane.showMessageDialog(null, lblPlayer1.getText() + ", you pass because you have no available turns.");
                //outputs a message to tell the user he has to pass
                if(counter != 0){//checks if this is the first player that passed
                    checkWinner();//checks who won 
                    return;
                }
                counter ++; //adds 1 to num players passed
            }
            else if(pTurn == 1){//checks whos turn it is
                JOptionPane.showMessageDialog(null, lblPlayer2.getText() + ", you pass because you have no available turns.");
                //outputs a message to tell the user he has to pass
                if(counter != 0){//checks if this is the first player that passed
                    checkWinner();//checks who won 
                    return;
                }
                counter ++;  //adds 1 to num players passed         
            }
            changeTurn();//changes the player turn to check if they also have to pass
            disSpots();//displays the clickable spots
        }
    }
    
    public void defaultSpots(){//method to change any content of 3 back to 2
        for(int i = 0; i < tileData.length; i++)//double loop to go through the whole array
            for(int j = 0; j < tileData.length; j++)
                if(tileData[i][j] == 3)
                    tileData[i][j] = 2;  
    }
    
    public void changeTurn(){//changes the player turn
        pTurn++;//adds one to pturn
        if(pTurn%2 == 1){
            pTurn = 1;
            imgP1Turn.setVisible(false);//displays whos
            imgP2Turn.setVisible(true);//turn it is
        }
        else{
            pTurn = 0;
            imgP1Turn.setVisible(true);//displays whos
            imgP2Turn.setVisible(false);//turn it is
        }
    }
    
    public void updateScore(){//updates the score
        int p1 = 0, p2 = 0;//makes the score 0
        for(int i = 0; i < tileData.length; i++)//goes through the data array
            for(int j = 0; j < tileData.length; j++){//to check how many
                if(tileData[i][j] == 0)//1's and 0's there are in total
                    p1++;
                else if(tileData[i][j] == 1)
                    p2++;
            }
        lblScore1.setText(p1 + "");//displays the score
        lblScore2.setText(p2 + "");//for each player
    }
    
    public void updateBoard(){//updates the board according to the data array
        for(int i = 0; i < tileData.length; i++){//double loop to display the images according to the data array
            for(int j = 0; j < tileData.length; j++){
                btn[i][j].setImage(new Image("resources/" + picP[tileData[i][j]]));//sets the images for the board
                
            }
        }
    }

    public void printArray(){//prints the array of data for debugging
        for(int i = 0; i < 8; i++){
            System.out.println("");
            for(int j = 0; j < 8; j++){
                System.out.print(tileData[i][j] + "   ");
            }
        }
        System.out.println("");
        System.out.println("");
    }
    
    public boolean checkWinner(){//checks the winner
        if(turns == 64){//condition to see if all spaces have been filled
            disWinner();//method to display the winner
            return true;
        }
        else if(!checkAvaMoves()){//condition to see if the game ended by there
            disWinner();//being no more moves for both players
            return true;
        }
        return false;
    }
    
    public void disWinner(){//method to display the winner
        imgWin.setVisible(true);
        lblWinner.setVisible(true);
        imgWin.setImage(new Image("resources/Congrats.gif"));
        if(Integer.parseInt(lblScore1.getText()) > Integer.parseInt(lblScore2.getText())){
            //player 1 wins
            lblWinner.setText(lblPlayer1.getText() + " WINS!!!"); 
        }
        else if(Integer.parseInt(lblScore1.getText()) < Integer.parseInt(lblScore2.getText())){
            //player 2 wins
            lblWinner.setText(lblPlayer2.getText() + " WINS!!!"); 
            if(botMode)
                imgWin.setImage(new Image("resources/Bender.gif"));
        }
        else{
            imgWin.setImage(new Image("resources/Tie.jpg"));
            //tie
            lblWinner.setText("It's a Tie!!!"); 
        }
        imgP1Turn.setVisible(false);
        imgP2Turn.setVisible(false);
    }
    
    public boolean checkAvaMoves(){//method to check any available moves
        int temp = pTurn, count = 0;//counter and saves the current pTurn
        while(count < 2){//loop to through both players if needed
            for(int i = 0; i < tileData.length; i++)//double loop to go through the whole array
                for(int j = 0; j < tileData.length; j++)
                    if(tileData[i][j] == 0 || tileData[i][j] == 1){}//skip over it
                    else if(checkMove(i,j)){//checks if any move is possible
                        pTurn = temp;//makes the pTurn back to what it was
                        return true; //returns true
                    }
            changeTurn();//changes the player
            count ++;//goes to the next player
        }
        pTurn = temp;
        return false;
    }
    
    public boolean checkMove(int row, int col){//method to check the player move
        if(checkHorMove(row, col))//calls every method that 
            return true;//checks moves 
        if(checkVerMove(row, col))
            return true;
        if(checkDiagMove(row, col))
            return true;
        return false;
    }

    public boolean checkHorMove(int row, int col){
        boolean badMove = false;//boolean to help determine if there is no match
        if(col != 7)//condition to check to prevent out of bound error
            for(int i = col + 1; i < 8 ; i++){//loop to go through the board
                if(tileData[row][i] == 2)//condition to check if there is a neutral tile
                    badMove = true;
                if(tileData[row][i] == pTurn && i - col == 1)//condition to check for a match after it finds
                    badMove = true;//its same color tile, not a valid move
                else if(tileData[row][i] == pTurn && i - col != 1 && !badMove){//condition to check for
                    return true;//a valid match
                }
            }
        badMove = false;//goes back to false to test the other direction
        if(col != 0)//condition to check to prevent out of bound error
            for(int j = col - 1; j >= 0; j--){//loop to go through the board
                if(tileData[row][j] == 2)//condition to check if there is a neutral tile
                    badMove = true;
                if(tileData[row][j] == pTurn && col - j == 1)//condition to check for a match after it finds
                    badMove = true;//its same color tile, not a valid move
                else if(tileData[row][j] == pTurn && col - j != 1 && !badMove)//condition to check for
                    return true;//a valid match
            }     
        return false;
    }
    
    public boolean checkVerMove(int row, int col){
        boolean badMove = false;
        if(row != 0)//condition to check to prevent out of bound error
            for(int i = row - 1; i >= 0 ; i--){//loop to go through the board                
                if(tileData[i][col] == 2)//condition to check if there is a neutral tile
                    badMove = true;
                if(tileData[i][col] == pTurn && row - i == 1)//condition to check for a match after it finds
                    badMove = true;//its same color tile, not a valid move
                else if(tileData[i][col] == pTurn && row - i != 1 && !badMove)//condition to check for
                    return true;//a valid match
            }
        badMove = false;
        if(row != 7)//condition to check to prevent out of bound error 
            for(int j = row + 1; j < 8 ; j++){//loop to go through the board
                if(tileData[j][col] == 2)//condition to check if there is a neutral tile
                    badMove = true;
                if(tileData[j][col] == pTurn && j - row == 1)//condition to check for a match after it finds
                    badMove = true;//its same color tile, not a valid move
                else if(tileData[j][col] == pTurn && j - row != 1 && !badMove)//condition to check for
                    return true;//a valid match
            }
        return false;
    }
    
    public boolean checkDiagMove(int row, int col){//checks for a valid move
        if(checkPosDiMove(row, col))//in both diagonals
            return true;
        else if(checkNegDiMove(row, col))
            return true;
        else
            return false;
    }
    
    public boolean checkNegDiMove(int row, int col){
        boolean badMove = false;//boolean to help determine if there is no match
        if(col != 7 && row != 7){//condition to check to prevent out of bound error
            int j = col;//stores the column data
            try{//try statement to look for out of bounds error
                for(int i = row + 1; i < 8; i++){
                    j++;
                    if(tileData[i][j] == 2)//condition to check if there is a neutral tile
                        badMove = true;      
                    if(tileData[i][j] == pTurn && i-row == 1)//condition to check for a match after it finds
                        badMove = true;//its same color tile, not a valid move
                    else if(tileData[i][j] == pTurn && i-row != 1 && !badMove)//condition to check for
                        return true;//a valid match
                }
            }catch(ArrayIndexOutOfBoundsException e){}//catches the error
        }
        badMove = false;
        if(col != 0 && row != 0){//condition to check to prevent out of bound error
            int y = col;//stores the column data
            try{//try statement to look for out of bounds error
                for(int x = row - 1; x >= 0; x--){
                    y--;
                    if(tileData[x][y] == 2)//condition to check if there is a neutral tile
                        badMove = true;  
                    if(tileData[x][y] == pTurn && row-x == 1)//condition to check for a match after it finds
                        badMove = true;//its same color tile, not a valid move           
                    else if(tileData[x][y] == pTurn && row-x != 1 && !badMove)//condition to check for
                        return true;//a valid match
                }
            }catch(ArrayIndexOutOfBoundsException e){//catches the error
                return false;
            }
        }
        return false;
    }
    
    public boolean checkPosDiMove(int row, int col){
        boolean badMove = false;//boolean to help determine if there is no match
        if(col != 7 && row != 0){//condition to check to prevent out of bound error
            int j = col;//stores the column data
            try{//try statement to look for out of bounds error
                for(int i = row - 1; i >= 0; i--){
                    j++;
                    if(tileData[i][j] == 2)//condition to check if there is a neutral tile              
                        badMove = true;
                    if(tileData[i][j] == pTurn && row - i == 1)//condition to check for a match after it finds
                        badMove = true;//its same color tile, not a valid move
                    else if(tileData[i][j] == pTurn && row - i != 1 && !badMove)//condition to check for
                        return true;//a valid match
                }
            }catch(ArrayIndexOutOfBoundsException e){}//catches the error
        }
        badMove = false;
        if(col != 0 && row != 7){//condition to check to prevent out of bound error
            int y = col;//stores the column data
            try{//try statement to look for out of bounds error
                for(int x = row + 1; x < 8; x++){
                    y--;
                    if(tileData[x][y] == 2)//condition to check if there is a neutral tile
                        badMove = true;
                    if(tileData[x][y] == pTurn && x-row == 1)//condition to check for a match after it finds
                        badMove = true;//its same color tile, not a valid move               
                    else if(tileData[x][y] == pTurn && x-row != 1 && !badMove)//condition to check for
                        return true;//a valid match
                }
            }catch(ArrayIndexOutOfBoundsException e){//catches the error
                return false;
            }
        }
        
        return false;
    }
    
    public void checkDiag(int row, int col){//method to check
        checkNDiRight(row, col);//for a flip in tiles 
        checkNDiLeft(row, col);//in the diagonals
        checkPDiRight(row, col);
        checkPDiLeft(row, col);
    }
    
    public void checkNDiRight(int row, int col){
        if(col != 7 && row != 7){//condition to check to prevent out of bound error
            int i;//variable to store the row
            int j = col;//variable to store the column
            int temp = 0;//temp to store the limit
            int bound = 8;//variable to store the bound for the loop
            boolean match = false;//boolean to check if there is a match
            try{//try statement to look for out of bounds error
                for(i = row + 1; i < bound; i++){
                    j++;
                    if(tileData[i][j] == 2)//condition to check if there is a neutral tile
                        return;
                    else if(tileData[i][j] == pTurn){//condition to check for a match
                        temp = i;//stores the limit for the next loop
                        match = true;//makes match true
                        break;
                    }
                }
            }catch(ArrayIndexOutOfBoundsException e){//catches the error
                bound--;//dicreaces the bound
                i = row + 1;//resets i
                j = col;//resets j
            }
            i = row;
            j = col;
            if(match)//condition to check if there has been a match
                for(i = row + 1; i <= temp; i++){//loop to change the data 
                    j++;
                    tileData[i][j] = pTurn;  //changes the data to the right number
                }
        }
    }   
    
    public void checkNDiLeft(int row, int col){
        if(col != 0 && row != 0){//condition to check to prevent out of bound error
            int i;//variable to store the row
            int j = col;//variable to store the column
            int temp = 0;//temp to store the limit
            int bound = 0;//variable to store the bound for the loop
            boolean match = false;//boolean to check if there is a match
            try{//try statement to look for out of bounds error
                for(i = row - 1; i >= bound; i--){
                    j--;
                    if(tileData[i][j] == 2)//condition to check if there is a neutral tile
                        return;
                    else if(tileData[i][j] == pTurn){//condition to check for a match
                        temp = i;//stores the limit for the next loop
                        match = true;//makes match true
                        break;
                    }
                }
            }catch(ArrayIndexOutOfBoundsException e){//catches the error
                bound++;//increases the bound
                i = row - 1;//resets i
                j = col;//resets j
            }
            i = row;
            j = col;
            if(match)//condition to check if there has been a match
                for(i = row - 1; i > temp; i--){//loop to change the data 
                    j--;
                    tileData[i][j] = pTurn;  //changes the data to the right number
                }
        }
    }
    
    public void checkPDiRight(int row, int col){
        if(col != 7 && row != 0){//condition to check to prevent out of bound error
            int i;//variable to store the row
            int j = col;//variable to store the column
            int temp = 0;//temp to store the limit
            int bound = 0;//variable to store the bound for the loop
            boolean match = false;//boolean to check if there is a match
            try{//try statement to look for out of bounds error
                for(i = row - 1; i >= bound; i--){
                    j++;
                    if(tileData[i][j] == 2)//condition to check if there is a neutral tile
                        return;
                    else if(tileData[i][j] == pTurn){//condition to check for a match
                        temp = i;//stores the limit for the next loop
                        match = true;//makes match true
                        break;
                    }
                }
            }catch(ArrayIndexOutOfBoundsException e){//catches the error
                bound++;//increases the bound
                i = row - 1;//resets i
                j = col;//resets j
            }
            i = row;
            j = col;
            if(match)//condition to check if there has been a match
                for(i = row - 1; i > temp; i--){//loop to change the data 
                    j++;
                    tileData[i][j] = pTurn;  //changes the data to the right number
                }
        }
    }
    
    public void checkPDiLeft(int row, int col){
        if(col != 0 && row != 7){//condition to check to prevent out of bound error
            int i;//variable to store the row
            int j = col;//variable to store the column
            int temp = 0;//temp to store the limit
            int bound = 8;//variable to store the bound for the loop
            boolean match = false;//boolean to check if there is a match
                try{//try statement to look for out of bounds error
                    for(i = row + 1; i < bound; i++){
                        j--;
                        if(tileData[i][j] == 2)//condition to check if there is a neutral tile
                            return;
                        else if(tileData[i][j] == pTurn){//condition to check for a match
                            temp = i;//stores the limit for the next loop
                            match = true;//makes match true
                            break;
                        }
                    }
                }catch(ArrayIndexOutOfBoundsException e){//catches the error
                    bound--;//dicreaces the bound
                    i = row + 1;//resets i
                    j = col;//resets j
                }
            i = row;
            j = col;
            if(match)//condition to check if there has been a match
                for(i = row + 1; i <= temp; i++){//loop to change the data 
                    j--;
                    tileData[i][j] = pTurn;  //changes the data to the right number
                }
        }
    }
    
    public void checkVertical(int row, int col){//method to check the columns 
        checkUp(row, col);//for any tiles needed to be flipped
        checkDown(row, col);
    }
    
    public void checkUp(int row, int col){   
        if(row != 0){//condition to check to prevent out of bound error            
            int i;//integer i
            boolean match = false;//boolean to check if there is a match
            for(i = row - 1; i >= 0 ; i--){//loop to go through the board                
                if(tileData[i][col] == 2)//condition to check if there is a neutral tile
                    return;//stops the method from continuing
                if(tileData[i][col] == pTurn){//condition to check for a match
                    match = true;//match is true                    
                    break;//breaks the loop
                }
            }
            if(match)//condition to check if there has been a match
                for(int j = row - 1; j>= i; j--)//loop to change the data 
                    tileData[j][col] = pTurn;//changes the data to the right number
        }
    }
    
    public void checkDown(int row, int col){
        if(row != 7){//condition to check to prevent out of bound error 
            int temp;//temp variable
            int i;//integer i
            boolean match = false;//boolean to check if there is a match
            for(i = row + 1; i < 8 ; i++){//loop to go through the board
                if(tileData[i][col] == 2)//condition to check if there is a neutral tile
                    return;
                if(tileData[i][col] == pTurn){//condition to check for a match
                    temp = i;//gets the col number
                    match = true;//match is true
                    break;//breaks the loop
                }
            }
            if(match)//condition to check if there has been a match
                for(int j = row + 1; j <= i; j++)//loop to change the data 
                    tileData[j][col] = pTurn;  //changes the data to the right number            
        }
    }
    
    public void checkHorizontal(int row, int col){//method to check the horizontal tiles
        checkRight(row, col);//calls the right method
        checkLeft(row, col);//calls the left method
    }
    
    public void checkRight(int row, int col){//this method checks for any match to the right side of the button clicked
        if(col != 7){//condition to check to prevent out of bound error 
            int temp;//temp variable
            int i;//integer i
            boolean match = false;//boolean to check if there is a match
            for(i = col + 1; i < 8 ; i++){//loop to go through the board
                if(tileData[row][i] == 2)//condition to check if there is a neutral tile
                    return;
                if(tileData[row][i] == pTurn){//condition to check for a match
                    temp = i;//gets the col number
                    match = true;//match is true
                    break;//breaks the loop
                }
            }
            if(match)//condition to check if there has been a match
                for(int j = col + 1; j <= i; j++)//loop to change the data 
                    tileData[row][j] = pTurn;  //changes the data to the right number            
        }
    }
    
    public void checkLeft(int row, int col){//this method checks for any match to the left side of the button clicked
        if(col != 0){//condition to check to prevent out of bound error
            int temp;//temp variable
            int i;//integer i
            boolean match = false;//boolean to check if there is a match
            for(i = col - 1; i >= 0; i--){//loop to go through the board
                if(tileData[row][i] == 2)//condition to check if there is a neutral tile
                    return;
                if(tileData[row][i] == pTurn){//condition to check for a match
                    temp = i;//gets the col number
                    match = true;//match is true
                    break;//breaks the loop
                }
            }
            if(match)//condition to check if there has been a match
                for(int j = col - 1; j >=i; j--)//loop to change the data 
                    tileData[row][j] = pTurn;//changes the data to the right number
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}