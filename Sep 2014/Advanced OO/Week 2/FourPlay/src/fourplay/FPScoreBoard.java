/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fourplay;

/**
 *
 * @author User
 */
import java.util.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author User
 */
public class FPScoreBoard implements Observer {
    
    
    private JFrame scoreBoard;
    private JPanel scorePanel;
    private JLabel player1Score,player2Score,player1Label,player2Label;
    private FPModel gameModel;
    
    public FPScoreBoard(FPModel gameModel){
        this.gameModel=gameModel;
        
        gameModel.addObserver(this);
        createBoard();
    }
    
    public void createBoard(){
        
        //Initialise GUI components
        //scoreBoard = new JFrame("ScoreBoard");
        scorePanel = new JPanel();
        
        player1Label = new JLabel("", JLabel.CENTER);        
        player2Label = new JLabel("",JLabel.CENTER); 
        player1Score = new JLabel("", JLabel.CENTER);        
        player2Score = new JLabel("",JLabel.CENTER);
        
        player1Label.setText("Player 1");
        player2Label.setText("Player 2");
        player1Score.setText(""+gameModel.getScore(1));
        player2Score.setText(""+gameModel.getScore(2));
        
        scoreBoard = new JFrame("Java Swing Examples");
        scoreBoard.setPreferredSize(new Dimension(200,200));
        scoreBoard.setLayout(new GridLayout(2, 2));
        scoreBoard.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
         }        
        }); 
        
        scoreBoard.add(player1Label);
        scoreBoard.add(player2Label);
        scoreBoard.add(player1Score);
        scoreBoard.add(player2Score);
        
        scoreBoard.pack();
        scoreBoard.setResizable(false);
        scoreBoard.setVisible(true);
        
    }
    
    public void update(java.util.Observable o, Object arg){
        player1Score.setText(""+gameModel.getScore(1));
        player2Score.setText(""+gameModel.getScore(2));
        scoreBoard.repaint();
    }

    
}
