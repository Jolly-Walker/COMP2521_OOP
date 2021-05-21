package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class GameOverController {

	StartScreen startScreen;
	Stage stage;
	private AudioClip loseSound;
	
    @FXML
    private Button mainMenuBtn;

    @FXML
    private Button exitBtn;
    

	public void setStage(Stage stage) {
		this.stage = stage;
		
	}
	
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
		
	}
	
    @FXML
    void handleExitButton(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void handleMainMenuBtn(ActionEvent event) throws IOException {
    	startScreen.start();
    	loseSound.stop();
    }

    void setSound(AudioClip loseSound) {
    	this.loseSound = loseSound;
    }
}
