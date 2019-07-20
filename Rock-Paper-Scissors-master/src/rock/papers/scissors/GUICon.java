/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rock.papers.scissors;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author omar
 */
public class GUICon implements Initializable {
    
    @FXML
    private Label lblWins, lblLosses, lblTies, lblDiff;
    //labels to set the wins, losses , ties, and the difficulty 
    @FXML
    private Button btnStart,btnSci,btnRock,btnPaper;
    //buttons to start the game, choose rock, paper, or scissors
    @FXML
    private ImageView pImg, aIImg;
    //imageviews to display images for rock, paper, and scissors
    Gameplay mainG = new Gameplay();
    //instance of the gameplay class
    @FXML
    private void handleStart(ActionEvent event) {//button to start the game
        String difficulty;//String to store the difficulty
        do{//do while loop to check the difficulty
        difficulty = JOptionPane.showInputDialog("What difficulty do you want, novice or veteran?");//asks the user for the difficulty
        }
        while(!difficulty.equalsIgnoreCase("novice") && !difficulty.equalsIgnoreCase("veteran"));//loop if the answer isint any of the options
        lblDiff.setText(lblDiff.getText() + " " + difficulty);//makes the label display the difficulty   
        lblDiff.setVisible(true);//makes the label visible
        btnStart.setDisable(true);//makes the button clickable
        btnStart.setVisible(false);//makes the label invisible
        btnSci.setDisable(false);//makes the button clickable
        btnRock.setDisable(false);//makes the button clickable
        btnPaper.setDisable(false);//makes the button clickable
        mainG.setDiff(difficulty);//calls the method to set the difficulty in the gameplay class
    }
    
    @FXML
    private void handleRock(ActionEvent event) {//button to choose rock
        pImg.setImage(new Image("resources/rock.jpg"));//displays the rock image
        mainG.playGame("R");//calls the playGame method in the gameplay class
        setImage();//method to set the computer image
        updateScore();//method to update the score
    }
    
    @FXML
    private void handlePaper(ActionEvent event) {//button to choose paper
        pImg.setImage(new Image("resources/paper.jpg"));
        mainG.playGame("P");//calls the playGame method in the gameplay class
        setImage();//method to set the computer image
        updateScore();//method to update the score
    }
    
    
    @FXML
    private void handleScissors(ActionEvent event) {//button to choose scissors
        pImg.setImage(new Image("resources/scissors.jpg"));
        mainG.playGame("S");//calls the playGame method in the gameplay class
        setImage();//method to set the computer image
        updateScore();//method to update the score
    }
    
    public void setImage(){//method to set the image for the computer
        if(mainG.getAIMove().equals("R"))//condition to check the computer move
            aIImg.setImage(new Image("resources/AIRock.PNG"));//sets the image to rock
        else if(mainG.getAIMove().equals("P"))
            aIImg.setImage(new Image("resources/AIPaper.PNG"));//sets the image to paper
        else
            aIImg.setImage(new Image("resources/AIScis.PNG")); //sets the image to scissors
    }
    
    public GUICon(){//constructor
        JOptionPane.showMessageDialog(null, "Welcome to rock, papers, scissors.\nTo start the game click start game,\nthen type in the difficulty to continue.\nGood Luck, you will need it.");
    }
    
    public void updateScore(){//method to update the score
        lblWins.setText(mainG.getWins() + "");//sets the label = to the total wins in the gameplay class
        lblTies.setText(mainG.getTies() + "");//sets the label = to the total ties in the gameplay class
        lblLosses.setText(mainG.getLosses() + "");//sets the label = to the total losses in the gameplay class
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}






//{p,r,s,p,r,s,p,r,s,p,r,s,p,r,s,p,r,s}