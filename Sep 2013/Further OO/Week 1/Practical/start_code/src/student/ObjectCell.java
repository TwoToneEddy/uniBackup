package student;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p0073862
 */
public class ObjectCell {

    private int width;
    private int height;
    private int noOfSqares = 300;
    private int noOftriangles = 300;
    private int noOfhexagons = 300;
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN,
        Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};

   // private ArrayList<KSquare> squares = new ArrayList<KSquare>();
    //private ArrayList<KTriangle> triangles = new ArrayList<KTriangle>();
    private ArrayList<KShape> shapes = new ArrayList<KShape>();
    private int nudge = 5;
    

    public ObjectCell(int width, int height) {
        this.width = width;
        this.height = height;
        reset();
    }

    public ArrayList<int[]> getShapeData() {
        ArrayList<int[]> data = new ArrayList<int[]>(); 
        
       // System.out.println(""+squareList.size());
        for(KShape s: shapes){
            data.add(s.getData());
        }

        return data;
        //return getRedBlackSquares();
        
    }
    


    public void reset() {
        Random numberGen = new Random();
        for (int i = 0; i < noOfSqares; i++) {
                int x = numberGen.nextInt(width);
                int y = numberGen.nextInt(height);
                Color c = colors[numberGen.nextInt(colors.length)];
                int w = numberGen.nextInt(30);
                shapes.add(new KSquare(x, y, w, c));
             }
        for (int i = 0; i < noOftriangles; i++) {
                int x = numberGen.nextInt(width);
                int y = numberGen.nextInt(height);
                Color c = colors[numberGen.nextInt(colors.length)];
                int w = numberGen.nextInt(30);
                shapes.add(new KTriangle(x, y, w, c));
             }
       for (int i = 0; i < noOfhexagons; i++) {
                int x = numberGen.nextInt(width);
                int y = numberGen.nextInt(height);
                Color c = colors[numberGen.nextInt(colors.length)];
                int w = numberGen.nextInt(30);
                shapes.add(new KHexagon(x, y, w, c));
             }

    }

        public void but1() {
    }

    public void but2() {
    }

    public void nudge() {
        for(KShape s : shapes){
            s.nudge(width, height, nudge);
        }
    }
    
    private ArrayList<int[]> getRedBlackSquares() {

        ArrayList<int[]> result = new ArrayList<int[]>();
        int squareWidth = Math.min(width, height) / 50;
        int sep = squareWidth * 3 / 2;
        boolean black = true;
        for (int i = 0;
                i < width;
                i += sep) {
            for (int j = 0; j < height; j += sep) {
                int[] data = new int[11];
                data[0] = black ? 0 : 255;
                data[1] = 0;
                data[2] = 0;
                data[3] = i;
                data[4] = j;
                data[5] = i + squareWidth;
                data[6] = j;
                data[7] = i + squareWidth;
                data[8] = j + squareWidth;
                data[9] = i;
                data[10] = j + squareWidth;
                result.add(data);
                black = !black;
            }
        }
        return result;
    }


}
