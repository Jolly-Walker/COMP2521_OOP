package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameWinController {

	StartScreen startScreen;
	Stage stage;

    @FXML
    private Button menuBttn;

    @FXML
    void backHandler(ActionEvent event) {
    	startScreen.start();
    }


	public void setStage(Stage stage) {
		this.stage = stage;
		
	}
	
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
		
	}
}
