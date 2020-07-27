/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package week2;

/**
 *
 * @author 09092543
 */
public interface Stats extends Addable {

    public double getSum();

    public int getCount();

    public double getAverage();
    
    //Added for ex7, already implemented in SimpleStats, added to ArrayStats
    public double getLastElement();
    
    //Added for ex7, added to both ArrayStats and SimpleStats
    public double getPenultimateElement();

}

