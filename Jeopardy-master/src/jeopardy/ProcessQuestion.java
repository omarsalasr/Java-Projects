/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardy;

import javax.swing.JOptionPane;

/**
 *
 * @author rcortez
 */
public class ProcessQuestion 
{
    
    private String quest, ans;
    private int id;
    
    
    public ProcessQuestion(String q, String a, int i){
        quest = q;
        ans = a;
        id = i;
    }
   
    public String getQuest(){
        return quest;
    }
    
    public String getAns(){
        return ans;
    }
    
    public int getID(){
        return id;
    }
    
    public void setID(int i){
        id = i;
    }
}
