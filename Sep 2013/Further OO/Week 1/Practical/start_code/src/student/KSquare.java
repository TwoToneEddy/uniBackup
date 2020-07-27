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

public class KSquare extends KShape {
    private int width;
    
    public KSquare(int x, int y, int w, Color color) {
        super(x,y,color);
        this.width = w;
    }

    
    
    public int getWidth() {
        return width;
    }
    
    @Override
    public int[] getData() {
       
        int[] data = new int[11];
        
        data[0]=getColor().getRed();
        data[1]=getColor().getGreen();
        data[2]=getColor().getBlue();
        data[3]=getX()-getWidth()/2;
        data[4]=getY()-getWidth()/2;
        data[5]=getX()+getWidth()/2;
        data[6]=getY()-getWidth()/2;
        data[7]=getX()+getWidth()/2;
        data[8]=getY()+getWidth()/2;
        data[9]=getX()-getWidth()/2;
        data[10]=getY()+getWidth()/2;
        
        return data;
    }
}

