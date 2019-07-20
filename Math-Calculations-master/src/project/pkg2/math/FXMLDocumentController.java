/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg2.math;

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
    
    public FXMLDocumentController(){//intrudoces the program
        JOptionPane.showMessageDialog(null,"Welcome to my Math Formula Project! \n For every formula you will be asked to input values. \n Click any of the buttons to calculate the designated formula.");
    }
    
    private double volSphere;//variable to store the volume of a sphere
    private double areaTri;//variable to store the area of n Equilateral triangle
    private double hypot;//hypotenuse of a right triangle
    private double oppos;//opposite side of a right triangle
    private double angleMes;//angle measure
    private double a, b, c, d;//sides of a cyclic quadrilateral for Bram's formula
    private double areaQuad;//area of the quadrilateral
    private double volTetra;//volume of a tetrahedron
    
    @FXML
    private Label label1, label2, label3, label4, label5;
    
    
    @FXML
    private void handleCalcArea() {//button to find the area of an Equilateral triangle
        doEquaTri();//calls the method to get the data to find the area of an Equilateral triangle
        label1.setText("The Area is " + getRounded(areaTri));//displays the area of an Equilateral triangle
    }
    
    public void doEquaTri(){//method to get the data for a side of an Equilateral triangle
        String input = JOptionPane.showInputDialog("Input a length for a side to find the Area of an Equilateral Triangle.");//asks the user to input a side value
        double side = Double.parseDouble(input);//converts the String response into an double
        getAreaETri(side);//calls the method to calculate the area of an Equilateral triangle
    }
    
    public double getAreaETri(double sideLen){  //this method calculates the area of an Equilateral Triangle
        areaTri = (Math.pow(sideLen,2)*Math.sqrt(3))/4.0;// formula = ((s*s) * Sqrt(3))/4
        return areaTri;// returns the area
    }
    
    
    @FXML
    private void handleCalcVolume() {//button to find the volume of a sphere
        doVolumeS();//calls the method to get the data to find the volume of a sphere
        label2.setText("The Volume is: " + getRounded(volSphere));//displays the volume of a sphere
    }
    
    public void doVolumeS(){//method to get the data for the radius of a sphere
        String input = JOptionPane.showInputDialog("Input a radius to find the volume of a sphere.");//asks the user to input a radius value
        double radius = Integer.parseInt(input);//converts the String response into an double
        getVolumeSphere(radius);//calls the method to calculate the volume of a sphere
    }
    
    public double getVolumeSphere(double r){//this method calculates the Volume of a sphere
        volSphere = (4.0/3.0)*Math.PI*Math.pow(r, 3);//formula 4/3(PI)(r.cubed)
        return volSphere;//returns the Volume of a Sphere
    }  
    
    @FXML
    private void handleCalcTrig() {//button to find the inverse sine 
        doTrig();//calls the method to get the data to find the inverse sine
        label3.setText("The angle measure is: " + getRounded(getAngle()) + "°");//displays the inverse sine
    }
    
    public void doTrig(){//this method gets the data to find the inverse sine
        JOptionPane.showMessageDialog(null, "Get the angle measure of a right triangle \n using inverse sine.");//tells the user what it can find
        String input = JOptionPane.showInputDialog("Input the value of a leg of a right triangle.");//asks the user to input a value for the length of the oppoisite side
        oppos = Double.parseDouble(input);//converts the String into a double
        input = JOptionPane.showInputDialog("Input the value of the hypotenuse of a right triangle.");//asks the user to input a value for the length of the hypotenuse
        hypot = Double.parseDouble(input);//converts the String into a double
        getAngle();//calls the method to calculate the inverse sine
    }
    
    public double getAngle(){//this method calculates the inverse sine
        double sine = (oppos/hypot);//formula is opp_side/hypotenuse = sine
        angleMes = Math.toDegrees(Math.asin(sine)); //this gets the inverse sine
        return angleMes;//returns the inverse sine
    }
    
    @FXML
    private void handleCalcBrahm() {//button to find the area of a cyclic quadrilateral with Brahmagupta's formula
        doBrahm();//calls the method to get the data to find the area of a cyclic quadrilateral
        label4.setText("The Area is: " + getRounded(areaQuad));//displayes the area of a cyclic quadrilateral
    }
     
    public void doBrahm(){//this method gets the data to find the area of a cyclic quadrilateral
        JOptionPane.showMessageDialog(null, "With Brahmagupta's formula, you can find the Area of any \n cyclic quadrilateral as long as the sides are given.");// tells the user what it can find
        String input = JOptionPane.showInputDialog("Input a value for side A");//asks the user to input a value for side "A" 
        a = Integer.parseInt(input);//converts the String into a double
        input = JOptionPane.showInputDialog("Input a value for side B");//asks the user to input a value for side "B"
        b = Integer.parseInt(input);//converts the String into a double
        input = JOptionPane.showInputDialog("Input a value for side C");//asks the user to input a value for side "C"
        c = Integer.parseInt(input);//converts the String into a double
        input = JOptionPane.showInputDialog("Input a value for side D");// asks the user to input a value for side "D"
        d = Integer.parseInt(input);//converts the String into a double
        getBrahm();//calls the method to calculate the area of a cyclic quadrilateral
    }
    
    public double getBrahm(){//this method calculates the area of a cyclic quadrilateral
        double semiP = (a+b+c+d)/2.0;//semi perimeter of the cyclic quadrilateral = (sum of all sides)/2.0;
        areaQuad = Math.sqrt((semiP-a)*(semiP-b)*(semiP-c)*(semiP-d));//formula to find the area of a cyclic quadrilateral
        return areaQuad;//returns the area of a cyclic quadrilateral
    }
    
    
    @FXML
    private void handleCalcVolumeTetra() {//button to find the Volume of a TetraHedron
        doVolumeTetra();//calls the method to get the data to find the Volume of a TetraHedron
        label5.setText("The Volume is: " + getRounded(volTetra));//desplays the Volume of a TetraHedron
    }
    
    public void doVolumeTetra(){
        getVolumeTetra();//calls the method to calculate the Volume of a TetraHedron
        
    }
    
    public double getVolumeTetra(){
        String input = JOptionPane.showInputDialog("Input a length for a side to find the Volume of a Tetrahedron.");//asks the user to input a value for a side
        double radius;//radius of the base of the TetraHedron
        double side = Double.parseDouble(input);//length of a side of the TetraHedron
        double apothem;//apothem of the base of the TetraHedron
        double height;//height of the TetraHedron
        apothem = (side/2.0)*(Math.sqrt(3)/3.0);//formula to find the apothem
        radius = Math.sqrt(Math.pow(apothem, 2) + Math.pow(side/2.0, 2));//formula to find the radius
        height = Math.sqrt(Math.pow(side, 2) - Math.pow(radius, 2));//formula to find the height
        volTetra = (1.0/3.0) * getAreaETri(side) * height;//formula to find the Volume of a TetraHedron
        return volTetra;//returns the Volume of the TetraHedron
    }
    
    public double getRounded(double roundThis){//method to round double to the nearest tenth
        roundThis *= 100;//multiplies the value by 100
        roundThis = Math.round(roundThis);//rounds to the ones place
        roundThis /= 100;//divides the value by 100
        return roundThis;//returns rounded answer
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
