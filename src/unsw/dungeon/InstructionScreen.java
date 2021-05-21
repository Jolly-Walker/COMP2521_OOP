package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstructionScreen {

	    private Stage stage;
	    private String title;
	    private InstructionController controller;
	    private Scene scene;

	    public InstructionScreen(Stage stage) throws IOException {
	        this.stage = stage;
	        title = "Info Screen";
	        controller = new InstructionController();
	        controller.setStage(stage);
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Instruction.fxml"));
	        loader.setController(controller);
	        // load into a Parent node called root
	        Parent root = loader.load();
	        scene = new Scene(root);
	        scene.getStylesheets().addAll(this.getClass().getResource("instruct.css").toExternalForm());
	        root.requestFocus();
	    }

	    public void start() {
	        stage.setTitle(title);
	        stage.setScene(scene);
	        stage.show();
	    }

	    public InstructionController getController() {
	        return controller;
	    }
	}
