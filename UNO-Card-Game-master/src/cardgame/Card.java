/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author R2D2
 */
public class Card {//card class
    private int number;//int for the number of the card
    private String color;//string for the color
    private String path;//string for the path name
    
    public Card(){//constructor with no parameters
        setInfo();
    }
    
    public Card(int number, String color){//constructor with parameters
        this.color = color;//sets the color to whatever the param is
        this.number = number;//sets the number
        name = color + number;//makes up the name
        path = "resources/" + color + number + ".png";//creates the path name
    }
    
    public void setColor(){//choses a random color with different percentages
        int temp = (int) (1+Math.random()*100);//100-79 R, 78-57 G, 56-35 B, 34-13 Y, 12-1 W
        if(temp <= 100 && temp >= 79)
            color = "R";
        else if(temp < 79 && temp >= 57)
            color = "G";
        else if(temp < 57 && temp >= 35)
            color = "B";
        else if(temp < 35 && temp >= 13)
            color = "Y";
        else
            color = "W";       
    }
    
    public void setNumber(){//gives the card a random number
        if(color.equalsIgnoreCase("W"))
            number = (int) (Math.random()*2);
        else
            number = (int) (Math.random()*13);
    }
    
    private String name;//String for the nme use to debug
     
    public void setInfo(){//method to config the card
        setColor();//sets color
        setNumber();//sets the number
        name = color + number;//sets the name
        path = "resources/" + color + number + ".png";//sets the path name
    }
    
    public String getColor(){//returns the color
        return color;
    }
    
    public int getNumber(){//returns the number
        return number;
    }

    public String getPath(){//returns the path name
        return path;
    }
    
    @Override
    public String toString(){//overriden method to debug the project
        return name;
    }
    
}
