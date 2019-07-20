/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.util.Duration;
import javax.swing.Timer;

/**
 *
 * @author R2D2
 */
    
public class GUIClass implements Initializable,ActionListener{//gui class
    Timer timer = new Timer(1,this);//timer for the uno checking
    @FXML
    private GridPane p0Pane, p1Pane, p2Pane, p3Pane;//gridpanes for the hands
    @FXML
    private Button btnDraw,btnUNO;//buttons 
    ArrayList<ImageView> hand0 = new ArrayList();//array list of image views for the hand
    ArrayList<ImageView> hand1 = new ArrayList();
    ArrayList<ImageView> hand2 = new ArrayList();
    ArrayList<ImageView> hand3 = new ArrayList();
    @FXML
    private ImageView imgStash, imgDeck, imgWin;//image views
    @FXML
    private Label lblWin, lblP0, lblP1, lblP2, lblP3;//labels
    @FXML
    private MenuItem btnStart;//menu button
    GamePlay game = new GamePlay();//instance of gameplay class
    
    @FXML
    private void handleStart(ActionEvent event) {//start button
        btnStart.setText("Restart game");//changes the name of the button
        tmr.stop();//tops the timers
        timer.stop();
        lblP0.setVisible(true);//makes the player names visible
        lblP1.setVisible(true);       
        lblP2.setVisible(true);       
        lblP3.setVisible(true);       
        game.config();//runs the config method
        for(int i = 0; i < 4; ++i){//clears the panes for each player
            panes[i].getChildren().clear();
            hands[i].clear();
        }
        for(int i = 0; i < 5 ; ++i){//displays the cards in the players hand
            hand0.add(new ImageView());//adds the cards to the 
            hand1.add(new ImageView());//array of imageviews
            hand2.add(new ImageView());
            hand3.add(new ImageView());
            hand0.get(i).setImage(new Image(game.getCard(0, i).getPath()));//gets the image path 
            hand1.get(i).setImage(new Image("resources/BackR.png"));//makes all the bots cards
            hand2.get(i).setImage(new Image("resources/BackT.png"));//to the backside 
            hand3.get(i).setImage(new Image("resources/BackL.png"));//of the uno card
            p0Pane.add(hand0.get(i), i, 0);//puts the images
            p1Pane.add(hand1.get(i), 0, i);//onto the panes
            p2Pane.add(hand2.get(i), i, 0);
            p3Pane.add(hand3.get(i), 0, i);
        }
        desWins(false);//desables the winds buttons
        imgDeck.setImage(new Image("resources/Back.png"));//makes the deck image set to back of the card
        imgStash.setImage(new Image(game.getTop().getPath()));//makes the stash image to the top card
        imgWin.setImage(new Image("resources/Congrats.gif"));//makes the win image set to congrats.gif
        desBtn(false);//desables the buttons
        setHandle();//sets the handle
    }
    
    
    @FXML
    private void handleDraw(ActionEvent event) {//button to draw
        game.players.get(0).drawCard(game.deck.remove(0));//draws a card
        updateCards();//updates the cards
        game.changeTurn();//changes turn
        desBtn(false);//desables the buttons
        tmr.play();//starts the timer
    }
    
    public void desBtn(boolean bol){//method to desable buttons
        btnDraw.setVisible(bol);
        btnDraw.setDisable(!bol);
    }
    
    @FXML
    private void handleUno(ActionEvent event) {//button to handle the uno
        timer.stop();//stops timer
        processUno(0);  //processes uno
        tmr.play();//starts the timer
        btnUNO.setVisible(false);
        btnUNO.setDisable(true);
    }
    
    public void processUno(int p){//method to process uno
        if(unoP != p)
            game.players.get(unoP).drawCard(game.deck.remove(0));
    }
    
    public boolean checkWinner(){//method to check the winner
        if(game.players.get(game.getTurn()).cards.isEmpty()){//checks if their hand is empty
            timer.stop();//stops the timers
            tmr.stop();
            desWins(true);//desables the buttons
            lblWin.setText(game.players.get(game.getTurn()).getName() + " WINS!");//set the label
            btnUNO.setVisible(false);//modifies stuff
            imgWin.setVisible(true);
            return true;
        }
        return false;
    }
    
    private void desWins(boolean bol){//method to desable the winning stuff
        lblWin.setVisible(bol);
        imgWin.setVisible(bol);
    }
    
    private int unoP;//int to store the person who said uno
    
    public boolean checkUno(int p){//method to check for uno
        if(game.players.get(p).cards.size() == 1){//cheks size
            unoP = p;
            for(int i  = 1; i < 4; ++i)//gives the bots a random interval
                ((AI)game.players.get(i)).setInterval((int)(1+Math.random()*3000));
            return true;
        }
        return false;
    }
    GridPane[] panes = new GridPane[4];//array of gridpanes
    ArrayList[] hands = new ArrayList[4];
    private boolean winner = false;//boolean winner

