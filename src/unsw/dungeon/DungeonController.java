package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    private List<ImageView> initialEntities;
    
    private Player player;
    private Dungeon dungeon;
    private DungeonControllerLoader dungeonCL;
	private Timeline timing;
	private Timeline moveStop;
	private DungeonScreen dungeonScreen;
	private GameOverScreen gameOverScreen;
	private GameWinScreen gameWinScreen;
    private List<ImageView> attacks;
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonControllerLoader dungeonCL) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.dungeonCL = dungeonCL;
        this.dungeon.set_dc(this);
        this.moveStop = new Timeline();
        this.timing = new Timeline();
        this.attacks = new ArrayList<ImageView>();

		timing.setCycleCount(Timeline.INDEFINITE);
		KeyFrame autoTick = new KeyFrame(Duration.millis(700), e ->{dungeon.tick();} );
		timing.getKeyFrames().add(autoTick);
		
		KeyFrame restrictMovement = new KeyFrame(Duration.millis(200), e ->{removeAttacks();} );
    	moveStop.getKeyFrames().add(restrictMovement);
    }

    private void removeAttacks() {
		for (ImageView attack: attacks) {
			squares.getChildren().remove(attack);
		}
		
		attacks.removeAll(attacks);
	}

	@FXML
    public void initialize() {
        Image ground = new Image("/space.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
    	
    	if (moveStop.getStatus() != Status.RUNNING) {
    		processKey(event);
    		moveStop.play();
    	}
        
    }

	private void processKey(KeyEvent event) {
		switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case X:
        	player.useSword();
        	break;
        case E:
        	player.dropKey();
        default:
            break;
        }
	}

	public void removeEntity(Entity entity) {
		ImageView tmp = dungeonCL.getEntityView(entity);
		squares.getChildren().remove(tmp);
	}

	public void addEntity(Entity entity) {
		ImageView tmp = dungeonCL.getEntityView(entity);
		squares.getChildren().add(tmp);
		
	}

	public void updateDoor(Entity e) {
		ImageView oldDoor = dungeonCL.getEntityView(e);
		squares.getChildren().remove(oldDoor);
		ImageView newDoor = dungeonCL.unlockDoor(e);
		squares.getChildren().add(newDoor);
		
	}

	public void start() {
		this.dungeonScreen.start();
		
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
		
	}

	public void startEnemy() {
		timing.play();
	}

	public void winningScreen() {
		timing.stop();
		moveStop.stop();
		gameWinScreen.start();
	}

	public void gameOverScreen() {
		timing.stop();
		moveStop.stop();
		gameOverScreen.start();
	}

	public void setGameOverScreen(GameOverScreen gameOverScreen) {
		this.gameOverScreen = gameOverScreen;
		
	}

	public void setGameWinScreen(GameWinScreen gameWinScreen) {
		this.gameWinScreen = gameWinScreen;
		
	}

	public void setPlayerView(boolean potionState) {
		ImageView oldPlayer = dungeonCL.getEntityView(player);
		squares.getChildren().remove(oldPlayer);
		ImageView newPlayer = dungeonCL.getNewPlayer(player, potionState, player.hasSword());
		squares.getChildren().add(newPlayer);
		
	}

	public void addSwordAttack(int i, int j) {
		ImageView attack = dungeonCL.getAttack();
		GridPane.setColumnIndex(attack, i);
        GridPane.setRowIndex(attack, j);
        squares.getChildren().add(attack);
        attacks.add(attack);
	}

}

