/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Taleb
 */

public class NormalGame extends Game implements Serializable {

    public boolean revealFlag;
    
    public boolean enableautoreveal;
    public boolean showtheminesonlose;
    
    public boolean canUseShields ;
    
     class DefaultRules extends GameRules  implements Serializable
    {
         
        public DefaultRules() 
        {
            isNumericValue = new int[9];
            for(int i = 0;i<9;i++)
                isNumericValue[i] = i;
            EmptySquare = 10;
            AutoReveal = 1;
            MarkIsMine = 5;
            MarkIsNotMine = -1;
            OneNotMarkedScore = 100;
            enableautoreveal = true;
            showtheminesonlose = true;
        }
         
        @Override
        public int GetScoreChange(List<PlayerMove> moves)
        {
            int x = 0;
            for(int i=0;i<moves.size();i++)
            {
                if(moves.get(i).player == currentPlayer)
                x+= moves.get(i).result.getScoreChange();
            }
            //System.out.print(x);
            return x;
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Player DecideNextPlayer(List<PlayerMove> moves) {
            
            if(moves.size()==0 && players.size()>1)
            {
                if(currentPlayer ==  players.get(0))
                currentPlayer = players.get(1);
                else
                    currentPlayer = players.get(0);
                return currentPlayer;
            }
            PlayerMove r = moves.get(moves.size()-1);
           
            for(int i = 0;i<players.size();i++)
            {
                Player tempPlayer =players.get(i);
                if(tempPlayer == r.player)
                {
                    if(  (i+1)  < players.size() )
                    currentPlayer = players.get(i+1);     
                    else
                        currentPlayer = players.get(0);
                    break;
                }
            }
            return currentPlayer;
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    public NormalGame()
    {
        grid = new Grid();
        players = new ArrayList<Player>();
        currentRules = new DefaultRules();   
       moves = new ArrayList<PlayerMove>();
       revealFlag = false;
       canUseShields = true;
    }
    
    public boolean checkifwin()
    {
        boolean emptySquare = true;
        int minesMultipleHunderd = 0;
        for(int i =0 ;i < grid.n;i++)
        {
            for(int j=0; j < grid.m;j++)
            {
                if(grid.square[i][j].getCurrentSquareStatus().currentStatus==SquareStatus.status.Closed  && 
                    !(grid.square[i][j].isMine()))
                {
                    emptySquare = false;
                    break;
                }
                if(grid.square[i][j].getCurrentSquareStatus().currentStatus==SquareStatus.status.Marked && 
                    !(grid.square[i][j].isMine())    )
                {
                    emptySquare = false;
                    break;
                }
                    if(grid.square[i][j].getCurrentSquareStatus().currentStatus==SquareStatus.status.Closed && 
                    (grid.square[i][j].isMine()))
                {
                    minesMultipleHunderd+=currentRules.OneNotMarkedScore;
                }
            }
        }
        if(emptySquare)
        {
            currentPlayer.currentScore += minesMultipleHunderd;
             ShowAllMines();
        }
        return emptySquare;
    }
    
    public void ShowAllMines()
    {
        SquareStatus tempSquareStatus = new SquareStatus();
            for(int i = 0 ; i < grid.n ; i++)
                {
                    for(int j = 0 ; j < grid.m ; j++)
                    {
                        if(grid.square[i][j].isMine())
                        {
                        tempSquareStatus.currentStatus = SquareStatus.status.OpenedMine;
                        grid.square[i][j].setCurrentSquareStatus(tempSquareStatus);                          
                        }
                    }
                }
    }
    
    public void setShieldsForPlayers(int x,int y)
    {
            players.get(0).nOfShields = x;
            if(players.get(1)!=null)
                {
                  players.get(1).nOfShields = y;
                }
    }
    
    @Override
    public void initGame() {
        grid.initGrid();
      for(int i = 0 ; i < players.size();i++)
             {
              players.get(i).currentScore = 0;
             }
       moves.clear();
        currentPlayer = players.get(0);
    }
    @Override
    public boolean AcceptMove(PlayerMove move) {
      
       try
        {
         int x = move.square.getX();
         int y = move.square.getY();
         MoveType tempMoveType = move.type;
         SquareStatus tempSquareStatus = new SquareStatus();
         String stringmovetype = tempMoveType.getType();
         Square squarexy = new Square();
         try
         {
             squarexy = grid.square[x][y];
            grid.square[x][y].setPlayerMove(currentPlayer);
         }
         catch(ArrayIndexOutOfBoundsException e)
         {
            //System.out.println("Console Gmame 96 out of boundries");
            return false;
         }

        /*/
                Mark Flag Proccessing On Console View
        /*/
        
         if(stringmovetype.equals("Mark"))
         {
             if(squarexy.getCurrentSquareStatus().currentStatus == SquareStatus.status.Closed)
             {
                 tempSquareStatus.currentStatus = SquareStatus.status.Marked;
                 this.grid.square[x][y].setCurrentSquareStatus(tempSquareStatus);
                 if(squarexy.isMine())
                 {
                     move.result.setScoreChange(currentRules.MarkIsMine);
                     ApplyPlayerMove(move);
                 }
                 else{
                     move.result.setScoreChange(currentRules.MarkIsNotMine);
                     move.result.setNewStatus(tempSquareStatus);
                 ApplyPlayerMove(move);
                 }
                 return true;
             }
              if(squarexy.getCurrentSquareStatus().currentStatus == SquareStatus.status.Marked)
                 {System.out.println("Sorry You can't Mark this Square !");
                 tempSquareStatus.currentStatus = SquareStatus.status.Closed;
                 this.grid.square[x][y].setCurrentSquareStatus(tempSquareStatus);
                 return true;
                 }
             else
          {
                System.out.println("Sorry You can't Mark this Square !");
              return false;
          }
         }
         
         
         /*/
                Reveal Move Proccessing On Console View
        /*/
         
         if(stringmovetype.equals("Reveal"))
         {
             
             if(squarexy.getCurrentSquareStatus().currentStatus==SquareStatus.status.Marked)
            {
                System.out.println(currentPlayer.name + "this Square is Marked with a Flag please chooce another one or remove the Mark Flag  !");
                return false;
            }
             if(!(squarexy.getCurrentSquareStatus().currentStatus==SquareStatus.status.Closed))
            {
                return false;
            }
            
            if(squarexy.isMine() && canUseShields==false)
            {
                if(showtheminesonlose)
               ShowAllMines();
                return true;
            }
            
                 if(squarexy.isShield() && canUseShields==false)
                {
                    return false;
                }
                 
                if(squarexy.isShield() && canUseShields==true)
            {
                tempSquareStatus.currentStatus = SquareStatus.status.Shield;
                grid.square[x][y].setCurrentSquareStatus(tempSquareStatus);
                currentPlayer.nOfShields +=1;
                move.result.setNewStatus(tempSquareStatus);
                ApplyPlayerMove(move);
                return true;
            }
                
            
     
         
                         
            if(squarexy.isMine() && canUseShields==true && currentPlayer.nOfShields >0)
            {
               tempSquareStatus.currentStatus = SquareStatus.status.OpenedMine;
                grid.square[x][y].setCurrentSquareStatus(tempSquareStatus);
                
                move.result.setNewStatus(tempSquareStatus);
                ApplyPlayerMove(move);
                return true;
            }
                       
             if(squarexy.isMine() && canUseShields==true && currentPlayer.nOfShields<=0)
            {
                if(showtheminesonlose)
               ShowAllMines();
                return true;
            }
             
     
           int numberOfMines = getNumberOfMinesaround(x, y);
     
            if(numberOfMines>0)
            {
                switch(numberOfMines)
                {
                    case 1:
                        tempSquareStatus.currentStatus = SquareStatus.status.oneMine;
                        break;
                    case 2:
                        tempSquareStatus.currentStatus = SquareStatus.status.twoMines;
                        break;
                    case 3:
                        tempSquareStatus.currentStatus = SquareStatus.status.threeMines;
                        break;
                    case 4:
                        tempSquareStatus.currentStatus = SquareStatus.status.fourMines;
                        break;
                    case 5:
                        tempSquareStatus.currentStatus = SquareStatus.status.fiveMines;
                        break;
                    case 6:
                        tempSquareStatus.currentStatus = SquareStatus.status.sixMines;
                        break;
                    case 7:
                        tempSquareStatus.currentStatus = SquareStatus.status.sevenMines;
                        break;
                    case 8:
                        tempSquareStatus.currentStatus = SquareStatus.status.eightMines;
                        break;
                }
                grid.square[x][y].setCurrentSquareStatus(tempSquareStatus);
                if(!revealFlag)
                move.result.setScoreChange(currentRules.isNumericValue[numberOfMines]);
                else
                 move.result.setScoreChange(currentRules.AutoReveal);
                 move.result.setNewStatus(tempSquareStatus);
                ApplyPlayerMove(move);
                 return true;
            }
            else
            {
                if(enableautoreveal)
                {
                    floodfill(x, y);
                move.result.setScoreChange(currentRules.AutoReveal);
               move.result.setNewStatus(tempSquareStatus);
                ApplyPlayerMove(move);
                 revealFlag = false;
                 currentPlayer.currentScore+=currentRules.EmptySquare;
                }
                else
                {
                    tempSquareStatus.currentStatus = SquareStatus.status.OpenedEmpty;
                    grid.square[x][y].setCurrentSquareStatus(tempSquareStatus);
                       move.result.setScoreChange(currentRules.EmptySquare);
               move.result.setNewStatus(tempSquareStatus);
                ApplyPlayerMove(move);
                }
        
                 return true;
             }
         }
        }
        catch(Exception e)
        {
            return false;
        }
     return true;
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void ApplyPlayerMove(PlayerMove move) {
        
            moves.add(move);
            currentPlayer.currentScore = currentRules.GetScoreChange(moves);
            
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public void floodfill(int x , int y)
    {
            revealFlag = true;
            SquareStatus tempSquareStatus = new SquareStatus();

            PlayerMove tempmove = new PlayerMove();
            tempmove.player = currentPlayer;
            MoveType tempMoveType = new MoveType();
            tempMoveType.type = "Reveal";
            tempmove.type = tempMoveType;
            tempmove.square = grid.square[x][y];           

           
        if(x < grid.n && y < grid.m  && x>=0 && y >=0)
        if(getNumberOfMinesaround(x,y)==0 )
        {
           tempSquareStatus.currentStatus = SquareStatus.status.OpenedEmpty;
           grid.square[x][y].setCurrentSquareStatus(tempSquareStatus);
           for(int i = -1 ; i < 2 ; i++)
               for(int j = -1 ; j < 2 ; j++)
               {
                   try{
                       if(grid.square[x+i][y+j].getCurrentSquareStatus().getStatus() == SquareStatus.status.Closed)
                   {
                       tempmove.square = grid.square[x+i][y+j];
                       AcceptMove(tempmove);
                   }
                   }
                   catch(ArrayIndexOutOfBoundsException e)
                   {}
                
               }
                   
        }
        else
        {
            //System.out.println("A");
            AcceptMove(tempmove);
        }
    }
    
    public int getNumberOfMinesaround( int x , int y)
    {    
            Square xtemp = new Square();
            int numberOfMines = 0;
            for(int i = -1 ; i < 2 ; i++)
               for(int j = -1 ; j < 2 ; j++)
               {
                   try{
                    xtemp = grid.square[x+i][y+j];
                    if(xtemp.isMine())
                    numberOfMines++;
                   }
                   catch(ArrayIndexOutOfBoundsException e){}
               }
            return numberOfMines;
    }
}
