/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.quiz.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author salasrodriguez1089
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        int numRight = 0;
        int numWrong = 0;
        
        String resp = JOptionPane.showInputDialog("Who was the first US President?");
        if(resp.equalsIgnoreCase("george washington") || resp.equalsIgnoreCase("washington")){
            System.out.println("You are Correct");
            numRight ++;
        }
        else{
            System.out.println("You are Incorrect");
            numWrong++;
        }
        
        resp = JOptionPane.showInputDialog("What is the last name of the teacher?");
        if(resp.equalsIgnoreCase("Cortez")){
            System.out.println("You are Correct");
            numRight ++;
        }
        else{
            System.out.println("You are Incorrect");
            numWrong++;
        }
        
        resp = JOptionPane.showInputDialog("What planet are we on?");
        if(resp.equalsIgnoreCase("earth")){
            System.out.println("You are Correct");
            numRight ++;
        }
        else{
            System.out.println("You are Incorrect");
            numWrong++;
        }
        
        resp = JOptionPane.showInputDialog("Whats the name of this High School?");
        if(resp.equalsIgnoreCase("Conant")){
            System.out.println("You are Correct");
            numRight ++;
        }
        else{
            System.out.println("You are Incorrect");
            numWrong++;
        }
        
        resp = JOptionPane.showInputDialog("How many Stars does the US Flag have?");
        int numResp = Integer.parseInt(resp);
        if(resp.equalsIgnoreCase("50")){
            System.out.println("You are Correct");
            numRight ++;
        }
        else{
            System.out.println("You are Incorrect");
            numWrong++;
        }
        
        label.setText("Correct: " + numRight + "   Incorrect: " + numWrong);   
        System.out.println("Thank you for playing!");     

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
