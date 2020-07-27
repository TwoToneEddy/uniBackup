/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Container;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.lang.StringBuilder;
import java.util.Formatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

/**
 *
 * @author user
 */
public class ShoppingApp extends JFrame implements ActionListener{

   
    
    static Locale currentLocale;
    static ResourceBundle messages;
    
    private JFrame mainFrame = new JFrame ("Basket");
    
    //Panel for the buttons
    private JPanel buttonPanel = new JPanel();
    
    //Panel for the text inputs
    private JPanel inputPanel = new JPanel();
    
    //Panel to contain all of the lower part of the GUI
    private JPanel lowerPanel = new JPanel();
    
    //Scroll pane used for the shopping list
    private DefaultListModel shoppingListModel = new DefaultListModel();
    private JList shoppingList = new JList(shoppingListModel);
    private JScrollPane listScrollPane = new JScrollPane(shoppingList);
    
    //The two buttons for interaction
    private JButton addItem = new JButton();
    
    //The input text fields
    private JTextField itemNumber = new JTextField();
    private JLabel itemNumberLabel = new JLabel();
    private JTextField quantity = new JTextField();
    private JLabel quantityLabel = new JLabel();
    private Font labelFont=new Font(itemNumberLabel.getFont().getName(),Font.ITALIC,
                                       itemNumberLabel.getFont().getSize());
    
    //JList to display the errors
    private DefaultListModel errorListModel = new DefaultListModel();
    private JList errorList = new JList(errorListModel);
    private JScrollPane errorScrollPane = new JScrollPane(errorList);
    
    private Scanner inputScanner;
    
    private ArrayList<PurchaseByNumber> purchasesByNumber =  new ArrayList<PurchaseByNumber>();
    private ArrayList<PurchaseByWeight> purchasesByWeight =  new ArrayList<PurchaseByWeight>();
    private ArrayList<String> errorArrayList = new ArrayList<String>();
    
    StringBuilder builder = new StringBuilder();
    Formatter formatter = new Formatter(builder, Locale.US);
    ItemStorage itemStorage;
    
