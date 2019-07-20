/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: GUI class that enhances the TextInterface GUI by introducing clickable buttons
               and displaying useful information about the player like their health, strength,
               inventory.
*
*/

import java.io.*;
import java.util.*;
import java.lang.String;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GUIOmar extends JFrame implements UserInterface {

    private IO io;
    private int id;
    private String buffer;
    private boolean hasInput;
    private DefaultListModel<String> inventory;

    // Swing components
    private JTextArea txtArea;
    private JButton btnAttack;
    private JButton btnDrop;
    private JButton btnEquip;
    private JButton btnGo;
    private JButton btnGet;
    private JButton btnUse;
    private JLabel lblInventory;
    private JLabel lblHealth;
    private JLabel lblStrength;
    private JLabel lblName;
    private JLabel lblCharName;
    private JList<String> listInventory;
    

    public GUIOmar(String name, IO io, int id){
        this.io = io;
        this.id = id;
        buffer = "";
        hasInput = true;
        // Menu selection for GUI switching
        JMenuBar menu = new JMenuBar();
        JMenu GUISwitch = new JMenu("GUI Selection");
        menu.add(GUISwitch);
        
        JMenuItem textGUI = new JMenuItem("Text");
        textGUI.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                buffer = "";
                hasInput = true;
                dis();
                io.selectInterface(0);
            }
        }); 
        JMenuItem omarGUI = new JMenuItem("Omar");
        omarGUI.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                buffer = "go";
                hasInput = true;
                dis();
                io.selectInterface(1);
            }
        });
        JMenuItem domGUI = new JMenuItem("Dominykas");
        domGUI.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                buffer = "go";
                hasInput = true;
                dis();
                io.selectInterface(2);
            }
        });
        JMenuItem rahulGUI = new JMenuItem("Rahul");
        rahulGUI.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                buffer = "go";
                hasInput = true;
                dis();
                io.selectInterface(3);
            }
        });
        GUISwitch.add(textGUI);
        GUISwitch.add(omarGUI);
        GUISwitch.add(domGUI);
        GUISwitch.add(rahulGUI);
        add(menu,BorderLayout.NORTH);
        // GridBagLayout for organizing all the components
        GridBagLayout gbFrame = new GridBagLayout();
        GridBagConstraints gbcFrame = new GridBagConstraints();
        setLayout(gbFrame);
        // Text Area for ouput
        txtArea = new JTextArea(2,10);
        JScrollPane scpTxtArea = new JScrollPane(txtArea);
        gbcFrame.gridx = 0;
        gbcFrame.gridy = 0;
        gbcFrame.gridwidth = 18;
        gbcFrame.gridheight = 10;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.weightx = 7;
        gbcFrame.weighty = 7;
        txtArea.setEditable(false);
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbcFrame.insets = new Insets(25,0,0,0);
        gbFrame.setConstraints(scpTxtArea, gbcFrame);
        add(scpTxtArea);
        // Button attack for attacking an enemy
        btnAttack = new JButton("Attack");
        gbcFrame.gridx = 18;
        gbcFrame.gridy = 3;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbcFrame.insets = new Insets(5,5,5,5);
        gbFrame.setConstraints(btnAttack, gbcFrame);
        btnAttack.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = JOptionPane.showInputDialog("Which enemy do you want to attack?");
                    buffer = "Attack " + name.trim();
                    txtArea.append(buffer + "\n");
                    hasInput = true;
                }catch(Exception ex){
                    System.out.println("Error: Please input a non-empty name");
                }
                
            }
        });
        add(btnAttack);
        // Button drop for dropping items
        btnDrop = new JButton("Drop");
        gbcFrame.gridx = 19;
        gbcFrame.gridy = 1;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbcFrame.insets = new Insets(5,5,5,5);
        gbFrame.setConstraints(btnDrop, gbcFrame);
        btnDrop.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = JOptionPane.showInputDialog("Which item do you wish to drop?");
                    buffer = "Drop " + name.trim();
                    txtArea.append(buffer + "\n");
                    hasInput = true;
                }catch(Exception ex){
                    System.out.println("Error: Please input a non-empty item name");
                }
                
            }
        });
        add(btnDrop);
        // Button equip for equiping items
        btnEquip = new JButton("Equip");
        gbcFrame.gridx = 19;
        gbcFrame.gridy = 2;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbcFrame.insets = new Insets(5,5,5,5);
        gbFrame.setConstraints(btnEquip, gbcFrame);
        btnEquip.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = JOptionPane.showInputDialog("Which item do you wish to equip?");
                    buffer = "Equip " + name.trim();
                    txtArea.append(buffer + "\n");
                    hasInput = true;
                }catch(Exception ex){
                    System.out.println("Error: Please input a non-empty item name");
                }
                
            }
        });
        add(btnEquip);
        // Button go for traveling somwhere
        btnGo = new JButton("Travel");
        gbcFrame.gridx = 19;
        gbcFrame.gridy = 3;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.CENTER;
        gbcFrame.insets = new Insets(5,5,5,5);
        gbFrame.setConstraints( btnGo, gbcFrame );
        btnGo.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = JOptionPane.showInputDialog("Which direction do you wish to travel?");
                    buffer = "Go " + name.trim();
                    txtArea.append(buffer + "\n");
                    hasInput = true;
                }catch(Exception ex){
                    System.out.println("Error: Please input a non-empty direction");
                }
                
            }
        });
        add(btnGo);
        // Button get for picking items from the floor
        btnGet = new JButton("Get");
        gbcFrame.gridx = 18;
        gbcFrame.gridy = 1;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbcFrame.insets = new Insets(5,5,5,5);
        gbFrame.setConstraints(btnGet, gbcFrame);
        btnGet.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = JOptionPane.showInputDialog("Which item do you wish to pick up?");
                    buffer = "Get " + name.trim();
                    txtArea.append(buffer + "\n");
                    hasInput = true;
                }catch(Exception ex){
                    System.out.println("Error: Please input a non-empty item name");
                }
                
            }
        });
        add(btnGet);
        // Button use for using items (keys)
        btnUse = new JButton("Use");
        gbcFrame.gridx = 18;
        gbcFrame.gridy = 2;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbcFrame.insets = new Insets(5,5,5,5);
        gbFrame.setConstraints(btnUse, gbcFrame);
        btnUse.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = JOptionPane.showInputDialog("Which item do you wish to use?");
                    buffer = "Use " + name.trim();
                    txtArea.append(buffer + "\n");
                    hasInput = true;
                }catch(Exception ex){
                    System.out.println("Error: Please input a non-empty item name");
                }
                
            }
        });
        add(btnUse);
        // Label to display your name
        lblName = new JLabel("Name: ");
        gbcFrame.gridx = 18;
        gbcFrame.gridy = 4;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.NONE;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbFrame.setConstraints(lblName, gbcFrame);
        add(lblName);
        // Label to display character name
        lblCharName = new JLabel(Character.getCharacterByID(id).getName());
        gbcFrame.gridx = 19;
        gbcFrame.gridy = 4;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.NONE;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbFrame.setConstraints(lblCharName, gbcFrame);
        add(lblCharName);
        // Label to display your health
        lblHealth = new JLabel("Health: " + Character.getCharacterByID(id).getHealth() + "/" + Character.getCharacterByID(id).getMaxhealth());
        gbcFrame.gridx = 18;
        gbcFrame.gridy = 5;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.NONE;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbFrame.setConstraints(lblHealth, gbcFrame);
        add(lblHealth);
        // Label to display your strength
        lblStrength = new JLabel("Strength: " + Character.getCharacterByID(id).getStrength());
        gbcFrame.gridx = 19;
        gbcFrame.gridy = 5;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.NONE;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbFrame.setConstraints(lblStrength, gbcFrame);
        add(lblStrength);
        // Label for inventory
        lblInventory = new JLabel("Inventory:");
        gbcFrame.gridx = 18;
        gbcFrame.gridy = 6;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.NONE;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 1;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbFrame.setConstraints(lblInventory, gbcFrame);
        add(lblInventory);
        // List for displaying inventory items
        inventory = new DefaultListModel<>();
        listInventory = new JList<>(inventory);
        gbcFrame.gridx = 19;
        gbcFrame.gridy = 6;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.HORIZONTAL;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 1;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbFrame.setConstraints(listInventory, gbcFrame);
        add(listInventory);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(name);
        setSize(1000,500);
        setVisible(true);
    }

    @Override
    public void display(String str){
        displayInfo();
        txtArea.append(str);
    }

    // Helper function to display information like the inventory and health
    public void displayInfo(){
        ArrayList<Artifact> inv = Character.getCharacterByID(id).getInventory();
        inventory.clear();
        for(int i = 0; i < inv.size(); i++){
            inventory.addElement(inv.get(i).name());
        }
        lblHealth.setText("Health: " + Character.getCharacterByID(id).getHealth() + "/" + Character.getCharacterByID(id).getMaxhealth());
        lblStrength.setText("Strength: " + Character.getCharacterByID(id).getStrength());
    }

    @Override
    public String getLine(){
        while(true){
            try{
                TimeUnit.SECONDS.sleep(3);
            }
            catch(InterruptedException ie){

            }
            if(hasInput == true){
                hasInput = false;
                return buffer;
            }
        }
    }

    public void dis(){
        this.dispose();
    }


}