/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingapp;
import java.util.Formatter;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author user
 */
public class PurchaseByWeight extends Purchase {
    
    private Double weight;
    
    PurchaseByWeight(String name, Double unitPrice,ResourceBundle messages, Double weight){
        super(name,unitPrice,messages);
        this.weight = weight;
    }
    
    @Override
    public Double getPurchasePrice(){
        assert weight >= 0:"Weight is invalid";
        assert unitPrice >= 0: "Unit price is invalid";
        
        return weight*unitPrice;
    }
    
    //Additional method not in the super class
    public Double getWeight(){
        assert weight >= 0:"Weight is invalid";
        
        return weight;
    }
    
    @Override
    public String printPurchase(){
        assert weight >= 0:"Weight is invalid";
        assert unitPrice >= 0: "Unit price is invalid";
        
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder, Locale.US);
        formatter.format("%-30s %15s %30s %45s %n", name, "£"+ unitPrice,weight+"kg","£"+getPurchasePrice());
        
        String output = builder.toString();
        System.out.println(output);
        return output;
    
    }
    
}
