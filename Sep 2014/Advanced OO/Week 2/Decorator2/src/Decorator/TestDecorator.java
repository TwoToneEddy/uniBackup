package Decorator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p0073862
 */
public class TestDecorator {

    public static void main(String[] args) {
        Animal q = new Winged (new Serpent("Q"));
        describe(q);
        Animal h = new FireBreathing(new Winged (new Hamster("H")));
        describe(h);
        Animal z = new Winged(new Talking(new FireBreathing((new Rhinoceros("Z")))));
        describe(z);
        Animal x = new Winged(new FireBreathing(new Talking((new Rhinoceros("X")))));
        describe(x);
        
    }

    public static void describe(Animal animal)
    {
        System.out.print("I am " + animal.getName() + " the " + animal.getDescription() + ". ");
        System.out.println("I weigh " + animal.getWeight() + " Kg.");
    }
}

