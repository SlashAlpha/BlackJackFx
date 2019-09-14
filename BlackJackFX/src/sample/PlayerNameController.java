package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PlayerNameController {

    @FXML
    TextField playerName;

    public String getplayerName(){
       String player= playerName.getText();
       return player;
    }

}
