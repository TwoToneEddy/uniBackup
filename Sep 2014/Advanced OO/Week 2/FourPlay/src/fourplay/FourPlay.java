/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fourplay;
import java.awt.Dimension;
import java.io.*;
/**
 *
 * @author Lee Hudson 09092543
 */
public class FourPlay {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() {
                public void run () {createAndShowGUI();}
            }
        ); 
        
        
    }
    public static void createAndShowGUI() {
        Console c = System.console();
        
        FPModel gameModel = new FPModel();
        FPController gameController = new FPController(gameModel);
        FPView gameView = new FPView(gameController,gameModel);
        FPScoreBoard scoreBoard = new FPScoreBoard(gameModel);
    }   
}
