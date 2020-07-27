/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

/**
 *
 * @author p0073862
 */
public abstract class Arthropod extends Animal {
    public Arthropod(String name) {
        super(name);
        setCovering("an exoskeleton");
    }
}