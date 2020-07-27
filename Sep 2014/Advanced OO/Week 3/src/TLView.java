import java.util.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TLView  implements Observer, ActionListener {
    
    private static final Dimension PANEL_SIZE = new Dimension(200,200);

    private TLModel model;
    private TLController controller;
    private JFrame frame;
    private JPanel panel;
    private ColorPanel colorPanel;
    
    private JTextField redField = new JTextField(3);
    private JTextField amberField = new JTextField(3);
    private JTextField greenField = new JTextField(3);
    private JLabel redLabel = new JLabel("Red");
    private JLabel amberLabel = new JLabel("Amber");
    private JLabel greenLabel = new JLabel("Green");
    private JButton changeButton = new JButton("Change");
    private JButton initialiseButton = new JButton("Initialise");
     
    public TLView(TLModel model, TLController controller)  {        
        this.model = model; 
        model.addObserver(this);
        this.controller = controller;
        createControls();
        controller.setView(this);
        update(model, null);
    } 
    
    public void createControls()
    {
        frame = new JFrame("MVC Traffic Light Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        createPanel();
        contentPane.add(panel);
        
        //these two lines added by David Sutton to set up practical exercise
        colorPanel = new ColorPanel();
        contentPane.add(colorPanel);
        
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
     public void update(java.util.Observable o, Object arg) {
        redField.setText(model.getRed()?"ON":"OFF");
        amberField.setText(model.getAmber()?"ON":"OFF");
        greenField.setText(model.getGreen()?"ON":"OFF");
        frame.repaint();
    }

    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(4,2));
        redField.setEditable(false);
        amberField.setEditable(false);
        greenField.setEditable(false);
        
        panel.add(redLabel);
        panel.add(redField);
        panel.add(amberLabel);
        panel.add(amberField);
        panel.add(greenLabel);
        panel.add(greenField);
        
        changeButton.addActionListener(this);
        panel.add(changeButton);
       
        initialiseButton.addActionListener(this);
        panel.add(initialiseButton);
        panel.setPreferredSize(PANEL_SIZE);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == initialiseButton)
	    controller.initialise();
        else if (event.getSource() ==changeButton)
            controller.change();
    }
}
   