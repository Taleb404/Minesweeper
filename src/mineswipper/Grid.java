/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Taleb
 */
public class Grid  implements Serializable{
    
  public  Square square[][];
    Mine mines[];
    Game currentGame;
    int n , m;
    int numberOfMines;
    int numberOfShields;
    public int getN()
    {
        return n;
    }
    public int getM()
    {
        return m;
    }
    
    public void setNumberOfShields(int numberOfShields) {
        this.numberOfShields = numberOfShields;
    }

    public int getNumberOfShields() {
        return numberOfShields;
    }


    public int getNumberOfMines() {
        return numberOfMines;
    }
    public void setN(int n) {
        this.n = n;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }
    Grid()
    {
        n = 8;
        m = 8;
       // numberOfMines = (int)((n*m)*0.10);
       numberOfMines =6;
       numberOfShields =6;
    }
    

    
    public void initGrid()
    {
    
       square = new Square[n][m];
       mines = new Mine[numberOfMines];
       int i,j;
     
     for(i=0;i<numberOfMines;i++)
     {
          Random r= new Random();
          int XRandom = r.nextInt(n);
          
          Random rr= new Random();
          int YRandomm = rr.nextInt(m) ;
          
          char c = (char)((char)65+YRandomm );
          mines[i] = new Mine();
          String tempMineId = XRandom + "" + c;
          mines[i].setId(tempMineId);
          square[XRandom][YRandomm] = new Square();
          square[XRandom][YRandomm].setX(XRandom);
          square[XRandom][YRandomm].setY(YRandomm);
          SquareStatus s = new SquareStatus();
          s.currentStatus = SquareStatus.status.Closed;
          square[XRandom][YRandomm].setCurrentSquareStatus(s);
          square[XRandom][YRandomm].setMine(mines[i]);
          
          //print Mines coordinates for debug proupuse
          System.out.println(XRandom + " Mine " + c );
     }

     //#2 add Sheilds loop
       
     for(i=0;i<numberOfShields;i++)
     {
          Random r= new Random();
          int XRandom = r.nextInt(n);
          
          Random rr= new Random();
          int YRandomm = rr.nextInt(m) ;
          
          while(square[XRandom][YRandomm]!=null)
          {
               XRandom = r.nextInt(n);
               YRandomm = rr.nextInt(m) ;
          }
          char c = (char)((char)65+YRandomm );
          
          String tempShieldId = XRandom + "" + c;
          
          //create a new Shield
          Shield tempShield = new Shield();
          tempShield.setId(tempShieldId);
          
          square[XRandom][YRandomm] = new Square();
          square[XRandom][YRandomm].setX(XRandom);
          square[XRandom][YRandomm].setY(YRandomm);
          SquareStatus s = new SquareStatus();
          s.currentStatus = SquareStatus.status.Closed;
          square[XRandom][YRandomm].setCurrentSquareStatus(s);
          square[XRandom][YRandomm].setShield(tempShield);
          
          //print Shields coordinates for debug proupuse
          System.out.println(XRandom + "  Shield  " + c );
     }
     
     for(i=0;i<n;i++)
     {
         for(j=0;j<m;j++)
         {
             if(square[i][j]==null)
             {
             Square temp = new Square();
             temp.setX(i);
             temp.setY(j);
             temp.setCurrentSquareStatus(new SquareStatus());
             square[i][j] = temp;
                     
             //System.out.println(i + " " + c );
             }
         }
     }
      
    }
    
        public void printGrid()
        {
            System.out.print("#|");
            for(int col=0;col<m;col++)
            {
                    System.out.print(" " + ((char)((char)col+65)) +( (col!=m-1)?"|":""));
            }
            System.out.println("");
            for(int i=0;i<n;i++)
            {
                System.out.print(i + "|");
                for(int j=0;j<m;j++)
                {
                    Square tmp = square[i][j] ;
                    SquareStatus tempstate = tmp.getCurrentSquareStatus();
                    if(null!=tempstate.getStatus())
                    switch (tempstate.getStatus()) {
                        case Closed:
                            System.out.print(" O ");
                            break;
                        case Marked:
                            System.out.print(" P ");
                            break;
                        case oneMine:
                            System.out.print(" 1 ");
                            break;
                         case twoMines:
                            System.out.print(" 2 ");
                            break;                           
                         case threeMines:
                            System.out.print(" 3 ");
                            break;                           
                         case fourMines:
                            System.out.print(" 4 ");
                            break;                           
                         case fiveMines:
                            System.out.print(" 5 ");
                            break;                           
                        case sixMines:
                            System.out.print(" 6 ");
                            break;         
                        case sevenMines:
                            System.out.print(" 7 ");
                            break;                            
                        case eightMines:
                            System.out.print(" 8 ");
                            break;
                        case OpenedEmpty:
                            System.out.print("   ");
                            break;
                        case OpenedMine:
                            System.out.print(" B ");
                            break;
                        default:
                            break;
                    }
                }
                System.out.println();
            }
        }
        
         
    public void AcceptMove(PlayerMove move)
    {
        
    }
    
}
