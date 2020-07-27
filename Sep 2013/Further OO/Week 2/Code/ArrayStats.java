/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package week2;

/**
 *
 * @author 09092543
 */
public class ArrayStats extends AbstractStats {

    public static final int MAX_ELEMENTS = 1000;
    protected double[] elements = new double[MAX_ELEMENTS];
    private int count;

    public double getElement(int i) {
        return elements[i];
    }

    public double getSum() {
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += getElement(i);
        }
        return sum;
    }

    public int getCount() {
        return count;
    }

    

    public void add(double element) {
        elements[count] = element;
        count++;
    }
    
    //Added for ex7, returns ,last element in the array
    public double getLastElement(){
        if(count >=1){
            return getElement(count-1);
        }else{
            return 0;
        }
            
    }
    
    //Added for ex7, returns the second to last element in the array
    public double getPenultimateElement(){
       
        if(count >= 2){
            return getElement(count - 2);
        }else{
            return 0;
        }
        
    }
}