    /**
     * @param args the command line arguments
     */
    public ShoppingApp(){
        itemStorage = new ItemStorage();
        
        itemStorage.recordItem(1, "Oranges", 0.49, 0);
        itemStorage.recordItem(2, "Apples", 0.39, 0);
        itemStorage.recordItem(3, "Bananas", 0.68, 1);
        itemStorage.recordItem(4, "Pick n mix", 1.29, 1);
        itemStorage.recordItem(5, "Beef", 1.40, 1);
        itemStorage.recordItem(6, "Coffee", 2.39, 0);
        
        
        itemNumber.setText(messages.getString("itemNumberTextField"));
        itemNumberLabel.setText(messages.getString("itemNumberLabel"));
        quantity.setText(messages.getString("quantityTextField"));
        quantityLabel.setText(messages.getString("quantityLabel"));
        addItem.setText(messages.getString("addItem"));
                
        addItem.addActionListener(this);
        
        //Fix dimensions for gui
        listScrollPane.setPreferredSize(new Dimension(600, 150));      
        buttonPanel.setPreferredSize(new Dimension(200,150));
        inputPanel.setPreferredSize(new Dimension(200,150));
        errorScrollPane.setPreferredSize(new Dimension(200,150));
 
        //Setup the button panel
        buttonPanel.setLayout(new GridLayout(2,2));      
        buttonPanel.add(addItem);

        //Setup the input panel        
        inputPanel.setLayout(new GridLayout(3,3));
        inputPanel.add(itemNumber);
        inputPanel.add(itemNumberLabel);
        inputPanel.add(quantity);
        inputPanel.add(quantityLabel);
        
        //Set label parameters for input panel labels
        itemNumberLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        itemNumberLabel.setFont(labelFont);
        quantityLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        quantityLabel.setFont(labelFont);
        
        
        //Encompass all of the above in the lower panel
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
        lowerPanel.add(buttonPanel);
        lowerPanel.add(inputPanel);
        lowerPanel.add(errorScrollPane);
        
        //Setup the main container
        Container contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(listScrollPane);
        contentPane.add(lowerPanel);
        
        formatter.format("%1$1s %2$30s %3$30s %4$30s %n", messages.getString("itemName"), messages.getString("unitPrice"),messages.getString("quantity"),messages.getString("total"));
        shoppingListModel.addElement(formatter.toString());
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        String itemNumberText = itemNumber.getText();
        String quantityText = quantity.getText();
        
        //Exits the method if an error has been found,
        //This allows all fields to be queried so all
        //errors can be display, not just the one it failed
        //on.
        boolean errorCaught = false;
        
        String name = "" ;
        Double weight = 0.0;
        int quantity = 0  ;
        int itemNo = 0;
        int sort = 2;
        
        if(e.getSource()==addItem){
            inputScanner = new Scanner(itemNumberText);
            
            try{
                itemNo = inputScanner.nextInt();
                clearError(messages.getString("itemNoError"));
                if(itemStorage.isKnownItemNumber(itemNo) == false){
                    displayError(messages.getString("notKnownError"));
                    return;
                }else{
                    clearError(messages.getString("notKnownError"));
                }
            }catch(InputMismatchException ex){
                displayError(messages.getString("itemNoError"));
                return;
            }
            
            sort = itemStorage.getSort(itemNo);
            
            switch(sort){
                // 0 = by number purchase
                case 0:
                    clearError(messages.getString("weightError"));
                    inputScanner = new Scanner(quantityText);
                    
                    try{
                        quantity = inputScanner.nextInt();
                        if(quantity < 0){
                            displayError(messages.getString("quantError"));
                            errorCaught = true;
                        }else{
                            clearError(messages.getString("quantError"));
                        }
                    }catch (InputMismatchException ex){
                        displayError(messages.getString("quantError"));
                        errorCaught = true;
                    }
                    if(errorCaught)
                        return;
                    purchasesByNumber.add(new PurchaseByNumber(itemStorage.getDescription(itemNo),itemStorage.getUnitPrice(itemNo),messages,quantity));
                    shoppingListModel.addElement(purchasesByNumber.get(purchasesByNumber.size()-1).printPurchase());
                    
                    break;
                // 1 =  by wieght purchase
                case 1:
                    clearError(messages.getString("quantError"));
                    inputScanner = new Scanner(quantityText);
                    try{
                        weight = inputScanner.nextDouble();
                        if(weight < 0){
                            displayError(messages.getString("weightError"));
                            errorCaught = true;
                        }else{
                            clearError(messages.getString("weightError"));
                        }
                    }catch (InputMismatchException ex){
                        displayError(messages.getString("weightError"));
                        errorCaught = true;
                    }
                    if(errorCaught)
                        return;
                    purchasesByWeight.add(new PurchaseByWeight(itemStorage.getDescription(itemNo),itemStorage.getUnitPrice(itemNo),messages,weight));
                    shoppingListModel.addElement(purchasesByWeight.get(purchasesByWeight.size()-1).printPurchase());
                    break;
                default:
                    break;
            }
   
        }
        
        
    }
     
    //Displays error in the error list
    public void displayError(String error){
        if(errorListModel.contains(error) == false){
            errorListModel.addElement(error);
        }
    }
    
    //Removes an error from the error list
    public void clearError(String error){
        for( int i = 0; i < errorListModel.size(); i++){
            if(error.equalsIgnoreCase(errorListModel.get(i).toString())){
                errorListModel.remove(i);
            }
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
      String language;
      String country;

      if (args.length != 2) {
          language = new String("en");
          country = new String("US");
      } else {
          language = new String(args[0]);
          country = new String(args[1]);
      }
      
      

      currentLocale = new Locale(language, country);

      try{
        messages = ResourceBundle.getBundle("MessagesBundle",currentLocale);
      }catch(MissingResourceException ex){
          System.out.println("Cannot find resource file!!");
      }
      
        new ShoppingApp();
        
    }
    
    
}
