/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package week2;

/**
 *
 * @author 09092543
 */
public abstract class AbstractStats implements Stats {
    
    public abstract double getSum();

    public abstract int getCount();

    public double getAverage() {return getSum()/getCount();};

    public abstract void add(double element);

    
}
