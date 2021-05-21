package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
	
	private Stage stage;
	private String title;
	private Scene scene;
	private DungeonController controller;
	
	public DungeonScreen(Stage stage, String dungeonName) throws IOException {
		
		this.stage = stage;
		this.title = "Dungeon";
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeonName);

        this.controller = dungeonLoader.loadController();
        controller.setDungeonScreen(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        
        
        scene = new Scene(root);
        root.requestFocus();
	}
	
	public void start() {
		stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        controller.startEnemy();
	}
	
	public DungeonController getController() {
		return controller;
	}
}
