/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

/**
 *
 * @author p0073862
 */
public class TestStrategy {

    public static void main(String[] args) {
        Animal sidney = new Snake("Sidney");
        Animal eddie = new Eagle("Eddie");
        doAttack(sidney);
        doAttack(eddie);
    }

    private static void doAttack(Animal animal) {
        System.out.print("Hello I'm " + animal.getName());
        System.out.print(" I'm " + animal.getDescription());
        System.out.println(" I'm covered in " + animal.getCovering() + " and...");
        animal.performAttack();
    }

}
