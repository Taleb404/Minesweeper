package mineswipper;
import java.util.Scanner;

public class ConsolePlayer extends Player{

    public ConsolePlayer(String name) 
    {
        this.name = name;
        this.currentScore = 0;
        illegagamemove = new IllegalGameMove();
        illegalsquarename = new IllegalSquareName();
     }
    
    
    @Override
    public PlayerMove GetPlayerMove() {
        
        PlayerMove temp = new PlayerMove();
        System.out.print(name + " Turn : ");
        Scanner s = new Scanner(System.in);
        String move = s.nextLine();
        char[] tempchararray = move.toCharArray();
        int x,y;
        if(tempchararray[0]!='-')
        {
           x = Character.getNumericValue(tempchararray[0]);
           char c=tempchararray[1];
           y =(int)c - 65;;
        }
        else
        {
            x = Character.getNumericValue(tempchararray[1]);
            char c=tempchararray[2];
            y =(int)c - 65;
        }
        
        temp.player = this;
        
        Square tempsquare = new Square();
        tempsquare.setX(x);
        tempsquare.setY(y);
        
        MoveType tempmovetype = new MoveType();
        if(tempchararray[0]=='-')
           tempmovetype.type="Mark";
        else
            tempmovetype.type = "Reveal";
        
        temp.square = tempsquare;
        temp.type = tempmovetype;
        
       return temp;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
