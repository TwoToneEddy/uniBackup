package framework;


import java.awt.Color;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p0073862
 */
public class KShape {

    private Color color;
    private int x;
    private int y;
    private Random rand = new Random();

    public KShape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int[] getData() {
        return null;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void nudge(int xmax, int ymax, int nudgeValue) {
        x = x - nudgeValue + rand.nextInt(2 * nudgeValue + 1);
        if (x < 0) {
            x = 0;
        }
        if (x > xmax) {
            x = xmax;
        }
        y = y - nudgeValue + rand.nextInt(2 * nudgeValue + 1);
        if (y < 0) {
            y = 0;
        }
        if (y > ymax) {
            y = ymax;
        }
    }
}
