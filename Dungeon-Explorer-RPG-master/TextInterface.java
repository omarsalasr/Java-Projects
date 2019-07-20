/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
* Written by Omar Salas-Rodriguez, osalas3, CS 342 Fall 2018
*
* Description: 
*
*/
import java.io.*;
import java.util.*;
import java.lang.String;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class TextInterface extends JFrame implements UserInterface{

    private JTextField txtField;
    private JTextArea txtArea;
    private JScrollPane scrollP;
    private String input;
    private boolean hasInput;
    private IO io;


    public TextInterface(String name, IO io) {
        this.io = io;
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

        JScrollPane scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Output area
        txtArea = new JTextArea();
        scrollP = new JScrollPane(txtArea);
        scrollP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Input area
        txtField = new JTextField();
        txtField.addActionListener(new txtActionListener());
        
        add(menu,BorderLayout.NORTH);
        add(txtArea,BorderLayout.CENTER);
        add(txtField,BorderLayout.SOUTH);

        setTitle(name);
        setSize(1000,500);  
        setVisible(true);  
        input = "";
        getContentPane().add(scrollP);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void display(String str){
        txtArea.append(str);
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
                return input;
            }
        }
    }

    class txtActionListener implements ActionListener{
        //execute action listener whenever enter key is pressed
        public void actionPerformed(ActionEvent e){
            String txt = txtField.getText();
            input = txt;
            //add command in output
            txtArea.append(txt + "\n");
            //reset input field
            txtField.setText("");
            hasInput = true;
        }
    }

    public void dis(){
        this.dispose();
    }

    class selectRahul implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input = "go";
            hasInput = true;
            dis();
            io.selectInterface(3);
        }
    }

    class selectText implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input = "go";
            hasInput = true;
            dis();
            io.selectInterface(0);
        }
    }

    class selectOmar implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input = "go";
            hasInput = true;
            dis();
            io.selectInterface(1);
        }
    }

    class selectDom implements ActionListener{
        public void actionPerformed(ActionEvent e){
            input = "go";
            hasInput = true;
            dis();
            io.selectInterface(2);
        }
    }

}
