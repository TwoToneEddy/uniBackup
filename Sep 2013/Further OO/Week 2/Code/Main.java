/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package week2;

/**
 *
 * @author 09092543
 */
import java.util.Scanner;

public class Main {

    private static Stats myStats = new ArrayStats();
    private static TrendBoundableStats myArrayStats = new BStats(new ArrayStats());
    private static TrendBoundableStats mySimpleStats = new BStats(new SimpleStats());
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String option;
        do {
            System.out.println("Options are:");
            System.out.println("AS: Add a value to the SimpleStats Collection");
            System.out.println("PS: Print out SimpleStats statistics");
            System.out.println("AA: Add a value to the ArrayStats Collection");
            System.out.println("PA: Print out ArrayStats statistics");
            System.out.print("Enter your option > ");
            option = scan.nextLine();

            if (option.equalsIgnoreCase("AS")) {
                addValue(mySimpleStats);
            }

            if (option.equalsIgnoreCase("PS")) {
                printStats(mySimpleStats);
            }
            
            if (option.equalsIgnoreCase("AA")) {
                addValue(myArrayStats);
            }

            if (option.equalsIgnoreCase("PA")) {
                printStats(myArrayStats);
            }
            
            System.out.println();

        } while (!option.equalsIgnoreCase("Q"));

    }

    private static void addValue(Addable stats) {
        System.out.print("Enter value to be added > ");
        double d = scan.nextDouble();
        stats.add(d);
    }

    private static void printStats(TrendBoundableStats stats) {
        System.out.println("Min = " + stats.getMin());
        System.out.println("Max = " + stats.getMax());
        System.out.println("Count = " + stats.getCount());
        System.out.println("Sum = " + stats.getSum());
        System.out.println("Trend = " + stats.getTrend());
        if (stats.getCount() > 0) {
            System.out.println("Average = " + stats.getAverage());
        }
    }
}

