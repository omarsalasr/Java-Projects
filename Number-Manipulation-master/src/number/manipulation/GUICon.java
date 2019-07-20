/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package number.manipulation;

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
    
    @FXML
    private void handlePrimeNum(ActionEvent event) {
        getPrimeNum();//method to get the prime numbers
    }
    
    public GUICon(){//construcor for the program
        JOptionPane.showMessageDialog(null, "Welcome to my number manipulation project.\nClick on any of the buttons to do the\ntask displayed in the button.");
    }//message to tell the user what to do
    
    public void getPrimeNum(){//method to get the prime numbers
        int numTimes = Integer.parseInt(JOptionPane.showInputDialog("How many prime numbers do you want to display?"));//asks the user for how many prime numbers they want
        boolean primeNum;//boolean to check if they are prime
        int count = 1;//integer to check how many have been displayed
        int numCheck = 3;//integer to check for non prime 
        System.out.println(2);//outputs 2 becuase that is the first prime number
        while(count<numTimes){//loop to check if the right amount of prime numbers have been displayed
            primeNum = true;//boolean set to true
            for(int i = 2;i<numCheck;i++){//loop to check for non prime numbers
                if(numCheck %i == 0)//condition to check the remainder
                    primeNum = false;//boolean for prime numbers      
            }
            if(primeNum){//condition if the boolean is true
                System.out.println(numCheck);//outputs the prime numbers
                count ++;//integer counter how many ouputted ++
            }
            numCheck++;//number to check for non prime
        }
    }
    
    
    @FXML
    private void handleFactors(ActionEvent event) {
        getFactors();//method for getting the factors of a number
    }
    
    public void getFactors(){//method for getting the factors of a number
        int num = Integer.parseInt(JOptionPane.showInputDialog("What number do you want the factors of?"));//asks the user the number they want the factors of
        for(int i = 1; i<=(int) Math.sqrt(num) ;i++){//loop to print out only 1 set of the factors
            if(num%i==0){//checks if the number is a factor
                int temp = num/i;//puts the result into a temp variable
                System.out.println(i + ", " + temp);//outputs the set of factors
            }
        } 
    }
    
    @FXML
    private void handleConversion(ActionEvent event) {
        numInput();//method for getting the input for conversion
    }
    
    public void numInput(){//method to get the the data to convert
        boolean valid = false;//boolean to check if the user has inputted a valid 
        int convBase = 0;//integer for the base the number is in
        String convNum = "";//String to store the number being converted
        do{//loop to check if the user input a valid number
            valid = true;//boolean to make it true before asked to input data
            convNum = JOptionPane.showInputDialog("What number do you want to convert?");//asks the user the number they want to convert
            convBase = Integer.parseInt(JOptionPane.showInputDialog("What base is the number in?"));//asks the user for the base in which the number is in
            for (int i = 0; i<convNum.length();i++){//loop to get each individual character of the number being converted
                int temp = Integer.parseInt(convNum.substring(i, i+1));//gets the character
                if(temp >= convBase)//condition to check if the character is bigger than the base
                    valid = false;//makes the input invalid          
            }
            if(!valid)//condision to check if valid is false
                System.out.println("Invalid input.\n");//outputs that they input an invalid input
        }
        while(!valid);//loop to check if the input is invalid
        int newConvBase = Integer.parseInt(JOptionPane.showInputDialog("What base do you want to convert the number to?"));//asks the user fir the base they want to convert to
        if(convBase == 10)//condition to check if the number they inputted is in base 10
            System.out.println(getConversion(convNum, convBase, newConvBase));//outputs the converted number
        else{//if the number is not in base 10
            convNum = getBase10(convNum, convBase) + "";//converts the number into basae 10 first
            System.out.println(getConversion(convNum, convBase, newConvBase));//outputs the converted number
        }
    }
    
    public int getBase10(String num, int convBase){//method to convert a number into base 10
        int convNum = 0;//variable to store the converted number
        for(int i = 0; i<num.length(); i++){//loop until the number has been converted
            String oneChar = num.substring(i, i+1);//string to get each individual character in the unconverted number
            int temp = Integer.parseInt(oneChar);//temp variable to make the character into an integer
            int exp = (num.length()-1)-i;//gets the exp to which the power is being raised to
            convNum = convNum + (int) (temp*Math.pow(convBase, exp));//gets the converted number
        }
        return convNum;//returns the converted number
    }
    
    public String getConversion(String convNum, int convBase, int newConvBase){//method to convert a number into a different base that is not 10
            int num = Integer.parseInt(convNum);//makes the unconverted number into an integer
            String newNum = "";//string to store the converted number
            while(num>1){//loop until the unconverted number is less than 2
                newNum = num%newConvBase + newNum;//adds the remainder to the string
                num = num/newConvBase;//divides int by int to get an int
                if(num/newConvBase < 2){//condition to check if the result is less than 2
                   newNum =  num%newConvBase + newNum;//adds the remainder to the string
                  num = num/newConvBase;//divides int by int to get an int
                 newNum = num%newConvBase + newNum;//adds the remainder to the string
                }   
            }
            int temp = newNum.indexOf("0");//temp variable to get the index number of the character 0
            if(temp == 0)//condition to check if the index is 0
                newNum = newNum.substring(1, newNum.length());//removes the first character
            return newNum;//returns the converted number
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
