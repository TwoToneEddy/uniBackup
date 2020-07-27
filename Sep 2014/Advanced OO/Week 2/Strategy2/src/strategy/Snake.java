/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

/**
 *
 * @author p0073862
 */
public class Snake extends Reptile{

    /** Creates a new instance of Snake */
    public Snake(String name) {
        super(name);
        setAttack(new PoisonBiteAttack());
        setDescription("a slithering reptile");
    }

    public void move() {
        System.out.println("slither");
    }
}
