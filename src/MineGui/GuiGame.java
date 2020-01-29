package MineGui;

import com.sun.javafx.css.StyleManager;
import java.io.Serializable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mineswipper.NormalGame;

public class GuiGame extends Application  implements Serializable {

    public static NormalGame guiGame;

    public static Stage theGameStage;

    public static Scene scene;

    public static boolean canautoplayeruseshield;
    
    public static int turntime;
    @Override
    public void start(Stage primaryStage) throws Exception {
        theGameStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("GuiGame.fxml"));
        scene = new Scene(root);
        primaryStage.setTitle("MineSwepper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void main(NormalGame guiGame) {
        String[] args = new String[0];
        this.guiGame = guiGame;
    
        launch(args);
    }
}
