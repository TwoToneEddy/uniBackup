/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingapp;

import java.text.DecimalFormat;
import java.lang.StringBuilder;
import java.util.Formatter;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author user
 */
public class PurchaseByNumber extends Purchase {
    
    private int quantity;
    
    PurchaseByNumber(String name,Double unitPrice,ResourceBundle messages,int quantity){
        super(name,unitPrice,messages);
        this.quantity = quantity;
    }
    
    @Override
    public Double getPurchasePrice(){
        assert quantity >= 0:"Quantity is invalid";
        assert unitPrice >= 0: "Unit price is invalid";
        
        return quantity*unitPrice;
    }
    
    //Additional method not in the super class
    public int getQuantity(){
        assert quantity >= 0:"Quantity is invalid";
        
        return quantity;
    }
    
    @Override
    public String printPurchase(){
        assert quantity >= 0:"Quantity is invalid";
        assert unitPrice >= 0: "Unit price is invalid";
        
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder, Locale.US);
        formatter.format("%-30s %15s %30s %50s %n", name,"£"+ unitPrice,quantity,"£"+getPurchasePrice());
        
        String output = builder.toString();
        System.out.println(output);
        return output;
    
    }
    
}
