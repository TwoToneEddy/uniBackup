/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

/**
 *
 * @author 09092543
 */
import java.awt.Color;
import java.util.Random;

public class KShape {
    private int x;
    private int y;
    private Color color;
    private Random randNo;
    
    public KShape(int x, int y,Color color) {
        this.x=x;
        this.y=y;
        this.color = color;
    }
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Color getColor() {
        return color;
    }
    
    void nudge(int xMax,int yMax,int nudgeVal){
        randNo = new Random();
        if((x < xMax) && (x > 0))
            x = x - (nudgeVal + randNo.nextInt((2*nudgeVal)+1));
        
        if((y < yMax) && (y > 0))
            y = y - (nudgeVal + randNo.nextInt((2*nudgeVal)+1));
    }
    
    public int[] getData(){
        return null;
    }
}
