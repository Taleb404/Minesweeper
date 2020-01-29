/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import MineGui.MainGui;


public class MineSwipper {

    
    public static void main(String[] args)  
    {
        
    NormalGame newgame= new MainGui();
    Player guiPlayer = new GuiPlayer("Taleb");
    AutoPlayer autoPlayer = new RandomAIPlayer(newgame.grid.getN(), newgame.grid.getM());
    
    newgame.players.add(guiPlayer);
    newgame.players.add(autoPlayer);
    newgame.initGame();
    
    }
    
}
