/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

/**
 *
 * @author p0073862
 */
public abstract class Animal {
    private String name;
    private String description;
    private String covering;
    private AttackBehaviour attack;

    public Animal (String name) {
       this.name = name;
    }
    
    public abstract void move();
    public void performAttack() {attack.attack();}
    
    public void setAttack(AttackBehaviour attack) {
        this.attack = attack;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    
    public void setCovering(String covering) {
        this.covering = covering;
    }
    public String getCovering() {
        return covering;
    }
    
    public String getName() {
        return name;
    }
}