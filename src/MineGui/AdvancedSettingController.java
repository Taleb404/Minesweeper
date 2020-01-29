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

public class AdvancedSettingController implements Initializable {

    @FXML
    TextField emptySquareScore;

    @FXML
    TextField withmine;

    @FXML
    TextField wihtoutmine;

    @FXML
    TextField autoreveal;

    @FXML
    TextField notmarkedmine;

    @FXML
    TextField onem;

    @FXML
    TextField twom;

    @FXML
    TextField threem;

    @FXML
    TextField fourm;

    @FXML
    TextField fivem;

    @FXML
    TextField sixm;

    @FXML
    TextField sevenm;

    @FXML
    TextField eightm;

    @FXML
    CheckBox enableautoreveal;

    @FXML
    CheckBox showallminesonlose;

    @FXML
    private void Save(ActionEvent event) throws IOException {
        try {
            Stage primaryStage = (Stage) emptySquareScore.getScene().getWindow();
            Parent newRoot = FXMLLoader.load(getClass().getResource("GuiGame.fxml"));
            primaryStage.getScene().setRoot(newRoot);
            GuiGame.guiGame.currentRules.AutoReveal = Integer.parseInt(autoreveal.getText());
            GuiGame.guiGame.currentRules.EmptySquare = Integer.parseInt(emptySquareScore.getText());
            GuiGame.guiGame.currentRules.MarkIsMine = Integer.parseInt(withmine.getText());
            GuiGame.guiGame.currentRules.MarkIsNotMine = Integer.parseInt(wihtoutmine.getText());
            GuiGame.guiGame.currentRules.OneNotMarkedScore = Integer.parseInt(notmarkedmine.getText());
            GuiGame.guiGame.currentRules.isNumericValue[1] = Integer.parseInt(onem.getText());
            GuiGame.guiGame.currentRules.isNumericValue[2] = Integer.parseInt(twom.getText());
            GuiGame.guiGame.currentRules.isNumericValue[3] = Integer.parseInt(threem.getText());
            GuiGame.guiGame.currentRules.isNumericValue[4] = Integer.parseInt(fourm.getText());
            GuiGame.guiGame.currentRules.isNumericValue[5] = Integer.parseInt(fivem.getText());
            GuiGame.guiGame.currentRules.isNumericValue[6] = Integer.parseInt(sixm.getText());
            GuiGame.guiGame.currentRules.isNumericValue[7] = Integer.parseInt(sevenm.getText());
            GuiGame.guiGame.currentRules.isNumericValue[8] = Integer.parseInt(eightm.getText());
            if (enableautoreveal.isSelected())
                GuiGame.guiGame.enableautoreveal = true;
            else
                GuiGame.guiGame.enableautoreveal = false;
            if (showallminesonlose.isSelected())
                GuiGame.guiGame.showtheminesonlose = true;
            else
                GuiGame.guiGame.showtheminesonlose = false;
            primaryStage.show();
        } catch (Exception e) {
        } finally {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(emptySquareScore!=null)
            emptySquareScore.setText(GuiGame.guiGame.currentRules.EmptySquare+"" );
           if(withmine!=null)
            withmine.setText(GuiGame.guiGame.currentRules.MarkIsMine+"" );
            if(wihtoutmine!=null)
            wihtoutmine.setText(GuiGame.guiGame.currentRules.MarkIsNotMine+"" );
            if(autoreveal!=null)
            autoreveal.setText(GuiGame.guiGame.currentRules.AutoReveal+"" );
            if(onem!=null)
            onem.setText(GuiGame.guiGame.currentRules.isNumericValue[1]+"" );
            if(twom!=null)
            twom.setText(GuiGame.guiGame.currentRules.isNumericValue[2]+"" );  
            if(threem!=null)
            threem.setText(GuiGame.guiGame.currentRules.isNumericValue[3]+"" );     
            if(fourm!=null)
            fourm.setText(GuiGame.guiGame.currentRules.isNumericValue[4]+"" );     
            if(fivem!=null)
            fivem.setText(GuiGame.guiGame.currentRules.isNumericValue[5]+"" );
            if(sixm!=null)
            sixm.setText(GuiGame.guiGame.currentRules.isNumericValue[6]+"" );    
             if(sevenm!=null)
            sevenm.setText(GuiGame.guiGame.currentRules.isNumericValue[7]+"" );
             if(eightm!=null)
            eightm.setText(GuiGame.guiGame.currentRules.isNumericValue[8]+"" );   
             if(enableautoreveal!=null)
                 if (GuiGame.guiGame.enableautoreveal == true)
                  enableautoreveal.setSelected(true);
            else
                enableautoreveal.setSelected(false);
             
                if (GuiGame.guiGame.showtheminesonlose == true)
                  showallminesonlose.setSelected(true);
            else
                showallminesonlose.setSelected(false);
    }
}
