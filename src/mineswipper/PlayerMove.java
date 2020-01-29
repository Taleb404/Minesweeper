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

public class PlayerMove   implements Serializable{
    public Player player;
    public Square square;
    public MoveType type;
    public int MoveTime;
    public  MoveResult result;

    public PlayerMove() {
    result = new MoveResult();
    }
    
}
