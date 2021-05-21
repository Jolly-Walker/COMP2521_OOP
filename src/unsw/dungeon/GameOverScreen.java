package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class GameOverScreen {
	private Stage stage;
    private String title;
    private GameOverController controller;
    private Scene scene;
    private AudioClip loseSound;
    
    public GameOverScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Game Over";

        controller = new GameOverController();
        controller.setStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameOver.fxml"));
        loader.setController(controller);
        this.loseSound = new AudioClip(getClass().getResource("sounds/evilmorty.mp3").toString());
        this.controller.setSound(loseSound);
        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }
    
	public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        loseSound.play(1, 0, 0.95, 0, 1);
    }

    public GameOverController getController() {
        return controller;
    }
}