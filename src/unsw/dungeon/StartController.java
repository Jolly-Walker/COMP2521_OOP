package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
	MenuScreen menuScreen;
	InstructionScreen instructScreen;
	Stage stage;
    @FXML
    private Button startBtn;
    private Button insBtn;
    
    @FXML
    void handleStartBtn(ActionEvent event) {
    	menuScreen.start();
    }
    
    @FXML
    void handleInsBtn(ActionEvent event) {
    	instructScreen.start();
    }

	public void setStage(Stage stage) {
		this.stage = stage;
		
	}
	
	public void setMenuScreen(MenuScreen menuScreen) {
		this.menuScreen = menuScreen;
		
	}
	
	public void setInstructionScreen(InstructionScreen instructScreen) {
		this.instructScreen = instructScreen;
	}
	
	public MenuScreen getMenuScreen() {
		return menuScreen;
	}
}
