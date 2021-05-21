package unsw.dungeon;

import javafx.application.Application;
import javafx.stage.Stage;

public class FrontendApplication extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GameOverScreen gameOverScreen = new GameOverScreen(primaryStage);
		GameWinScreen gameWinScreen = new GameWinScreen(primaryStage);
		StartScreen startScreen = new StartScreen(primaryStage);
		MenuScreen menuScreen = new MenuScreen(primaryStage, gameOverScreen, gameWinScreen);
		InstructionScreen instructScreen = new InstructionScreen(primaryStage);
		
		startScreen.getController().setMenuScreen(menuScreen);
		startScreen.getController().setInstructionScreen(instructScreen);
		instructScreen.getController().setStartScreen(startScreen);
		gameOverScreen.getController().setStartScreen(startScreen);
		gameWinScreen.getController().setStartScreen(startScreen);
		startScreen.start();
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
