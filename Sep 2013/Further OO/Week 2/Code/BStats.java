/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package week2;

/**
 *
 * @author 09092543
 */
public class BStats implements TrendBoundableStats{
    private Stats stats;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;

    public BStats(Stats stats) {
        this.stats = stats;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public void add(double element) {
        if (element > max) {
            max = element;
        }
        if (element < min) {
            min = element;
        }
        stats.add(element);
    }

    public double getSum() {
        return stats.getSum();
    }

    public int getCount() {
        return stats.getCount();
    }

    public double getAverage() {
        return stats.getAverage();
    }
    
    //Added for ex7
    public double getLastElement(){
        return stats.getLastElement();
    }
    
    //Added for ex7
    public double getPenultimateElement(){
        return stats.getPenultimateElement();
    }
    
    //Added for ex7
    public double getTrend(){
       double penultimateValue = stats.getPenultimateElement();
       double lastValue = stats.getLastElement();
       int count = stats.getCount();
       
       if(count < 2){
           return -2;
       }
       if(lastValue > penultimateValue){
           return 1;
       }
       if(lastValue < penultimateValue){
           return -1;
       }
       if(lastValue == penultimateValue){
           return 0;
       }
       
              
       return -5;
    }

    
}
