/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

/**
 *
 * @author p0073862
 */
public class PoisonBiteAttack implements AttackBehaviour {

    public void attack() {
        //in reality we would put something more complicated here!
        System.out.println("I just bit you - you're poisoned!");
    }
}
