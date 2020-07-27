/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Decorator;

/**
 *
 * @author p0073862
 */
public class Winged extends AnimalDecorator {
    public Winged(Animal animal) {
        super(animal);
    }
    public String getDescription() {
        return "winged " + getBaseAnimal().getDescription();
    }
    public double getWeight() {
        //wings add 10% to an animal's weight
        return 1.1*getBaseAnimal().getWeight();
    }
}
