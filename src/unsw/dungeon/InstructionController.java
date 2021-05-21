package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InstructionController {
		
		StartScreen startScreen;
		Stage stage;
		
	    @FXML
	    private Button backBtn;
	    
	    @FXML
	    void handleBack(ActionEvent event) {
	    	startScreen.start();
	    }

		public void setStage(Stage stage) {
			this.stage = stage;
			
		}
		
		public void setStartScreen(StartScreen startScreen) {
			this.startScreen = startScreen;
			
		}
}
