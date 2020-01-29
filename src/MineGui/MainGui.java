package MineGui;

import mineswipper.NormalGame;
import mineswipper.Player;

public class MainGui extends NormalGame {

    @Override
    public void initGame() {
        super.initGame();
        GuiGame nnn = new GuiGame();
        nnn.main(this);
    }
}
