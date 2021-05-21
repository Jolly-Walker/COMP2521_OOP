package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class GameWinScreen {
	private Stage stage;
    private String title;
    private GameWinController controller;
    private Scene scene;
    private AudioClip wonSound;
    
    public GameWinScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Victory!";

        this.wonSound = new AudioClip(getClass().getResource("sounds/winSound.mp3").toString());
        controller = new GameWinController();
        controller.setStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameWin.fxml"));
        loader.setController(controller);
        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }
    
	public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        wonSound.play(1, 0, 1.5, 0, 1);
    }

    public GameWinController getController() {
        return controller;
    }
}