    public void setHandle(){//method that sets the handle for the cards
        EventHandler eve = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int col = GridPane.getColumnIndex(((ImageView) event.getSource()));//gets the column that they clicked
                if(!game.checkCard(col))//checks that card 
                    return;
                prevTurn = game.getTurn();//gets the current turn
                game.processCard(col);//processes the card
                imgStash.setImage(new Image(game.getTop().getPath()));//sets the top to that
                updateCards();//updates the cards
                if(checkUno(prevTurn)){//checks for uno
                    timer.start();//starts the timer
                    btnUNO.setVisible(true);//modifies stuff
                    btnUNO.setDisable(false);
                    return;
                }if(checkWinner()){//checks for the winner
                    return;
                }
                setDisCards(true);//desables some buttons
                tmr.play();//starts second timer
            }
        };
        for(int i = 0; i < hand0.size(); ++i){//adds the event to the buttons
            hand0.get(i).setOnMouseClicked(eve);
        }
    }
    
    private int prevTurn;//method previous turn
    
    Timeline tmr = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {//timeline
        @Override
        public void handle(ActionEvent event) {
            updateCards();//updates the cards
            if(!winner){//checks if there is no winner
                counter = 0;//resets counter
                prevTurn = game.getTurn();//gets the prev turn
                if(game.getTurn() == 0){//checks for player 1
                    if(!game.players.get(0).hasPlayable())//checsk if i have a playable card
                        desBtn(true);//modifies buttons
                    setDisCards(false);//disables the cards
                    tmr.stop();//stops the timer
                    return;
                }else
                    game.botMove();//goes through the bot moves    
                imgStash.setImage(new Image(game.getTop().getPath()));//updates top image
                updateCards();//updates cards
                if(checkWinner())//checks the winner
                    return;
                if(checkUno(prevTurn)){//checks for uno
                    timer.start();//starts the timer
                    btnUNO.setVisible(true);//modifies stuff
                    btnUNO.setDisable(false);
                    return;
                }
                btnUNO.setVisible(false);//modifies stuff
                btnUNO.setDisable(true);
                tmr.stop();//stops and starts the timer
                tmr.play();
            }
        }
    }));
    
    

    public void updateCards(){//method to update the cards       
        panes[0].getChildren().clear();//clears the panes
        hands[0].clear();
        for(int i = 0; i < game.getHandSize(0) ; ++i){//updates player hand
            hands[0].add(new ImageView());
            hand0.get(i).setImage(new Image(game.getCard(0, i).getPath()));
            p0Pane.add(hand0.get(i), i, 0);
        }

        setHandle();//sets the handle
        
        for(int i = 1; i < 4; ++i){//clears panes
            panes[i].getChildren().clear();
            hands[i].clear();
        }
        for(int i = 0; i < game.getHandSize(1) ; ++i){//updates the bot hand
            hand1.add(new ImageView());
            hand1.get(i).setImage(new Image("resources/BackR.png"));
            p1Pane.add(hand1.get(i), 0, i);
        }
        
        for(int i = 0; i < game.getHandSize(2) ; ++i){
            hand2.add(new ImageView());
            hand2.get(i).setImage(new Image("resources/BackT.png"));
            p2Pane.add(hand2.get(i), i, 0);
        }
        
        for(int i = 0; i < game.getHandSize(3) ; ++i){
            hand3.add(new ImageView());
            hand3.get(i).setImage(new Image("resources/BackL.png"));
            p3Pane.add(hand3.get(i), 0, i);
        }

    }
    
    public void setDisCards(boolean bol){//method to desable the cards
        for(int i = 0; i < hand0.size(); ++i)
            p0Pane.getChildren().get(i).setDisable(bol);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {//adds the panes to the array on start
        hands[0] = hand0;
        hands[1] = hand1;
        hands[2] = hand2;
        hands[3] = hand3;
        panes[0] = p0Pane;
        panes[1] = p1Pane;
        panes[2] = p2Pane;
        panes[3] = p3Pane;
    }    
 
    private int counter = 0;//counter for the uno timer
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {//uno timer
        counter++;//counter increments
        if(counter == game.getIn(1)){//checks if the counter has hit the bots interval
            btnUNO.setVisible(false);//modifies buttons
            btnUNO.setDisable(true);
            tmr.play();//starts the timer
            timer.stop();//stops this timer
            processUno(1);//processes uno
            return;
        }else if(counter == game.getIn(2)){       
            btnUNO.setVisible(false);
            btnUNO.setDisable(true);
            tmr.play();
            timer.stop();
            processUno(2);
            return;
        }else if(counter == game.getIn(3)){        
            btnUNO.setVisible(false);
            btnUNO.setDisable(true);
            tmr.play();
            timer.stop();
            processUno(3);
            return;
        }
    }
}
