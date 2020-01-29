package MineGui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mineswipper.RandomAIPlayer;

public class OprtionMenuController implements Initializable {

    @FXML
    TextField gridwidthfield;

    @FXML
    TextField gridheightfield;

    @FXML
    TextField minesNumber;

    @FXML
    TextField shieldsNumber;

    @FXML
    TextField shieldsNumber2;
    
    @FXML
    CheckBox autoPlayerSheild;
    
    @FXML
    TextField TurnTime;
    
    @FXML
    private void SaveButton(ActionEvent event) throws IOException {
        try {
            Stage primaryStage = (Stage) gridheightfield.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("GuiGame.fxml"));
            primaryStage.getScene().setRoot(newRoot);
            primaryStage.show();
            String width = gridwidthfield.getText();
            GuiGame.guiGame.grid.setM(Integer.parseInt(width));
            String height = gridheightfield.getText();
            GuiGame.guiGame.grid.setN(Integer.parseInt(height));
            String mines = minesNumber.getText();
            GuiGame.guiGame.grid.setNumberOfMines(Integer.parseInt(mines));
           
            String time = TurnTime.getText();
          GuiGame.turntime = (Integer.parseInt(time));
            
     String shields = shieldsNumber.getText();
     String shields2 = shieldsNumber2.getText();
          GuiGame.guiGame.setShieldsForPlayers(Integer.parseInt(shields),Integer.parseInt(shields2));
      if (autoPlayerSheild.isSelected())
           {    
               GuiGame.canautoplayeruseshield = true;
                GuiGame.guiGame.canUseShields = true;
           }
            else
            {
            
                 GuiGame.canautoplayeruseshield = false;
                 GuiGame.guiGame.canUseShields = false;
            }
             //System.out.println(" nnnnnnnnnnnnnn "+  GuiGame.canautoplayeruseshield);
            GuiGame.guiGame.grid.initGrid();
            GuiGame.guiGame.initGame();
            
       
                  
          
        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
    }

    @FXML
    private void AdvancedSettings(ActionEvent event) throws IOException {
        try {
            Stage primaryStage = (Stage) gridwidthfield.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("AdvancedSetting.fxml"));
            primaryStage.getScene().setRoot(newRoot);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(gridwidthfield!=null)
            gridwidthfield.setText( GuiGame.guiGame.grid.getM()+"");
        if(gridheightfield!=null)
             gridheightfield.setText( GuiGame.guiGame.grid.getN()+"");
        if(minesNumber!=null)  
            minesNumber.setText( GuiGame.guiGame.grid.getNumberOfMines()+"");

    }
}
