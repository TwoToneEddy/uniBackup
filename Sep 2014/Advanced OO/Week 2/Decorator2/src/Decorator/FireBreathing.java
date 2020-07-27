/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Decorator;

/**
 *
 * @author p0073862
 */
public class FireBreathing extends AnimalDecorator {
    public FireBreathing(Animal animal) {
        super(animal);
    }
    public String getDescription() {
        return "fire breathing " + getBaseAnimal().getDescription();
    }
    public double getWeight() {
        //all that hydrogen reduces the weight by 20%
        return 0.8* getBaseAnimal().getWeight();
    }
}
