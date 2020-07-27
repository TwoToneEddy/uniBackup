/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author 09092543
 */
public class KTriangle extends KShape {
    private int d;
    
    public KTriangle(int x, int y, int d, Color color) {
        super(x,y,color);
        this.d = d;
    }
    
    
    public int getD() {
        return d;
    }
   
    @Override
    public int[] getData() {
        int[] data = new int[11];
        int xStore = getX();
        int yStore = getY();
        int dStore = getD();

        data[0]=getColor().getRed();
        data[1]=getColor().getGreen();
        data[2]=getColor().getBlue();
        
        
        //top vertex
        data[3]=xStore;
        data[4]=yStore-dStore;
        //bottom right vertex
        data[5] = (int) (xStore + ((Math.cos(Math.toRadians(30))*dStore)));
        data[6] = (int) (yStore + ((Math.sin(Math.toRadians(30))*dStore)));
        //bottom left vertex
        data[7] = (int) (xStore - ((Math.cos(Math.toRadians(30))*dStore)));
        data[8] = (int) (yStore + ((Math.sin(Math.toRadians(30))*dStore)));

        
        return data;
    }
}
