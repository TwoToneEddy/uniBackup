/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Decorator;

/**
 *
 * @author p0073862
 */
public abstract class AbstractAnimal implements Animal {

    private String name;
    private String description;
    private double weight;

    public AbstractAnimal(String nameIn, String descIn, double weightIn) {
        name = nameIn;
        description = descIn;
        weight = weightIn;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public double getWeight() {return weight;}
}

