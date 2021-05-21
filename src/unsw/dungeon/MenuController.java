package unsw.dungeon;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MenuController {

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;

	private Stage stage;
	private DungeonScreen dungeonScreen1;
	private DungeonScreen dungeonScreen2;
	private DungeonScreen dungeonScreen3;
	private DungeonScreen dungeonScreen4;
	private DungeonScreen dungeonScreen5;
	GameOverScreen gameOverScreen;
	GameWinScreen gameWinScreen;
	
    public MenuController(GameOverScreen gameOverScreen, GameWinScreen gameWinScreen) {
		this.gameOverScreen = gameOverScreen;
		this.gameWinScreen = gameWinScreen;
	}

    @FXML
    void handleBtn1(ActionEvent event) throws IOException {
    	dungeonScreen1 = new DungeonScreen(stage, "marking.json");
    	dungeonScreenSetup(dungeonScreen1);
    	dungeonScreen1.start();
    }


	@FXML
    void handleBtn2(ActionEvent event) throws IOException {
		dungeonScreen2 = new DungeonScreen(stage, "boulders.json");
    	dungeonScreenSetup(dungeonScreen2);
    	dungeonScreen2.start();
    }

	@FXML
    void handleBtn3(ActionEvent event) throws IOException {
		dungeonScreen3 = new DungeonScreen(stage, "advanced.json");
    	dungeonScreenSetup(dungeonScreen3);
    	dungeonScreen3.start();
    }

	@FXML
    void handleBtn4(ActionEvent event) throws IOException {
		dungeonScreen4 = new DungeonScreen(stage, "portals.json");
    	dungeonScreenSetup(dungeonScreen4);
    	dungeonScreen4.start();
    }

	@FXML
    void handleBtn5(ActionEvent event) throws IOException {
		dungeonScreen5 = new DungeonScreen(stage, "massacre.json");
    	dungeonScreenSetup(dungeonScreen5);
    	dungeonScreen5.start();
    }

	public void setStage(Stage stage) {
		this.stage = stage;
		
	}

	public DungeonScreen getDungeonScreen1() {
		return this.dungeonScreen1;
	}

	private void dungeonScreenSetup(DungeonScreen dungeonScreen) {
		dungeonScreen.getController().setGameOverScreen(gameOverScreen);
		dungeonScreen.getController().setGameWinScreen(gameWinScreen);
	}
}
