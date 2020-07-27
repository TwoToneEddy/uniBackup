/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketch;

/**
 *
 * @author 09092543
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Sketch extends JFrame implements MouseListener,MouseMotionListener,ActionListener {

    private static final Dimension SKETCH_SIZE = new Dimension(360, 360);
    private JPanel controlPanel = new JPanel();
    private JButton deleteButton = new JButton("Delete");
    private JTextField xTextField = new JTextField("x");
    private JTextField yTextField = new JTextField("y");
    
    ArrayList<Point> points = new ArrayList<Point>();
    SketchPanel sketchPanel = new SketchPanel(points);

    public Sketch() {
        super("Sketch");

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        sketchPanel.addMouseListener(this);
        sketchPanel.addMouseMotionListener(this);
        sketchPanel.setPreferredSize(SKETCH_SIZE);
        contentPane.add(sketchPanel);
        
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.add(deleteButton);
        controlPanel.add(xTextField);
        controlPanel.add(yTextField);
        
        xTextField.setEditable(false);
        yTextField.setEditable(false);
        
        contentPane.add(controlPanel);

        deleteButton.addActionListener(this);
        
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }
    
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            int i = points.size();
            if(i > 0){
                points.remove(i-1);
                repaint();
            }
           
        }
    }


    public void mouseClicked(MouseEvent e) {
        points.add(e.getPoint());
        repaint();
    }

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        xTextField.setText("X: "+x);
        yTextField.setText("Y: "+y);
        repaint();
        
    }
    
    public void mouseDragged(MouseEvent e) {
    }
    
    public static void main(String[] args) {
        new Sketch();
    }
}

