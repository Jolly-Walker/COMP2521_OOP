package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {

    private Stage stage;
    private String title;
    private StartController controller;
    private Scene scene;

    public StartScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Start Screen";
        controller = new StartController();
        controller.setStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startScreen.fxml"));
        loader.setController(controller);
        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("start_style.css").toExternalForm());
        root.requestFocus();
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public StartController getController() {
        return controller;
    }
}