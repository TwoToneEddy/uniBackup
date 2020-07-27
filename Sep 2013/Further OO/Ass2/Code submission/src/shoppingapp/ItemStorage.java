/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingapp;

import java.util.HashMap;

/**
 *
 * @author user
 */
public class ItemStorage {
    
    HashMap<String,Item> itemMap;
    
    public ItemStorage(){
        itemMap = new HashMap<String,Item>();
    
    }
    
    public void recordItem(int itemNumber,String description,double unitPrice,int sort) {
        // pre: ! isKnownItemNumber(itemNumber)
        // && unitPrice > 0
        // && (sort == 0 || sort == 1) sort == 0 => by number, sort == 1 ==> by weight
        // post:
        // description unit price and sort now stored for item with this itemNumber
        assert itemNumber >= 0:"itemNumber must be a positive integer";
        assert ((sort == 0) ||(sort == 1)):"sort must be either 0 or 1";
        assert unitPrice >= 0:"unitPrice must be a positive double";
        
        itemMap.put(Integer.toString(itemNumber), new Item(itemNumber,description,unitPrice,sort));
    }
    
    public String getDescription(int itemNumber) {
        // pre: isKnownItemNumber(itemNumber)
        // post: returns description for item with this itemNumber
        assert isKnownItemNumber(itemNumber) == true:"Item must exist";
        
        Item item = itemMap.get(Integer.toString(itemNumber));
        return item.getDescription();
    }
    
    public double getUnitPrice(int itemNumber) {
        // pre: isKnownItemNumber(itemNumber)
        // post: returns unit price for item with this itemNumber
        assert isKnownItemNumber(itemNumber) == true:"Item must exist";
        
        Item item = itemMap.get(Integer.toString(itemNumber));
        return item.getUnitPrice();
    }
    
    public int getSort(int itemNumber) {
        // pre: isKnownItemNumber(itemNumber)
        // post: returns sort (0 or 1) for item with this itemNumber
        assert isKnownItemNumber(itemNumber) == true:"Item must exist";
        
        Item item = itemMap.get(Integer.toString(itemNumber));
        return item.getSort();
    }
    
    public boolean isKnownItemNumber(int itemNumber) {
        // pre: true
        // post: returns true iff there is information stored for this itemNumber
        String itemNumberString = Integer.toString(itemNumber);
        if(itemMap.containsKey(itemNumberString))
            return true;
        else
            return false;
        
    }
}
