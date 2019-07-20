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
public class Triangle {
    
    public Triangle(){//constructor for the class    
    }
    
    public double getDistance(int x_1, int y_1, int x_2, int y_2){//method to get the distance between 2 points
        return Math.sqrt((Math.pow((x_2 - x_1), 2)) + (Math.pow((y_2-y_1), 2)));//returns distance 
    }
    
    public String getType(int x_1, int y_1, int x_2, int y_2,int x_3, int y_3){//method to get the type of triangle it is
        int x1 = x_1;//sets x1
        int y1 = y_1;//sets y1
        int x2 = x_2;//sets x2
        int y2 = y_2;//sets y2
        int x3 = x_3;//sets x3
        int y3 = y_3;//sets y3
        boolean rightTri = false;//boolean for a right triangle
        boolean equilateral = false;//boolean for an =lateral triangle
        boolean scalene = false;//boolean for a scalene triangle
        boolean isosceles = false;//boolean for an isosceles triangle
        double segAB = getDistance(x1,y1,x2,y2);//sets segAB to the distance between A and B
        double segBC = getDistance(x2,y2,x3,y3);//sets segBC to the distance between B and C
        double segCA = getDistance(x3,y3,x1,y1);//sets segCA to the distance between C and A
        double base = 0,ht = 0,hyp = 0,side = 0;//values for the base, height, hypotenuse, and side
        if(segAB == segBC && segBC == segCA){//checks if its an =lateral triangle
            equilateral = true;//=lateral boolean is true
            side = segAB;//all sides are =, sets 1 length to a side
        }
        else if(segAB > segBC && segAB > segCA){//checks if segAB is the longest
            hyp = segAB;//sets the hypotenuse
            base = segBC;//sets the base
            ht = segCA;//sets the height
            if(Math.pow(base, 2) + Math.pow(ht, 2) == Math.pow(hyp, 2))//checks if its a right triangle
                rightTri = true;//right triangle boolean is set to true          
            else if(segAB != segBC && segBC != segCA && segCA != segAB)//checks if its a scalene triangle
                scalene = true;//scalene tirangle boolean is set to true           
        }
        else if(segBC > segAB && segBC > segCA){//checks if segBC is the longest
            hyp = segBC;//sets the hypotenuse
            base = segCA;//sets the base
            ht = segAB;//sets the height
            if(Math.pow(base, 2) + Math.pow(ht, 2) == Math.pow(hyp, 2))//checks if its a right triangle
                rightTri = true;//right triangle boolean is set to true
            else if(segAB != segBC && segBC != segCA && segCA != segAB)//checks if its a scalene triangle
                scalene = true;//scalene tirangle boolean is set to true  
        }
        else if(segCA > segAB && segCA > segBC){//checks if segCA is the longest
            hyp = segCA;//sets the hypotenuse
            base = segAB;//sets the base
            ht = segBC;//sets the height
            if(Math.pow(base, 2) + Math.pow(ht, 2) == Math.pow(hyp, 2))//checks if its a right triangle
                rightTri = true;//right triangle boolean is set to true
            else if(segAB != segBC && segBC != segCA && segCA != segAB)//checks if its a scalene triangle
                scalene = true;//scalene tirangle boolean is set to true  
        }
        else{
            isosceles = true;//isosceles boolean is set to true
        }

        if(equilateral){//checks for =lateral boolean
            double temp = (Math.pow(side,2)*Math.sqrt(3))/4.0;//gets the area of the =lateral triangle
            return "Type: Equalateral\nArea: " + getRounded(temp) + "\nPerimeter: " + getRounded(getPerim(side, side, side));//returns the type, area, and perimeter
            
        }
        else if (rightTri){//checks for right triangle boolean
            Double temp = (base*ht)/2.0;//gets the area of the right triangle
            return "Type: Right Triangle\nArea: " + getRounded(temp) + "\nPerimeter: " + getRounded(getPerim(base,ht,hyp));//returns the type, area, and perimeter
        }
        else if(scalene)//checks fot the scalene boolean
            return "Type: Scalene" + "\nPerimeter: " + getRounded(getPerim(segAB,segBC,segCA)) + "\n" + getAnType(segAB,segBC,segCA);//returns the type, perimeter, and angle type
        else//isosceles must be true for this to execute
            return "Type: Isosceles" + "\nPerimeter: " + getRounded(getPerim(segAB,segBC,segCA)) + "\n" + getAnType(segAB,segBC,segCA);//returns the type, perimeter, and angle type
    }
    
    public String getAnType(double side1, double side2, double side3){//method to get the type of angles
        int numAc = 0, numOb = 0;//integers for keeping track of # of acute and obtuse angles
        if(Math.pow(side1, 2) + Math.pow(side2, 2) > Math.pow(side3, 2))//checks if the first angle is acute
            numAc++;//adds 1 to acute count
        else//not acute
            numOb++;//adds 1 to obtuse count
        if(Math.pow(side2, 2) + Math.pow(side3, 2) > Math.pow(side1, 2))//checks if the second angle is acute
            numAc++;//adds 1 to acute count
        else//not acute
            numOb++;//adds 1 to obtuse count
        if(Math.pow(side3, 2) + Math.pow(side1, 2) > Math.pow(side2, 2))//checks if the third angle is acute
            numAc++;//adds 1 to acute count
        else//not acute
            numOb++;//adds 1 to obtuse count
        return "Angles: " + numAc + " acute and " + numOb + " obtuse";//returns # of acute and obtuse angles there are
    }
    
    public double getPerim(double side1,double side2, double side3){//method to get the perimeter of the triangle
        double temp = side1 + side2 + side3;//gets the sum of all the sides
        return temp;//returns the perimeter
    }
    
    public double getRounded(double roundThis){//method to round double to the nearest tenth
        roundThis *= 10;//multiplies the value by 10
        roundThis = Math.round(roundThis);//rounds to the ones place
        roundThis /= 10;//divides the value by 10
        return roundThis;//returns rounded answer
    }
    
}
