/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketch;

/**
 *
 * @author 09092543
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import javax.swing.JPanel;

public class SketchPanel extends JPanel {

    private static final int DIAMETER = 16;
    private List<Point> points;

    public SketchPanel(List<Point> points) {
        this.points = points;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);

        int size = points.size();
        int[] x = new int[size];
        int[] y = new int[size];
        int i = 0;
        for (Point p : points) {
            x[i] = p.x;
            y[i] = p.y;
            i++;
        }
        g.drawPolyline(x, y, i);
        if (size > 0) {
            g.fillOval(x[size - 1] - DIAMETER / 2, y[size - 1] - DIAMETER / 2,
                    DIAMETER, DIAMETER);
        }
    }
}

