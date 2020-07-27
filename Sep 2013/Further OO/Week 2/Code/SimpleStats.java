/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package week2;

/**
 *
 * @author 09092543
 */
public class SimpleStats extends AbstractStats {

    private double sum, lastElement,penultimateElement;
    private int count;

    public double getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }

    

    public void add(double element) {
        //Added for ex7, stores the second to last value
        penultimateElement = lastElement;
        lastElement = element;
       
        count++;
        sum += element;
    }

    public double getLastElement() {
        return lastElement;
    }
    
    //Added to ex7, returns the second to last element.
    public double getPenultimateElement(){
        return penultimateElement;
    }
}

