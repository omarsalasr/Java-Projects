/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nim.game;

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
public class GUICon implements Initializable {
    
    @FXML
    private Label label;
    
    Gameplay start = new Gameplay();
    
    private GUICon(){
//        JOptionPane.showMessageDialog(null, "Welcome to the Game of Nim.\nIn here you will verse a computer.\n"
//        + "Rules: Take out from 1 marble to half the marbles.\nYou will take turns taking out marbles.\n"
//        + "The last one to take out a marble loses.\nGood luck!");
    }
 
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String diffic;
        int marb = Integer.parseInt(JOptionPane.showInputDialog("How many blocks do you want to start with?"));
        do{
        diffic = JOptionPane.showInputDialog("What difficulty do you want, easy or hard?");
        }
        while(!diffic.equalsIgnoreCase("hard") && !diffic.equalsIgnoreCase("easy"));
        start.startGame(marb,diffic);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
