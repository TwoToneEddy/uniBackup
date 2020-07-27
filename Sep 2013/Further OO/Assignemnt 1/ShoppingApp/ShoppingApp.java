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

/**
 *
 * @author user
 */
public class ShoppingApp extends JFrame implements ActionListener{

    public static final String QUANT_ERROR = "Quantity must be a pos int!";
    public static final String WEIGHT_ERROR = "Weight must be a pos double!";
    public static final String PRICE_ERROR = "Price must be a pos double!";
    public static final String NAME_ERROR = "Invalid name";
    
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
    private JButton addNumber = new JButton("Add by number");
    private JButton addWeight = new JButton("Add by Weight");
    
    //The input text fields
    private JTextField itemName = new JTextField("name");
    private JLabel itemNameLabel = new JLabel("Name");
    private JTextField quantity = new JTextField("quantity");
    private JLabel quantityLabel = new JLabel("Qty / Weight (kg)");
    private JTextField price = new JTextField("price");
    private JLabel priceLabel = new JLabel("Price (£)");
    private Font labelFont=new Font(itemNameLabel.getFont().getName(),Font.ITALIC,
                                       itemNameLabel.getFont().getSize());
    
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
    
    /**
     * @param args the command line arguments
     */
    public ShoppingApp(){
        
        addNumber.addActionListener(this);
        addWeight.addActionListener(this);
        
        //Fix dimensions for gui
        listScrollPane.setPreferredSize(new Dimension(600, 150));      
        buttonPanel.setPreferredSize(new Dimension(200,150));
        inputPanel.setPreferredSize(new Dimension(200,150));
        errorScrollPane.setPreferredSize(new Dimension(200,150));
 
        //Setup the button panel
        buttonPanel.setLayout(new GridLayout(2,2));      
        buttonPanel.add(addNumber);
        buttonPanel.add(addWeight);

        //Setup the input panel        
        inputPanel.setLayout(new GridLayout(3,3));
        inputPanel.add(itemName);
        inputPanel.add(itemNameLabel);
        inputPanel.add(quantity);
        inputPanel.add(quantityLabel);
        inputPanel.add(price);
        inputPanel.add(priceLabel);
        
        //Set label parameters for input panel labels
        itemNameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        itemNameLabel.setFont(labelFont);
        quantityLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        quantityLabel.setFont(labelFont);
        priceLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        priceLabel.setFont(labelFont);
        
        
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
        
        formatter.format("%1$1s %2$30s %3$30s %4$30s %n", "Item Name", "Unit Price (£)","Quantity / Weight (kg)","Total (£)");
        shoppingListModel.addElement(formatter.toString());
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        String nameText = itemName.getText();
        String quantityText = quantity.getText();
        String priceText = price.getText();
        
        //Exits the method if an error has been found,
        //This allows all fields to be queried so all
        //errors can be display, not just the one it failed
        //on.
        boolean errorCaught = false;
        
        String name = "" ;
        Double price = 0.0;
        Double weight = 0.0;
        int quantity = 0;
        
        if(e.getSource()==addNumber){
            inputScanner = new Scanner(quantityText);
            
            try{
                quantity = inputScanner.nextInt();
                if(quantity < 0){
                    displayError(QUANT_ERROR);
                    errorCaught = true;
                }else{
                    clearError(QUANT_ERROR);
                }
            }catch (InputMismatchException ex){
                displayError(QUANT_ERROR);
                errorCaught = true;
            }
            
            inputScanner = new Scanner(priceText);
            try{
                price = inputScanner.nextDouble();
                if(price <= 0){
                    displayError(PRICE_ERROR);
                    errorCaught = true;
                }else{
                    clearError(PRICE_ERROR);
                }
            }catch (InputMismatchException ex){
                displayError(PRICE_ERROR);
                errorCaught = true;
            }
            
            inputScanner = new Scanner(nameText);
            try{
                name = inputScanner.next();
                clearError(NAME_ERROR);
            }catch (NoSuchElementException ex){
                displayError(NAME_ERROR);
                errorCaught = true;
            }
            if(errorCaught)
                return;
            purchasesByNumber.add(new PurchaseByNumber(name,price,quantity));
            shoppingListModel.addElement(purchasesByNumber.get(purchasesByNumber.size()-1).printPurchase());
        }
        
        if(e.getSource()==addWeight){
            inputScanner = new Scanner(quantityText);
            
            try{
                weight = inputScanner.nextDouble();
                if(weight < 0){
                    displayError(WEIGHT_ERROR);
                    errorCaught = true;
                }else{
                    clearError(WEIGHT_ERROR);
                }
            }catch (InputMismatchException ex){
                displayError(WEIGHT_ERROR);
                errorCaught = true;
            }
            
            inputScanner = new Scanner(priceText);
            try{
                price = inputScanner.nextDouble();
                if(price <= 0){
                    displayError(PRICE_ERROR);
                    errorCaught = true;
                }else{
                    clearError(PRICE_ERROR);
                }
            }catch (InputMismatchException ex){
                displayError(PRICE_ERROR);
                errorCaught = true;
            }
            
            inputScanner = new Scanner(nameText);
            try{
                name = inputScanner.next();
                clearError(NAME_ERROR);
            }catch (NoSuchElementException ex){
                displayError(NAME_ERROR);
                errorCaught = true;
            }
            
            if(errorCaught){
                return;
            }
            
            purchasesByWeight.add(new PurchaseByWeight(name,price,weight));
            shoppingListModel.addElement(purchasesByWeight.get(purchasesByWeight.size()-1).printPurchase());
            
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
        new ShoppingApp();
        
    }
    
    
}
