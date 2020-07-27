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
public abstract class Purchase implements PurchaseInterface {
    public String name;
    public Double unitPrice;
    ResourceBundle messages;
    
    public Purchase(String name, Double unitPrice,ResourceBundle messages){
        this.name = name;
        this.unitPrice = unitPrice;
        this.messages = messages;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public Double getUnitPrice(){
        return unitPrice;
    }
    
    //Implemented differently in each subclass
    public abstract Double getPurchasePrice();
    public abstract String printPurchase();
    
}
