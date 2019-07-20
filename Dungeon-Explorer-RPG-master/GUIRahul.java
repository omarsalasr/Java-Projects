import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.lang.String;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class GUIRahul extends JFrame implements UserInterface {

    private IO io;
    private JTextField txtField;
    private JTextArea txtArea;
    private JTextArea inveArea;
    private String input;
    private boolean hasInput;
    private String n;
    Panel p1, p2;
    JProgressBar pb;

    JButton north;
    JButton south;
    JButton east;
    JButton west;
    JButton center;
    JButton quit;
    JButton down;

    Label l1;

    public GUIRahul(String name, IO io){
        this.io = io;
        n = name;
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

        //JFrame
        GridBagLayout gbFrame = new GridBagLayout();
        GridBagConstraints gbcFrame = new GridBagConstraints();
        setLayout(gbFrame);

        txtArea = new JTextArea(2,10);
        inveArea = new JTextArea(2,10);
        l1 = new Label("Inventory: ");
        //PrintStream out = new PrintStream( new TextAreaOutputStream( txtArea ) );
        //System.setOut( out );
        //System.setErr( out );
        //System.out.println( "Hello World" );
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
        gbcFrame.insets = new Insets(60,0,0,0);
        gbFrame.setConstraints(scpTxtArea, gbcFrame);
        add(scpTxtArea);

        pb = new JProgressBar();
        UIManager.put("ProgressBar.selectionForeground", Color.red);
        UIManager.put("ProgressBar.selectionBackground", Color.green);
        pb.setForeground(Color.green);
        pb.setBackground(Color.red);
        //getContentPane().add(pb);
        pb.setString("Health: " + Character.getCharacterByName(n).health + "/100");
        pb.setStringPainted(true);
        pb.setValue(Character.getCharacterByName(n).health);
        //pb.setValue(50);
        pb.setMaximum(100);
        //pb.setBackground(Color.red);
        pb.setVisible(true);

        gbcFrame.gridx = 18;
        gbcFrame.gridy = 3;
        gbcFrame.gridwidth = 1;
        gbcFrame.gridheight = 1;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.weightx = 1;
        gbcFrame.weighty = 0;
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbcFrame.insets = new Insets(5,5,5,5);
        gbFrame.setConstraints(pb, gbcFrame);
        add(pb);

        setTitle(name);
        setSize(1000,500);
        setVisible(true);


        p1 = new Panel(new BorderLayout(5,5));
        p2 = new Panel(new BorderLayout(5,5));
        north = new JButton("North");
        p1.add(north, BorderLayout.NORTH);
        north.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = "go n";
                input = txt;
                System.out.println(input);
                //add command in output
                txtArea.append(txt + "\n");
                DisplayInve();
                //reset input field
                txtField.setText("");
                hasInput = true;
            }
        });
        south = new JButton("South");
        p1.add(south, BorderLayout.SOUTH);
        south.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = "go s";
                input = txt;
                System.out.println(input);
                //add command in output
                txtArea.append(txt + "\n");
                DisplayInve();
                //reset input field
                txtField.setText("");
                hasInput = true;
            }
        });
        east = new JButton("East");
        p1.add(east, BorderLayout.EAST);
        east.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = "go e";
                input = txt;
                System.out.println(input);
                //add command in output
                txtArea.append(txt + "\n");
                DisplayInve();
                //reset input field
                txtField.setText("");
                hasInput = true;
            }
        });
        west = new JButton("West");
        p1.add(west, BorderLayout.WEST);
        west.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = "go w";
                input = txt;
                System.out.println(input);
                //add command in output
                txtArea.append(txt + "\n");
                DisplayInve();
                //reset input field
                txtField.setText("");
                hasInput = true;
            }
        });
        center = new JButton("Up");
        p1.add(center, BorderLayout.CENTER);
        center.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = "go up";
                input = txt;
                System.out.println(input);
                //add command in output
                txtArea.append(txt + "\n");
                DisplayInve();
                //reset input field
                txtField.setText("");
                hasInput = true;
            }
        });

        add(p1);
        input = "";

        quit = new JButton("Quit");
        p2.add(quit, BorderLayout.NORTH);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = "quit";
                input = txt;
                System.out.println(input);
                //add command in output
                txtArea.append(txt + "\n");
                DisplayInve();
                //reset input field
                txtField.setText("");
                hasInput = true;
            }
        });

        down = new JButton("Down");
        p2.add(down, BorderLayout.SOUTH);
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = "go d";
                input = txt;
                System.out.println(input);
                //add command in output
                txtArea.append(txt + "\n");
                DisplayInve();
                //reset input field
                txtField.setText("");
                hasInput = true;
            }
        });

        add(p2);

        txtField = new JTextField();
        txtField.addActionListener(new txtActionListener());
        //txtField.addActionListener(new TextInterface.txtActionListener(new txtActionListener()));
        p2.add(txtField, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scpInveArea = new JScrollPane(inveArea);
        gbcFrame.gridx = 18;
        gbcFrame.gridy = 2;
        gbcFrame.gridwidth = 18;
        gbcFrame.gridheight = 10;
        gbcFrame.fill = GridBagConstraints.BOTH;
        gbcFrame.weightx = 7;
        gbcFrame.weighty = 7;
        inveArea.setEditable(false);
        gbcFrame.anchor = GridBagConstraints.NORTH;
        gbcFrame.insets = new Insets(60,0,0,0);
        gbFrame.setConstraints(scpInveArea, gbcFrame);
        add(scpInveArea);

        inveArea.append("Inventory: " + "\n");


    }


    
    @Override
    public void display(String str){
        txtArea.append(str);
        DisplayInve();
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
            System.out.println(input);
            //add command in output
            txtArea.append(txt + "\n");
            DisplayInve();
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

    public void DisplayInve(){
        ArrayList<Artifact> a = new ArrayList<Artifact>();
        a = Character.getCharacterByName(n).getInventory();
        String s;
        for(int i=0;i<a.size();i++){
            s = a.get(i).print1();
            inveArea.append(s + "\n");
        }
    }

    /*public class TextAreaOutputStream extends OutputStream {
        private JTextArea textControl;

        public TextAreaOutputStream( JTextArea control ) {
            textControl = control;
        }

        public void write( int b ) throws IOException {
            // append the data as characters to the JTextArea control
            textControl.append( String.valueOf( ( char )b ) );
        }
    }*/

}
