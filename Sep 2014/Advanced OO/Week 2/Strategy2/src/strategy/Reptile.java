/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

/**
 *
 * @author p0073862
 */
public abstract class Reptile extends Animal {

    /** Creates a new instance of Reptile */
    public Reptile(String name) {
        super(name);
        setCovering("scales");
    }
}

