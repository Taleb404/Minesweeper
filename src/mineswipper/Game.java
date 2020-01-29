/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Taleb
 */
public abstract class Game implements Serializable {
    public  List<Player> players;
    public Player currentPlayer;
    public List<PlayerMove> moves;
    public GameRules currentRules;
    public Grid grid;

    
    public abstract class GameRules  implements Serializable  {
                 
         public int isNumericValue[];
         public int EmptySquare ;
         public int AutoReveal ;
         public int MarkIsMine;
         public int MarkIsNotMine;
         public int OneNotMarkedScore ;
    public abstract int GetScoreChange(List<PlayerMove> moves);
    public abstract Player DecideNextPlayer(List<PlayerMove> moves);
    }

    public abstract void initGame();
    public abstract boolean AcceptMove(PlayerMove move);
    public abstract void ApplyPlayerMove(PlayerMove move);
    
    
}
