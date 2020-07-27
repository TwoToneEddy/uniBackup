/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.awt.Color;

/**
 *
 * @author 09092543
 */
public class KHexagon extends KShape {
    private int s;
    
    public KHexagon(int x, int y, int s, Color color) {
        super(x,y,color);
        this.s = s;
    }

    
    
    public int getS() {
        return s;
    }
    
    @Override
    public int[] getData() {
        int[] data = new int[16];  
        double angle = 2*Math.PI/6;
        for (int i = 0; i < 6; i++) {
            data[3+2*i] = (int) (getX() + s * Math.cos(angle * i));
            data[4+2*i] = (int) (getY() + s * Math.sin(angle * i));
        }
        return data;
    }
    
}
