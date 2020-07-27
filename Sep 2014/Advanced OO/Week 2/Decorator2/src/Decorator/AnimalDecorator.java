/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Decorator;

/**
 *
 * @author p0073862
 */
public abstract class AnimalDecorator implements Animal {
    private Animal baseAnimal;

    public AnimalDecorator(Animal animal) {
        baseAnimal = animal;
    }

    public Animal getBaseAnimal() {return baseAnimal;}

    public String getName() {return baseAnimal.getName();}
    public abstract String getDescription();
    public abstract double getWeight();
}
