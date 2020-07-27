import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {


    private static final Dimension COLOR_PANEL_SIZE = new Dimension(60,200);
    private static final int LIGHT_DIAMETER = 50;
    private static final int LIGHT_X=(COLOR_PANEL_SIZE.width-LIGHT_DIAMETER)/2;
    private static final int VERT_SPACING = LIGHT_X;

    private static final int RED_Y= VERT_SPACING;
    private static final int AMBER_Y= RED_Y + VERT_SPACING + LIGHT_DIAMETER;
    private static final int GREEN_Y= AMBER_Y + VERT_SPACING +  LIGHT_DIAMETER;


    /** Creates a new instance of ColorPanel */
    public ColorPanel() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillOval(LIGHT_X,RED_Y,LIGHT_DIAMETER,LIGHT_DIAMETER);

        g.setColor(Color.ORANGE);
        g.fillOval(LIGHT_X,AMBER_Y,LIGHT_DIAMETER, LIGHT_DIAMETER);

        g.setColor(Color.GREEN);
        g.fillOval(LIGHT_X,GREEN_Y,LIGHT_DIAMETER,LIGHT_DIAMETER);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return COLOR_PANEL_SIZE;
    }

    @Override
    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize()
    {
        return getPreferredSize();
    }
}
