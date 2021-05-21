package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuScreen {
	
    private Stage stage;
    private String title;
    private MenuController controller;
    private Scene scene;
    
    public MenuScreen(Stage stage, GameOverScreen gameOverScreen, GameWinScreen gameWinScreen) throws IOException {
        this.stage = stage;
        title = "Menu Screen";
        controller = new MenuController(gameOverScreen, gameWinScreen);
        controller.setStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("levelMenu.fxml"));
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
    }

    public MenuController getController() {
        return controller;
    }

	public void startNew() {
		stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
	}
}