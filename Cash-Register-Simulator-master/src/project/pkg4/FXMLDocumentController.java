/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author salasrodriguez1089
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label lblAdded,lblSub,lblTotal,lblChange,lblTax;//labels where the respective value will be displayed
    
    public final static double HUNDRED_VALUE = 100.0, TWENTY_VALUE = 20.0, TEN_VALUE = 10.0, FIVE_VALUE = 5.0, ONE_VALUE = 1.0,//constants for the dollar values
                               QUARTER_VALUE = 0.25, DIMES_VALUE = 0.10, NICKELS_VALUE = 0.05, PENNIES_VALUE = 0.01;
    private double subTotal;//instance field for the sub total
    @FXML
    private Button btnCell,btnComp,btnMonitor,btnCharger,btnPay,btnTotal,btnAgain;//buttons to manipulate their visability and being enabled
    
    public FXMLDocumentController(){//constructor to introduce the project
        JOptionPane.showMessageDialog(null, "Welcome to Milky Way Market!\nHere we sell only 4 products.");
    }
    
    @FXML
    private void handleCellPhone(ActionEvent event) {//button to buy a cell phone
        double cost = 400+(Math.random()*200);//random price range between 400-600
        doSubTotal(cost);//method to add amount to the sub total
    }
    
     @FXML
    private void handleComputer(ActionEvent event) {//butotn to buy a computer
        double cost = 800+(Math.random()*200);//random price range between 800-1000
        doSubTotal(cost);//method to add amount to the sub total
    }
    
     @FXML
    private void handleMonitor(ActionEvent event) {//button to buy a monitor
        double cost = 100+(Math.random()*100);//random price range between 100-200
        doSubTotal(cost);//method to add amount to the sub total
    }
    
     @FXML
    private void handleCharger(ActionEvent event) {//button to buy a charger
        double cost = 10+(Math.random()*10);//random price range between 10-20
        doSubTotal(cost);//method to add amount to the sub total
    }
    
     @FXML
    private void handlePay(ActionEvent event) {//button to finish shopping
        double paying = Double.parseDouble(JOptionPane.showInputDialog("How much are you paying?"));//asks the user for how much they are paying with
        double total = getRounded(subTotal + (subTotal*.10));//total amount due
        double change = getRounded(paying - total);//amount thats being returned
        double returnChange = change;//change variable used to display in the label
        if (paying >= total){//checks if the payer has sufficient money to pay
            double temp = change;//temp variable to keep the same value as the change and used to /
            int hundred = (int)(temp/HUNDRED_VALUE);//gets the amount of hundred $ bills needed
            change = change%HUNDRED_VALUE;//amount of change still left
            temp = change;//temp variable to keep the same value as the change and used to /
            int twenty = (int)(temp/TWENTY_VALUE);//gets the amount of twenty $ bills needed
            change = change%TWENTY_VALUE;//amount of change still left
            temp = change;//temp variable to keep the same value as the change and used to /
            int ten = (int)(temp/TEN_VALUE);//gets the amount of ten $ bills needed
            change = change%TEN_VALUE;//amount of change still left     
            temp = change;//temp variable to keep the same value as the change and used to /
            int five = (int)(temp/FIVE_VALUE);//gets the amount of five $ bills needed
            change = change%FIVE_VALUE;//amount of change still left
            temp = change;//temp variable to keep the same value as the change and used to /
            int one = (int)(temp/ONE_VALUE);//gets the amount of one $ bills needed
            change = change%ONE_VALUE;//amount of change still left
            temp = change;//temp variable to keep the same value as the change and used to /
            int quarter = (int)(temp/QUARTER_VALUE);//gets the amount of quarters needed
            change = change%QUARTER_VALUE;//amount of change still left
            temp = change;//temp variable to keep the same value as the change and used to /
            int dime = (int)(temp/DIMES_VALUE);//gets the amount of dimes needed
            change = change%DIMES_VALUE;//amount of change still left
            temp = change;//temp variable to keep the same value as the change and used to /
            int nickel = (int)(temp/NICKELS_VALUE);//gets the amount of nickels needed
            change = change%NICKELS_VALUE;//amount of change still left
            temp = change;//temp variable to keep the same value as the change and used to /
            int pennie = (int)(temp/PENNIES_VALUE);//gets the amount of pennies needed
            lblChange.setText("Your change: $" + getRounded(returnChange) + "\nHundreds: " + hundred + "\nTwenties: " + twenty + "\nTens: " + ten +
            "\nFives: " + five + "\nOnes: " + one + "\nQuarters: " + quarter + "\nDimes: " + dime + "\nNickels: " + nickel + "\nPennies: " + pennie);
            //displays the amount of certain bills and coins needed to return if any and the change in numbers
            btnCell.setDisable(true);//disables the button to make sure the user doesnt buy while he already checked out
            btnComp.setDisable(true);//disables the button to make sure the user doesnt buy while he already checked out
            btnMonitor.setDisable(true);//disables the button to make sure the user doesnt buy while he already checked out
            btnCharger.setDisable(true);//disables the button to make sure the user doesnt buy while he already checked out
            btnAgain.setDisable(false);//enables the button so it can be pressed and the user can shop again
            btnAgain.setVisible(true);//makes the button visible
            btnPay.setDisable(true);//disables the pay button so the user doesnt pay again
            JOptionPane.showMessageDialog(null, "Thanks for shopping.");//thanks the user for shopping
        }
        else{
            JOptionPane.showMessageDialog(null, "Come back when you have money.");//if the user doesnt input enough money
        }
    }
    
     @FXML
    private void handleTotal(ActionEvent event) {//mutator to display the tax and total in a label
        lblTax.setText("10%");//sets the tax % in the label
        double total = getRounded(subTotal + (subTotal*.10));//gets the total
        lblTotal.setText("$" + total); //displays the total in a label
        btnPay.setDisable(false);//enables the pay button so the user can check out
        btnTotal.setDisable(true);//disables the total button
    }
    
    public void doSubTotal(double amount){//method to add amount to the sub total
        subTotal += getRounded(amount);//ads the amount to the sub total
        lblSub.setText("$" + getRounded(subTotal));//displays the subtotal in a label
        lblAdded.setText("$" + getRounded(amount));//displays the amount added or price of the item in a label
        lblTax.setText(null);//sets the tax to null
        lblTotal.setText(null);//sets the total to null
        btnTotal.setDisable(false);//enables the total button
        btnPay.setDisable(true);//disables the pay button
    }
    
    public double getRounded(double round){//accessor to round a double
        round *= 100;//multiplies the double by 100
        round = Math.round(round);//rounds to the ones place
        round /= 100;//divides the double by 100
        return round;//returns the rounded amount
    }
    
    @FXML
    private void handleAgain(ActionEvent event) {//reset button
        subTotal = 0;//sets the subtotal = 0
        lblAdded.setText(null);//sets label to display no numbers
        lblSub.setText(null);//sets label to display no numbers
        lblTotal.setText(null);//sets label to display no numbers
        lblChange.setText(null);//sets label to display no numbers
        lblTax.setText(null);//sets label to display no numbers
        btnCell.setDisable(false);//enables button to by the product
        btnComp.setDisable(false);//enables button to by the product
        btnMonitor.setDisable(false);//enables button to by the product
        btnCharger.setDisable(false);//enables button to by the product
        btnAgain.setDisable(true);//disables this button
        btnAgain.setVisible(false);//makes this button not visible
    }
            
            
            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
