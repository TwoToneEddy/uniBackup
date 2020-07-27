/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingapp;

/**
 *
 * @author user
 */
public class Item {
    private int itemNumber;
    private String description;
    private double unitPrice;
    private int sort;
    
    public Item(int itemNumber,String description,double unitPrice, int sort){
        
        assert itemNumber >= 0:"itemNumber must be a positive integer";
        assert ((sort == 0) ||(sort == 1)):"sort must be either 0 or 1";
        assert unitPrice >= 0:"unitPrice must be a positive double";
        
        this.itemNumber = itemNumber;
        this.description = description;
        this.unitPrice = unitPrice;
        this.sort = sort;
    }
    
    public int getItemNumber(){
        return itemNumber;
    }
    public String getDescription(){
        return description;
    }
    public double getUnitPrice(){
        return unitPrice;
    }
    public int getSort(){
        return sort;
    }
        
}
