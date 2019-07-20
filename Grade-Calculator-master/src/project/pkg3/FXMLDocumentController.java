/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author omar
 */
public class FXMLDocumentController implements Initializable {  
    private double testWeight, quizWeight, hwWeight, testAvr, quizAvr, hwAvr, tTest, tQuizes, tHW;
    //variables for the test,quizes, and homework weights, averages, and total amount.
    private boolean weightSet, nameSet;//booleans to make sure the weights and name have been set
    @FXML
    private Label lblTest, lblQuiz, lblHW, lblGrade, lblName;
   //labels to display the information
    
    
    public FXMLDocumentController(){//constructor to introduces the project
        JOptionPane.showMessageDialog(null, "Welcome to my grading project. \n You will input values when asked to. \n Press the \"Start\" button to start.");
    }
    
    @FXML
    private void handleStart(ActionEvent event) {//method to start the project, cant continue without clicking it
        if (!nameSet){//checks if the name have not been set
            String name = JOptionPane.showInputDialog("What is your name?");//asks the user for their name
            String course = JOptionPane.showInputDialog("What class is this for?");//asks the user for the subject this is for
            lblName.setText(name + "'s " + "current grade in " + course + ".");//displays the users name and subject
            nameSet = true;//sets the nameSet to true so they dont have to input it again
        }
        if (!weightSet){//checks if the weights have not been set
            double temp = Double.parseDouble(JOptionPane.showInputDialog("What is the Test weight?"));//asks the user for the test weight
            testWeight = temp/100.0;//converts the weight to a decimal
            temp = Double.parseDouble(JOptionPane.showInputDialog("What is the Quiz weight?"));//asks the user for the quiz weight
            quizWeight = temp/100.0;////converts the weight to a decimal
            temp = Double.parseDouble(JOptionPane.showInputDialog("What is the Home Work weight?"));//asks the user for the home work weight
            hwWeight = temp/100.0;//converts the weight to a decimal
            if (testWeight + quizWeight + hwWeight > 1.0){//checks if the weights are over 1 or %100
                JOptionPane.showMessageDialog(null, "Your weights are too high.");//outputs that the weights are over 1 or 100%
            }
            else if ((int)(testWeight + quizWeight + hwWeight+.00005) == 1.0){//checks if the weights are = 1
                weightSet = true;   //weights are set
            }
            else{
                JOptionPane.showMessageDialog(null, "Your weights don't add up to 100%");//outputs that the weights are under 1 or 100%
            }
        }
        else if (weightSet){//checks if the weights have been set
            JOptionPane.showMessageDialog(null, "Restart the program to start over.");//outputs that the user must restart the program to set the weights again
        }
        
    }
    
    @FXML
    private void handleTestScores(ActionEvent event) {//button to add a test score
        if (weightSet){//checks if the weights have been set
            double score = Double.parseDouble(JOptionPane.showInputDialog("What was your score?"));//converts the score from String to double that the user inputed
            tTest ++;//adds 1 to the total test taken
            calcTest(score);//calculates the test average
            lblGrade.setText("Your current grade is: " + calcGrade(getRound(getPer())) + " with a " + getRound(getPer()) + "%");//displays the current letter grade and % in class
        }
        else{//if weights arent set, they cant put in a value
            JOptionPane.showMessageDialog(null, "Your weights don't add up to 100%");
        }
    }
    private double tTestS;//total test scores
    public void calcTest(double points){//method to calculate the test average
        tTestS += points;//adds the new score to the total test score
        testAvr = tTestS/tTest;//gets the average of the test
        lblTest.setText("Test Average is: " + calcGrade(testAvr) + " with a " + getRound(testAvr) + "%");//displays the test average with letter and %
    }
    
    @FXML
    private void handleQuizScores(ActionEvent event) {//button to add quiz score
        if (weightSet){//checks if the weights have been set
            double score = Double.parseDouble(JOptionPane.showInputDialog("What was your score?"));//converts the score from String to double that the user inputed    
            tQuizes ++;//adds 1 to the total quizes taken
            calcQuizes(score);//calculates the quiz average
            lblGrade.setText("Your current grade is: " + calcGrade(getRound(getPer())) + " with a " + getRound(getPer()) + "%");//displays the current letter grade and % in class
        }
        else{//if weights arent set, they cant put in a value
            JOptionPane.showMessageDialog(null, "Your weights don't add up to 100%");
        }
    }
    private double tQuizS;//total quiz scores
    public void calcQuizes(double points){//method to calculate the test average
        tQuizS += points;//adds the new score to the total quiz score
        quizAvr = tQuizS/tQuizes;//gets the average of the quizes
        lblQuiz.setText("Quiz Average is: " + calcGrade(quizAvr) + " with a " + getRound(quizAvr) + "%");//displays the quiz average with letter and %
    }
    
    @FXML
    private void handleHWScores(ActionEvent event) {//button to add home work score
        if (weightSet){//checks if the weights have been set
            double score = Double.parseDouble(JOptionPane.showInputDialog("What was your score?"));    //converts the score from String to double that the user inputed
            tHW ++;//adds 1 to the total home work done
            calcHW(score);//calculates the quiz average
            lblGrade.setText("Your current grade is: " + calcGrade(getRound(getPer())) + " with a " + getRound(getPer()) + "%");//displays the current letter grade and % in class
        }
        else{//if weights arent set, they cant put in a value
                JOptionPane.showMessageDialog(null, "Your weights don't add up to 100%");
        }
    }
    
    private double tHWS;//total home work scores
    public void calcHW(double points){//method to calculate the test average
        tHWS += points;//adds the new score to the total quiz score
        hwAvr = tHWS/tHW;//gets the average of the home works
        lblHW.setText("Homework Average is: " + calcGrade(hwAvr) + " with a " + getRound(hwAvr) + "%");//displays the home work average with letter and %
    }
    
    public String calcGrade(double perc){//gets the letter grade for the percentage
        if (perc >= 90){//checks if its over 90
            
            return "A";//returns A
        }
        else if (perc >= 80){//checks if its over 80
            
            return "B";//returns B
        }
        else if (perc >= 70){//checks if its over 70
            
            return "C";//returns C
        }
        else if (perc >= 60){//checks if its over 60
            
            return "D";//returns D
        }
        else if (perc >= 50){//checks if its over 50
            
            return "F";//returns F
        }
        else{//checks if its below an F
            return "BAD!";//returns BAD
        }
    }
    
    public double getPer(){//gets the percentage for the class
        return testWeight * testAvr + quizWeight * quizAvr + hwWeight * hwAvr;
    }
    
    public double getRound(double perc){//method to round to the nearest tenth
        double temp = (perc) * 100;//multiplies the percentage by 100
        temp = Math.round(temp);//rounds to the nearest ones
        temp /= 100;//divides the percentage by 100
        return temp;//returns rounded precentage
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
