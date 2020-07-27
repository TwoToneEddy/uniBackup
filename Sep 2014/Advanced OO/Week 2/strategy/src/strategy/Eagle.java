/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

/**
 *
 * @author p0073862
 */
public class Eagle extends Animal {

    public Eagle(String name) {
        super(name);
        setCovering("feathers");
        setDescription("a raptor");
         setAttack(new SwoopAttack());
    }

    public void move() {
        System.out.println("fly");
    }
}