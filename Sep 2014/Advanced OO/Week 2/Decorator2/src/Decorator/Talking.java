/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Decorator;

/**
 *
 * @author User
 */
public class Talking extends AnimalDecorator {
    public Talking(Animal animal) {
        super(animal);
    }
    public String getDescription() {
        return "talking " + getBaseAnimal().getDescription();
    }
    public double getWeight() {
        //all that hydrogen reduces the weight by 20%
        return getBaseAnimal().getWeight()-0.5;
    }
}
