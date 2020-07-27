/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

/**
 *
 * @author User
 */
public class Tiger extends Animal {
    
    public Tiger(String name) {
        super(name);
        setCovering("Fur");
        setDescription("Big cat");
         setAttack(new PounceAttack());
    }

    public void move() {
        System.out.println("Prowl");
    }
    
}
