/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

/**
 *
 * @author Taleb
 */
public abstract class AutoPlayer extends Player {

public boolean canUseShields;
    public void setname(String name)
    {
        super.name = name;
        this.nOfShields = 0;
    }
    
    @Override
    public abstract PlayerMove GetPlayerMove();

    @Override
    public abstract void run();
    
    
}
