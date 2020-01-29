/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MineGui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import mineswipper.AutoPlayer;
import mineswipper.MoveType;
import mineswipper.PlayerMove;
import mineswipper.Square;
import mineswipper.SquareStatus;

/**
 * FXML Controller class
 *
 * @author Taleb
 */
public class PreviewGuiController extends Thread implements Initializable  {

    @FXML
    Button myNewGameButton;

    @FXML
    GridPane myGridPane;

    @FXML
    private Label ScoreLabel;

    @FXML
    private HBox winandlosehbox;

    @FXML
    private Label winandloselabel;

    @FXML
    private Label shields1label;
    
    @FXML
    private Label shields2label;
    
    @FXML
    private HBox shileds2;
    
    @FXML
    public Label TimeLabel;
    
    @FXML
    public Button backbutton ;
    
    Button[][] matrix;

    int length;

    int width;

    boolean loseFlag;

    boolean winFlag;

    public int MoveTime ;

    public PreviewGuiController() 
    {
        loseFlag = false;
        winFlag = false;
        width = GuiGame.guiGame.grid.getM();
        length = GuiGame.guiGame.grid.getN();
    }
    
        public GridPane GenerateMatrixOfButtons() {
       
        width = GuiGame.guiGame.grid.getM();
        length = GuiGame.guiGame.grid.getN();
        myGridPane.setPadding(new Insets(40, 0, 0, 275));
        myGridPane.setHgap(2);
        myGridPane.setVgap(2);
        matrix = new Button[length][width];
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                matrix[x][y] = new Button();
                String coordinate = x + "" + ((char) (y + 65));
                matrix[x][y].setId(coordinate);
                matrix[x][y].setOnMousePressed(new EventHandler<MouseEvent>() {

                    
                    //Mark move
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.isSecondaryButtonDown()) {
                            Button xButton = (Button) mouseEvent.getSource();
                            String move = xButton.getId();
                            PlayerMove temp = new PlayerMove();
                            Square tempsquare = new Square();
                            char[] tempchararray = move.toCharArray();
                            int x, y;
                            x = 0;
                            if (Character.isDigit(move.charAt(1))) {
                                System.out.println("AAAAAAAAAAAAAaaaa");
                                x += Character.getNumericValue(tempchararray[0]) * 10;
                                x += Character.getNumericValue(tempchararray[1]);
                                char c = tempchararray[2];
                                y = (int) c - 65;
                            } else {
                                x = Character.getNumericValue(tempchararray[0]);
                                char c = tempchararray[1];
                                y = (int) c - 65;
                            }
                            System.out.println(move + x + " " + y);
                            tempsquare.setX(x);
                            tempsquare.setY(y);
                            temp.player = GuiGame.guiGame.currentPlayer;
                            MoveType tempmovetype = new MoveType();
                            tempmovetype.type = "Mark";
                            temp.square = tempsquare;
                            temp.type = tempmovetype;
                            boolean checkmove = GuiGame.guiGame.AcceptMove(temp);
                            if (checkmove) {
                                updateGuiMatrix();
                            }
                        }
                    }

                    public void updateGuiMatrix() {
                        SquareStatus squarestatus = new SquareStatus();
                        int n = GuiGame.guiGame.grid.getN();
                        int m = GuiGame.guiGame.grid.getM();
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                squarestatus = GuiGame.guiGame.grid.square[i][j].getCurrentSquareStatus();
                                if (squarestatus.getStatus() == SquareStatus.status.OpenedEmpty) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("emptySquare");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.oneMine) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("oneMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.twoMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("twoMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.threeMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("threeMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.fourMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("fourMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.fiveMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("fiveMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.sixMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("sixMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.sevenMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("sevenMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.eightMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("eightMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.Marked) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("Marked");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.Closed) {
                                    matrix[i][j].getStyleClass().removeAll("Marked");
                                    matrix[i][j].getStyleClass().addAll("buttons");
                                }
                                if (loseFlag) {
                                    if (squarestatus.getStatus() == SquareStatus.status.OpenedMine) {
                                        matrix[i][j].getStyleClass().removeAll("buttons");
                                        matrix[i][j].getStyleClass().addAll("boMine");
                                    }
                                }
                                if (winFlag) {
                                    if (squarestatus.getStatus() == SquareStatus.status.OpenedMine) {
                                        matrix[i][j].getStyleClass().removeAll("buttons");
                                        matrix[i][j].getStyleClass().addAll("wiMine");
                                    }
                                }
                            }
                        }
                        String theScore = "";
                        for (int ii = 0; ii < GuiGame.guiGame.players.size(); ii++) {
                            int score = GuiGame.guiGame.players.get(ii).currentScore;
                            theScore += GuiGame.guiGame.players.get(ii).name + " Score : " + score + "\n";
                        }
                        theScore += "--------------\nPlayer Turn : \n" + GuiGame.guiGame.currentPlayer.name;
                        ScoreLabel.setText(theScore);
                         shields1label.setText(GuiGame.guiGame.players.get(0).name + " Shields : " + 
                                        GuiGame.guiGame.players.get(0).nOfShields);
                                if(GuiGame.guiGame.players.size()>1)
                                {
                                    shields2label.setText(GuiGame.guiGame.players.get(1).name + " Shields : " + 
                                    GuiGame.guiGame.players.get(1).nOfShields);
                                }
                    }
                });
                
                //Reveal Move 
                matrix[x][y].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        Button xButton = (Button) event.getSource();
                        String move = xButton.getId();
                        PlayerMove temp = new PlayerMove();
                        Square tempsquare = new Square();
                        char[] tempchararray = move.toCharArray();
                        int x, y;
                        x = 0;
                        if (Character.isDigit(move.charAt(1))) {
                           // System.out.println("AAAAAAAAAAAAAaaaa");
                            x += Character.getNumericValue(tempchararray[0]) * 10;
                            x += Character.getNumericValue(tempchararray[1]);
                            char c = tempchararray[2];
                            y = (int) c - 65;
                        } else {
                            x = Character.getNumericValue(tempchararray[0]);
                            char c = tempchararray[1];
                            y = (int) c - 65;
                        }
                        System.out.println(move + x + " " + y);
                        tempsquare.setX(x);
                        tempsquare.setY(y);
                        temp.player = GuiGame.guiGame.currentPlayer;
                        MoveType tempmovetype = new MoveType();
                        tempmovetype.type = "Reveal";
                        temp.square = tempsquare;
                        temp.type = tempmovetype;
                        
                        //###
                         temp.MoveTime = MoveTime;
                  
                          GuiGame.guiGame.canUseShields = true;
                        boolean checkmove = GuiGame.guiGame.AcceptMove(temp);
                        System.out.println(checkmove + " " + GuiGame.guiGame.currentPlayer.nOfShields);
                        if (checkmove) {
                          
                            if (GuiGame.guiGame.checkifwin()) {
                               
                                winandloselabel.setText(GuiGame.guiGame.currentPlayer.name + " is Winner");
                                GuiGame.guiGame.grid.printGrid();
                                winandlosehbox.setVisible(true);
                                System.out.println(GuiGame.guiGame.currentPlayer.name + " Score : " + GuiGame.guiGame.currentPlayer.currentScore);
                                winFlag = true;
                                myGridPane.setDisable(winFlag);
                                updateGuiMatrix();
                                return;
                            }
                        
                                 if (GuiGame.guiGame.grid.square[x][y].isShield() &&GuiGame.guiGame.canUseShields==true) 
                            {
                                updateGuiMatrix();
                            }
                            
                            if (GuiGame.guiGame.grid.square[x][y].isMine() &&GuiGame.guiGame.canUseShields==true
                                     && GuiGame.guiGame.currentPlayer.nOfShields>0) {
                                
                                SquareStatus tempSquarestatus = new SquareStatus();
                                tempSquarestatus.setStatus(SquareStatus.status.OpenedMine);
                                GuiGame.guiGame.grid.square[x][y].setCurrentSquareStatus(tempSquarestatus);
                                GuiGame.guiGame.currentPlayer.nOfShields -=1;
                                updateGuiMatrix();
                           
                            }
                            
                            
                            if ((GuiGame.guiGame.grid.square[x][y].isMine() &&GuiGame.guiGame.canUseShields==false)
                               ||(GuiGame.guiGame.grid.square[x][y].isMine() &&GuiGame.guiGame.canUseShields==true
                                    && GuiGame.guiGame.currentPlayer.nOfShields<=0 )) {
                                loseFlag = true;
                                myGridPane.setDisable(loseFlag);
                                winandlosehbox.setVisible(loseFlag);
                                winandloselabel.setText(GuiGame.guiGame.currentPlayer.name + " GameOver !");
                                if(GuiGame.guiGame.showtheminesonlose)
                                {
                                    GuiGame.guiGame.ShowAllMines();
                                }
                                SquareStatus tempSquarestatus = new SquareStatus();
                                tempSquarestatus.setStatus(SquareStatus.status.OpenedMine);
                                GuiGame.guiGame.grid.square[x][y].setCurrentSquareStatus(tempSquarestatus);
                                updateGuiMatrix();
                                return;
                            }
                            GuiGame.guiGame.grid.printGrid();
                           
                                GuiGame.guiGame.currentPlayer = GuiGame.guiGame.currentRules.DecideNextPlayer(GuiGame.guiGame.moves);
                            updateGuiMatrix();
                            
                            
                            if (GuiGame.guiGame.currentPlayer instanceof AutoPlayer) {
                                checkmove = false;
                                GuiGame.guiGame.canUseShields =   GuiGame.canautoplayeruseshield ;
                                while (!checkmove) {
                                    
                                    temp = GuiGame.guiGame.currentPlayer.GetPlayerMove();
                                    checkmove = GuiGame.guiGame.AcceptMove(temp);
                                    System.out.println("PPPPP  P  "+  GuiGame.canautoplayeruseshield);
                                    if (checkmove) {
                                          temp.MoveTime = MoveTime;
                                       x =  temp.square.getX();
                                       y =  temp.square.getY();
                                       
                                       
                                       /*
                                               System.out.println(" VVVVVsVVVVVV " + GuiGame.guiGame.currentPlayer.nOfShields + " " +
                                      GuiGame.guiGame.grid.square[x][y].isMine() + " NNNN " +GuiGame.guiGame.canUseShields 
                                               + " XXXXX " + x + " YYYYY " + y );*/
                                        if (GuiGame.guiGame.checkifwin()) {
                                          
                                            winandloselabel.setText(GuiGame.guiGame.currentPlayer.name + " is Winner");
                                            GuiGame.guiGame.grid.printGrid();
                                            winandlosehbox.setVisible(true);
                                            System.out.println(GuiGame.guiGame.currentPlayer.name + " Score : " + GuiGame.guiGame.currentPlayer.currentScore);
                                            winFlag = true;
                                            myGridPane.setDisable(winFlag);
                                            updateGuiMatrix();
                                            return;
                                        }
                                          
                                
                            
                            if (GuiGame.guiGame.grid.square[x][y].isMine() &&GuiGame.guiGame.canUseShields==true
                                     && GuiGame.guiGame.currentPlayer.nOfShields>0) {
                          
                                SquareStatus tempSquarestatus = new SquareStatus();
                                tempSquarestatus.setStatus(SquareStatus.status.OpenedMine);
                                GuiGame.guiGame.grid.square[x][y].setCurrentSquareStatus(tempSquarestatus);
                                GuiGame.guiGame.currentPlayer.nOfShields -=1;
                                updateGuiMatrix();
                          //  return;
                            }
                                        if ((GuiGame.guiGame.grid.square[x][y].isMine() &&GuiGame.guiGame.canUseShields==false)
                               ||(GuiGame.guiGame.grid.square[x][y].isMine() &&GuiGame.guiGame.canUseShields==true
                                    && GuiGame.guiGame.currentPlayer.nOfShields<=0)) {
                                            loseFlag = true;
                                            myGridPane.setDisable(loseFlag);
                                            winandlosehbox.setVisible(loseFlag);
                                            winandloselabel.setText(GuiGame.guiGame.currentPlayer.name + " GameOver !");
                                            SquareStatus tempSquarestatus = new SquareStatus();
                                            tempSquarestatus.setStatus(SquareStatus.status.OpenedMine);
                                            GuiGame.guiGame.grid.square[x][y].setCurrentSquareStatus(tempSquarestatus);
                                            updateGuiMatrix();
                                            return;
                                        }
                                        GuiGame.guiGame.grid.printGrid();
                                    }
                                }
                               
                                GuiGame.guiGame.currentPlayer = GuiGame.guiGame.currentRules.DecideNextPlayer(GuiGame.guiGame.moves);
                            updateGuiMatrix();
                            }
                        }
                    }

                    public void updateGuiMatrix() {
                        SquareStatus squarestatus = new SquareStatus();
                        int n = GuiGame.guiGame.grid.getN();
                        int m = GuiGame.guiGame.grid.getM();
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                squarestatus = GuiGame.guiGame.grid.square[i][j].getCurrentSquareStatus();
                                if (squarestatus.getStatus() == SquareStatus.status.OpenedEmpty) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("emptySquare");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.oneMine) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("oneMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.twoMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("twoMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.threeMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("threeMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.fourMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("fourMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.fiveMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("fiveMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.sixMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("sixMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.sevenMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("sevenMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.eightMines) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("eightMine");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.Marked) {
                                    matrix[i][j].getStyleClass().removeAll("buttons");
                                    matrix[i][j].getStyleClass().addAll("Marked");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.Closed) {
                                    matrix[i][j].getStyleClass().removeAll("Marked");
                                    matrix[i][j].getStyleClass().addAll("buttons");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.Shield) {
                                    matrix[i][j].getStyleClass().removeAll("Marked");
                                    matrix[i][j].getStyleClass().addAll("Shield");
                                }
                                if (squarestatus.getStatus() == SquareStatus.status.OpenedMine) {
                                        matrix[i][j].getStyleClass().removeAll("buttons");
                                        matrix[i][j].getStyleClass().addAll("boMine");
                                    }
                                if (loseFlag) {
                                    if (squarestatus.getStatus() == SquareStatus.status.OpenedMine) {
                                        matrix[i][j].getStyleClass().removeAll("buttons");
                                        matrix[i][j].getStyleClass().addAll("boMine");
                                    }
                                }
                                if (winFlag) {
                                    if (squarestatus.getStatus() == SquareStatus.status.OpenedMine) {
                                        matrix[i][j].getStyleClass().removeAll("buttons");
                                        matrix[i][j].getStyleClass().addAll("wiMine");
                                    }
                                }
                            
                            }
                            
                        }
                        String theScore = "";
                        for (int ii = 0; ii < GuiGame.guiGame.players.size(); ii++) {
                            int score = GuiGame.guiGame.players.get(ii).currentScore;
                            theScore += GuiGame.guiGame.players.get(ii).name + " Score : " + score + "\n";
                        }
                        theScore += "--------------\nPlayer Turn : \n" + GuiGame.guiGame.currentPlayer.name;
                        ScoreLabel.setText(theScore);
                              shields1label.setText(GuiGame.guiGame.players.get(0).name + " Shields : " + 
                                        GuiGame.guiGame.players.get(0).nOfShields);
                                if(GuiGame.guiGame.players.size()>1)
                                {
                                    shields2label.setText(GuiGame.guiGame.players.get(1).name + " Shields : " + 
                                    GuiGame.guiGame.players.get(1).nOfShields);
                                }
                    }
                });
                
                
                matrix[x][y].getStyleClass().addAll("buttons");
                myGridPane.add(matrix[x][y], y, x);
            }
        }
       
        return myGridPane;
    }

    
        
           @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        int i  = 0;
        int ttt = 0;
   while(i<=GuiGame.turntime)
    {
       Task task = new Task() {
              int i  = 0;
              int ttt = 0;
        @Override
        public Void call() {
          i++;
          ttt++;
            return null;
        }
    };
  final  int    x=i;
    task.setOnSucceeded((e) -> {
                Platform.runLater(new Runnable() {
    @Override
    public void run() {
         TimeLabel.setText( "Time : "+x);
       
    }
});
    
    });
    new Thread(task).start(); 
       
      i++;
            System.out.println(ttt);
            
      ttt++;
 
            try {
                Thread.sleep(1000);
               
            } catch (InterruptedException ex) {
             //   Logger.getLogger(GuiGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
 
   }
    }
        
        
    public void updatescores() {
        String theScore = "";
        for (int ii = 0; ii < GuiGame.guiGame.players.size(); ii++) theScore += GuiGame.guiGame.players.get(ii).name + " Score : " + GuiGame.guiGame.players.get(ii).currentScore + "\n";
        theScore += "--------------\nPlayer Turn : \n" + GuiGame.guiGame.currentPlayer.name;
        ScoreLabel.setText(theScore);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if (myGridPane != null) {
            GenerateMatrixOfButtons();
        }
        if (ScoreLabel != null) {
            updatescores();
        }
             if(shileds2!=null && GuiGame.guiGame.players.size()>1)
        {
            shileds2.setVisible(true);
        }
        else if(shileds2!=null && GuiGame.guiGame.players.size()==1)
        {
            shileds2.setVisible(false);
        }
            if(shields1label!=null )
             shields1label.setText(GuiGame.guiGame.players.get(0).name + " Shields : " + 
                                        GuiGame.guiGame.players.get(0).nOfShields);
                                if(GuiGame.guiGame.players.size()>1 && shields2label!=null)
                                {
                                    shields2label.setText(GuiGame.guiGame.players.get(1).name + " Shields : " + 
                                    GuiGame.guiGame.players.get(1).nOfShields);
                                }
    }    
    
}
