/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import java.io.Serializable;

/**
 *
 * @author Taleb
 */
 public abstract class Player  implements Runnable,Serializable{
    public String name;
    public int currentScore;
    public IllegalGameMove illegagamemove;
    public IllegalSquareName illegalsquarename;
    public int nOfShields;
    public abstract PlayerMove GetPlayerMove();
    @Override
    public abstract void run();
    
}
