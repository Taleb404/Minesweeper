/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import MineGui.GuiGame;
import MineGui.GuiGameController;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 *
 * @author Taleb
 */
public class GuiPlayer extends Player implements Serializable{

    public GuiPlayer(String name) 
    {
        this.name = name;
        this.currentScore = 0;
        this.nOfShields = 0;
        illegagamemove = new IllegalGameMove();
        illegalsquarename = new IllegalSquareName();
     }
    
    @Override
    public PlayerMove GetPlayerMove() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        System.out.println("vvvvvvvvvvvvvvvvvvvvvvvv");
      
      Task task = new Task<Void>() {
  @Override
  public Void call() throws Exception {
    int i = 0;
        Thread.sleep(3000);
    while (true) {
      final int finalI = i;
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
        //  GuiGameController.TimeLabel.setText("" + finalI);
        }
      });
      i++;
      Thread.sleep(1000);
    }
  }
};
Thread th = new Thread(task);
th.setDaemon(true);
th.start();
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
