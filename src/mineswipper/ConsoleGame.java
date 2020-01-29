/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import static java.lang.System.exit;

/**
 *
 * @author Taleb
 */
public class ConsoleGame extends NormalGame{

    @Override
    public void initGame() {
        super.initGame(); //To change body of generated methods, choose Tools | Templates. 
        
       PlayerMove newmove;
       grid.printGrid();
       while(true)
       {
            newmove= currentPlayer.GetPlayerMove();
            boolean checkmove = AcceptMove(newmove);
            if(checkmove)
            {
                if(checkifwin())
                {
                    System.out.println(currentPlayer.name + " is Winner");
                    grid.printGrid();
                     System.out.println( currentPlayer.name + " Score : " + currentPlayer.currentScore);
                    exit(1);
                }
                   if(grid.square[newmove.square.getX()][newmove.square.getY()].isMine())
                   {
                       System.out.println(currentPlayer.name + " is Loser");
                    grid.printGrid();
                     System.out.println( currentPlayer.name + " Score : " + currentPlayer.currentScore);
                   exit(1);
                   }
                   System.out.println();
                grid.printGrid();
                System.out.println();
                System.out.println( currentPlayer.name + " Score : " + currentPlayer.currentScore);
                currentPlayer = currentRules.DecideNextPlayer(moves);   
            }
       }
    }
     
    
    @Override
    public boolean AcceptMove(PlayerMove move) {
        return super.AcceptMove(move);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }

     
}
