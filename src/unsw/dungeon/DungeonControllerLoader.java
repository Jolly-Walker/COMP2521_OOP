package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image swordImage;
    private Image enemyImage;
    private Image boulderImage;
    private Image floorSwitchImage;
    private Image potionImage;
    private Image treasureImage;
    private Image exitImage;
    private Image portalImage;
    private Image keyImage;
    private Image lockedDoorImage;
    private Image unlockedDoorImage;
    private Image invinciblePlayerImage;
    private Image attackAnimation;
    private Image playerSwordImage;
    
    private Map<Entity,Node> entityViews;
    private DungeonController dc;
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/morty.png");
        wallImage = new Image("/wall2.png");
        swordImage = new Image("/greatsword_1_new.png");
        enemyImage = new Image("/rick.png");
        boulderImage = new Image("/boulder2.png");
        floorSwitchImage = new Image("/pressure_plate.png");
        potionImage = new Image("/brilliant_blue_new.png");
        treasureImage = new Image("/gem.png");
        exitImage = new Image("/exit.png");
        portalImage = new Image("/portalgreen.png");
        keyImage = new Image("/key.png");
        lockedDoorImage = new Image("/closed_door.png");
        unlockedDoorImage = new Image("/open_door.png");
        invinciblePlayerImage = new Image("/firemorty.png");
        attackAnimation = new Image("/attack.png");
        playerSwordImage = new Image("/mortysword.png");
        entityViews = new HashMap<Entity, Node>();
        this.dc = null;
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }
    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    
    @Override
	public void onLoad(Switch floorSwitch) {
    	ImageView view = new ImageView(floorSwitchImage);
        addEntity(floorSwitch, view);
	}

	@Override
	public void onLoad(Potion potion) {
		ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
	}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
	}
	
	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
	}
	
	@Override
	public void onLoad(Portal portal) {
		ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
	}

	@Override
	public void onLoad(Door door) {
		ImageView view = new ImageView(lockedDoorImage);
        addEntity(door, view);
	}

	@Override
	public void onLoad(DoorKey key) {
		ImageView view = new ImageView(keyImage);
        addEntity(key, view);
	}
	
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        entityViews.put(entity, node);
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
    	DungeonController dc = new DungeonController(load(), entities, this);
    	loadGoal();
    	
    	this.dc = dc;
    	return dc;
    }

    public ImageView getEntityView(Entity entity) {
    	ImageView tmp = (ImageView) entityViews.get(entity);
    	return tmp;
    }

	public ImageView unlockDoor(Entity e) {
		ImageView oldDoor = (ImageView) entityViews.get(e);
		entityViews.remove(e, oldDoor);
		entities.remove(oldDoor);
		ImageView newDoor = new ImageView(unlockedDoorImage);
        addEntity(e, newDoor);
		return newDoor;
	}

	public ImageView getNewPlayer(Player player, boolean potionState, boolean hasSword) {
		ImageView oldPlayer = (ImageView) entityViews.get(player);
		entityViews.remove(player, oldPlayer);
		entities.remove(oldPlayer);
		ImageView newPlayer = null;
		if (potionState) {
			newPlayer = new ImageView(invinciblePlayerImage);
		} else {
			if (hasSword) {
				newPlayer = new ImageView(playerSwordImage);
			} else {
				newPlayer = new ImageView(playerImage);
			}
		}
		addEntity(player, newPlayer);
		return newPlayer;
	}

	public ImageView getAttack() {
		return new ImageView(attackAnimation);
	}
    
    
}
