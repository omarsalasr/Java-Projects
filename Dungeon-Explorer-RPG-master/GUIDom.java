/*
*   Author: Dominykas Sipelis
*   netid:  dsipel2
*   
*/

import java.io.*;
import java.util.*;
import java.lang.String;
import javax.swing.*;

import sun.awt.WindowClosingListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class GUIDom extends JFrame implements UserInterface {

    private String name;
    private JPanel panel;
    private IO io;
    private JTextArea info;
    private JTextArea output;
    private JTextField input;
    private JPanel commands;
    private String inputString;
    private boolean hasInput;

    GUIDom(String name, IO io){
        this.io = io;
        this.name = name;
        //set frame info
        setTitle(name);
        setSize(1000,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Menu selection for GUI switching
        JMenuBar menu = new JMenuBar();
        JMenu GUISwitch = new JMenu("GUI Selection");
        menu.add(GUISwitch);
        JMenuItem textGUI = new JMenuItem("Text");
        textGUI.addActionListener(new selectText());
        JMenuItem omarGUI = new JMenuItem("Omar");
        omarGUI.addActionListener(new selectOmar());
        JMenuItem domGUI = new JMenuItem("Dominykas");
        domGUI.addActionListener(new selectDom());
        JMenuItem rahulGUI = new JMenuItem("Rahul");
        rahulGUI.addActionListener(new selectRahul());
        GUISwitch.add(textGUI);
        GUISwitch.add(omarGUI);
        GUISwitch.add(domGUI);
        GUISwitch.add(rahulGUI);
        add(menu,BorderLayout.NORTH);
        //main grid bag layout
        panel = new JPanel(new GridBagLayout());
        add(panel);
        GridBagConstraints c = new GridBagConstraints();

        //add info text area
        info = new JTextArea();
        info.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.25;
        c.weighty = .75;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(info, c);
        displayInfo();

        //add output area
        output = new JTextArea();
        output.setEditable(false);
        c.weightx = .75;
        c.weighty = .75;
        c.gridx = 1;
        panel.add(output, c);

        //add button command area
        commands = new JPanel(new GridLayout(2,3));
        c.weightx = .25;
        c.weighty = .25;
        c.gridx = 0;
        c.gridy = 1;
        addCommandButtons();
        panel.add(commands,c);

        //add input area
        input = new JTextField();
        c.weightx = .75;
        c.gridx = 1;
        input.addActionListener(new txtActionListener());
        panel.add(input,c);

    }

    @Override
    public void display(String str){
        output.append(str);
        displayInfo();
    }

    @Override
    public String getLine(){
        while(true){
            //check for input every second
            try{
                TimeUnit.SECONDS.sleep(1);
            }
            catch(InterruptedException ie){

            }
            if(hasInput == true){
                hasInput = false;
                return inputString;
            }
        }
    }

    class txtActionListener implements ActionListener{
        //execute action listener whenever enter key is pressed
        public void actionPerformed(ActionEvent e){
            String txt = input.getText();
            inputString = txt;
            //clear output field
            output.setText("");
            //reset input field
            input.setText("");
            hasInput = true;
        }
    }

    //helper function to make command buttons in lower left panel
    private void addCommandButtons(){
        JButton go = new JButton("GO");
        go.addActionListener(new buttonGo());
        go.setFocusable(false);
        JButton get = new JButton("GET");
        get.addActionListener(new buttonGet());
        get.setFocusable(false);
        JButton use = new JButton("USE");
        use.addActionListener(new buttonUse());
        use.setFocusable(false);
        JButton drop = new JButton("DROP");
        drop.addActionListener(new buttonDrop());
        drop.setFocusable(false);
        JButton attack = new JButton("ATTACK");
        attack.addActionListener(new buttonAttack());
        attack.setFocusable(false);
        JButton equip = new JButton("EQUIP");
        equip.addActionListener(new buttonEquip());
        equip.setFocusable(false);

        commands.add(go);
        commands.add(attack);
        commands.add(equip);
        commands.add(get);
        commands.add(use);
        commands.add(drop);
    }

    //helper function to clear info field and redisplay
    private void displayInfo(){
        Character c = Character.getCharacterByName(name);
        info.setText("");
        info.append("Health: "+c.getHealth()+" / "+c.getMaxhealth()+"\n");
        info.append("Strength: "+c.getStrength()+"\n\n");
        info.append("Inventory: \n");
        ArrayList<Artifact> inventory = c.getInventory();
        for(int i=0;i<inventory.size();i++){
            Artifact item = inventory.get(i);
            info.append(item.name() + "\n");
            info.append(item.description() + "\n");
        }
    }

    public void dis(){
        this.dispose();
    }

    class selectRahul implements ActionListener{
        public void actionPerformed(ActionEvent e){
            inputString = "go";
            hasInput = true;
            dis();
            io.selectInterface(3);
        }
    }

    class selectText implements ActionListener{
        public void actionPerformed(ActionEvent e){
            inputString = "go";
            hasInput = true;
            dis();
            io.selectInterface(0);
        }
    }

    class selectOmar implements ActionListener{
        public void actionPerformed(ActionEvent e){
            inputString = "go";
            hasInput = true;
            dis();
            io.selectInterface(1);
        }
    }

    class selectDom implements ActionListener{
        public void actionPerformed(ActionEvent e){
            inputString = "go";
            hasInput = true;
            dis();
            io.selectInterface(2);
        }
    }

    class buttonGo implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input.setText("go ");
        }
    }

    class buttonAttack implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input.setText("attack ");
        }
    }

    class buttonEquip implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input.setText("equip ");
        }
    }

    class buttonGet implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input.setText("get ");
        }
    }

    class buttonUse implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input.setText("use ");
        }
    }

    class buttonDrop implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input.setText("drop ");
        }
    }

}