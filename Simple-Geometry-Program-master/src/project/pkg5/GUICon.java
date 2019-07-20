/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg5;

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
    private Label lblTriangle, lblQuad;//labels for the triangle and quads
    
    public GUICon(){//introduces the program
        JOptionPane.showMessageDialog(null, "Welcome to my coordinate geometry program.\nYou must click on either button to\nfind the respective figure.\nUse (x,y) format for each input.");
    }
    
    @FXML
    private void handleTriangle(ActionEvent event) {
        setCoorTri();//button to call the setCoorTri method
    }
    
    public void setCoorTri(){//gets the coordinates for a triangle
        String pointA = JOptionPane.showInputDialog("What are the coordinates for point A?");//JOP to get point A
        int comma = pointA.indexOf(",");//gets the index of the ,
        int x1 = Integer.parseInt(pointA.substring(1,comma));//gets the x value of point A
        int y1 = Integer.parseInt(pointA.substring(comma+1,pointA.length()-1));//gets the y value of point A
        String pointB = JOptionPane.showInputDialog("What are the coordinates for point B?");//JOP to get point B
        int comma2 = pointB.indexOf(",");//gets the index of the ,
        int x2 = Integer.parseInt(pointB.substring(1,comma2));//gets the x value of point B
        int y2 = Integer.parseInt(pointB.substring(comma2+1,pointB.length()-1));//gets the y value of point B
        String pointC = JOptionPane.showInputDialog("What are the coordinates for point C?");//JOP to get point C
        int comma3 = pointC.indexOf(",");//gets the index of the ,
        int x3 = Integer.parseInt(pointC.substring(1,comma3));//gets the x value of point C
        int y3 = Integer.parseInt(pointC.substring(comma3+1,pointC.length()-1));//gets the y value of point C
        Triangle tri = new Triangle();//makes in instance of the class Triangle
        lblTriangle.setText(tri.getType(x1,y1,x2,y2,x3,y3));//puts the information from the class in a label
    }
    @FXML
    private void handleQuad(ActionEvent event) {
        setCoorQuad();//button to call the setCoorQuad method
    }
    
    public void setCoorQuad(){//gets the coordinates for a quad
        String pointA = JOptionPane.showInputDialog("What are the coordinates for point A?");//JOP to get point A
        int comma = pointA.indexOf(",");//gets the index of the ,
        int x1 = Integer.parseInt(pointA.substring(1,comma));//gets the x value of point A
        int y1 = Integer.parseInt(pointA.substring(comma+1,pointA.length()-1));//gets the y value of point A
        String pointB = JOptionPane.showInputDialog("What are the coordinates for point B?");//JOP to get point B
        int comma2 = pointB.indexOf(",");//gets the index of the ,
        int x2 = Integer.parseInt(pointB.substring(1,comma2));//gets the x value of point B
        int y2 = Integer.parseInt(pointB.substring(comma2+1,pointB.length()-1));//gets the y value of point B
        String pointC = JOptionPane.showInputDialog("What are the coordinates for point C?");//JOP to get point C
        int comma3 = pointC.indexOf(",");//gets the index of the ,
        int x3 = Integer.parseInt(pointC.substring(1,comma3));//gets the x value of point C
        int y3 = Integer.parseInt(pointC.substring(comma3+1,pointC.length()-1));//gets the y value of point C
        String pointD = JOptionPane.showInputDialog("What are the coordinates for point D?");//JOP to get point D
        int comma4 = pointD.indexOf(",");//gets the index of the ,
        int x4 = Integer.parseInt(pointD.substring(1,comma4));//gets the x value of point D
        int y4 = Integer.parseInt(pointD.substring(comma4+1,pointD.length()-1));//gets the y value of point D
        Quadrilateral quad = new Quadrilateral();//makes in instance of the class Quadrilateral
        lblQuad.setText(quad.getType(x1,y1,x2,y2,x3,y3,x4,y4));//puts the information from the class in a label
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
