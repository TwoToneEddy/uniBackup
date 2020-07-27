/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

/**
 *
 * @author p0073862
 */
public class Spider extends Arthropod{

    public Spider(String name) {
        super(name);
        setDescription("a slithering reptile");
        setAttack(new PoisonBiteAttack());
    }

    public void move() {
        System.out.println("slither");
    }
}
