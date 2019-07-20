/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg5;


/**
 *
 * @author salasrodriguez1089
 */
public class Quadrilateral {
       
    public Quadrilateral() {//constructor for the class 
    }
    
    public String getType(int x_1, int y_1, int x_2, int y_2,int x_3, int y_3, int x_4, int y_4){//method to find the type of quad
        int x1 = x_1;//sets x1
        int y1 = y_1;//sets y1
        int x2 = x_2;//sets x2
        int y2 = y_2;//sets y2
        int x3 = x_3;//sets x3
        int y3 = y_3;//sets y3
        int x4 = x_4;//sets x4
        int y4 = y_4;//sets y4
        boolean rhombus = false;//boolean for a rhombus
        boolean rectangle = false;//boolean for a rectangle
        boolean trap = false;//boolean for a trapezoid
        boolean quadrilateral = false;//boolean for a quadrilateral
        String segAB = getDistance(x1,y1,x2,y2);//sets the length of segAB
        String segBC = getDistance(x2,y2,x3,y3);//sets the length of segBC
        String slopeAB = getSlope(x1,y1,x2,y2);//sets the slope of AB
        String slopeBC = getSlope(x2,y2,x3,y3);//sets the slope of BC
        String slopeCD = getSlope(x3,y3,x4,y4);//sets the slope of CD
        String slopeDA = getSlope(x4,y4,x1,y1);//sets the slope of DA

        
        if(slopeAB.equals(slopeCD) || slopeBC.equals(slopeDA)){//checks if either pair of opposite sides are parallel
            trap = true;//trapezoid boolean is set to true
            if(slopeAB.equals(slopeCD) && slopeBC.equals(slopeDA)){//checks if both pair of opposite sides are parallel
                if(segAB.equals(segBC))//checks if a side is = to the adjacent side to see if all sides are =
                    rhombus = true;//rhombus boolean is set to true
                if (slopeAB.equals(getOpSlope(x2,y2,x3,y3)))//checks if an angle is right
                    rectangle = true;//rectangle boolean is set to true
            }
        }
        else{//if its not either one of those its a quad
            quadrilateral = true;//quad boolean is set to true
        }
        
        if(rhombus && rectangle){//checks if both a rhombus and rectangle booleans are true
            double base = Double.parseDouble(getDistance(x3,y3,x4,y4));//sets the base length
            double height = Double.parseDouble(getDistance(x2,y2,x3,y3));//sets the height length
            return "Type: Square\nArea: " + getRounded((base*height));//returns the type and the area
        }
        if (rhombus){//checks for the rhombus boolean
            double p = Double.parseDouble(getDistance(x2,y2,x4,y4));//sets the first diagonal length
            double q = Double.parseDouble(getDistance(x1,y1,x3,y3));//sets the second diagonal length
            return "Type: Rhombus\nArea: " + getRounded(((p*q)/2.0));//returns the type and the area
        }
        else if (rectangle){//checks for the rectangle boolean
            double base = Double.parseDouble(getDistance(x3,y3,x4,y4));//sets the base length
            double height = Double.parseDouble(getDistance(x2,y2,x3,y3));//sets the height length
            return "Type: Rectangle\nArea: " + getRounded((base*height));//returns the type and area
        }
        else if (quadrilateral)//checks fot the quad boolean
            return "Type: Quadrilateral";//returns the type
        else//last type is a trapezoid
            return "Type: Trapezoid";//returns the type
    }
    
    public String getDistance(int x_1, int y_1, int x_2, int y_2){//method to get the distance between 2 points
        return "" + Math.sqrt((Math.pow((x_2 - x_1), 2)) + (Math.pow((y_2-y_1), 2)));//returns distance 
    }
    
    public String getSlope(int x_1, int y_1, int x_2, int y_2){//method to get the slope of a line
        
        double temp;//temp variable
        if (y_2-y_1 == 0){//checks if the slope is 0
            return "0";//returns 0
        }
        else if (x_2-x_1 == 0){//checks if the slope is undefined
            return "undefined";//returns undefined
        }
        else//does the regular formula
            temp = (y_2-y_1)/(x_2-x_1);//gets the slope
            return temp + "";//returns the slope
    } 
    
    public String getOpSlope(int x_1, int y_1, int x_2, int y_2){//method to get the opposite slope of a line
        
        double temp;//temp variable
        if (y_2-y_1 == 0){//checks if the slope is 0
            return "undefined";//returns the opposite slope of 0
        }
        else if (x_2-x_1 == 0){//checks if the slope is undefined
            return "0";//returns the opposite slope of undefined
        }
        else//does the regular formula
            temp = -1.0/((y_2-y_1)/(x_2-x_1));//gets the opposite slope
            return temp + "";//returns the opposite slope
    } 
          
    public double getRounded(double roundThis){//method to round double to the nearest hundreth
        roundThis *= 10;//multiplies the value by 10
        roundThis = Math.round(roundThis);//rounds to the ones place
        roundThis /= 10;//divides the value by 10
        return roundThis;//returns rounded answer
    }
    
}

