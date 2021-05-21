/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.AudioClip;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */

public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private DungeonController dungeonC;
    private List<Enemy> enemies;
    private int nEnemies;
    private int nTreasures;
    private Goal goal;
    private boolean isGameFinished;
    private AudioClip doorSound;
    
    /**
    * Constructor
    * @param width of the desired dungeon map
    * @param height of the desired dungeon map
    */
	public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.dungeonC = null;
        this.enemies = new ArrayList<Enemy>();
		this.setnEnemies(0);
		this.setnTreasures(0);
		this.goal = null;
		this.isGameFinished = false;
		this.doorSound = new AudioClip(getClass().getResource("sounds/door.mp3").toString());

    }

	public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void addEnemies(Enemy enemy) {
		enemies.add(enemy);
	} 
    
    public void removeEnemies(Enemy enemy) {
    	enemies.remove(enemy);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
	public int getnEnemies() {
		return nEnemies;
	}

	public void setnEnemies(int nEnemies) {
		this.nEnemies = nEnemies;
	}

	public int getnTreasures() {
		return nTreasures;
	}

	public void setnTreasures(int nTreasures) {
		this.nTreasures = nTreasures;
	}
	
	/**
	 * Method to check switches' status by checking is that a boulder above the switch
	 */
    public void checkSwitches() {
		for (Entity e: entities) {
			if (e instanceof Switch) {
				Entity check = getEntity(e.getX(), e.getY());
				if (check == null) continue;
				if (check instanceof Boulder) {
					((Switch) e).activateSwitch();
				}
			}
		}
	
	}
    
    /**
     * This method will return entity that on the given coordinate except player and switch.
     * @param x position
     * @param y position
     * @return Entity
     */
    public Entity getEntity(int x, int y) {
    	for (Entity e : entities) {
    		if (e.getX() == x && e.getY() == y) {
    			if (e instanceof Player || e instanceof Switch) {
    				continue;
    			} else {
    				return e;
    			}
    		};
    	};
		return null;
    }
    
    /**
     * This method is only for boulder to return switch
     * @param x position
     * @param y position
     * @return Entity
     */
    public ArrayList <Entity> switchGet(int x, int y, int z) {
    	ArrayList <Entity> obstacles = new ArrayList <Entity>(); 
    	for (Entity e : entities) {
    		if (e.getX() == x && e.getY() == y) {
    			if (e instanceof Player) {
    				continue;
    			} else {
    				obstacles.add(e);
    			}
    		};
    	};
		return obstacles;
    }
    public Entity switchGet(int x, int y) {
    	for (Entity e: entities) {
			if (e.getX() == x && e.getY() == y) {
				if (e instanceof Player) {
					continue;
				} else {
					return e;
				}
			}
    	}
		return null;
    }
    
    /**
     * This method is only called by player when the player use sword or moving 
     * and return list of enemies at the given coordinate.
     * @param x position
     * @param y position
     * @return ArrayList <Enemy>
     */
    public ArrayList <Enemy> getEnemies (int x, int y) {
    	ArrayList <Enemy> enemies2 = new ArrayList <Enemy> ();
    	for (Enemy e : enemies) {
    		if (e.getX() == x && e.getY() == y) {
    			enemies2.add(e);
    		};
    	}
    	return enemies2;
    }

    /**
     * This method is used to remove entity from the entities list in dungeon
     * if the entity is a enemy then decrement the nEnemies in dungeon and check goal
     * else if entity is a treasure then check goal
     * @param entity of an object
     */
    public void removeEntity(Entity entity) {
    	if (entity instanceof Enemy) {
    		removeEnemies((Enemy) entity);
    		setnEnemies(getnEnemies() - 1);
       		((Enemy) entity).notifyObservers();
     		checkGoals();
    	} else if (entity instanceof Treasure) {
    		((Treasure) entity).notifyObservers();
    		checkGoals();
    	}
    	entities.remove(entity);
    	if (dungeonC != null) dungeonC.removeEntity(entity);
	}
    
    /**
     * This method is used to search corresponding portal of the specific portal
     * @param target on entity
     * @return Entity
     */
    public Entity searchPortalEntity(Entity target) {
    	if (target instanceof Portal) {
	    	for (Entity e : entities) {
	    		if (e instanceof Portal && ((Portal) e).getPid() == -((Portal)target).getPid()) {
	    			return e;
	    		}
	    	}
    	}
		return target;
    }


    /**
     * This method is used to set the dungeon controller in dungeon.
     * @param dungeonController class
     */
	public void set_dc(DungeonController dungeonController) {
		this.dungeonC = dungeonController;
	}
	
	/**
	 * This method is used to restore the key back to the dungeon's entities list
	 * @param key for the door
	 */
	public void restoreKey(DoorKey key) {
		entities.add(key);
		if (dungeonC != null) dungeonC.addEntity(key);
	}

	/**
	 * This method is used to update the door open image after door is unlocked
	 * @param e Entity
	 */
	public void updateDoor(Entity e) {
		if (dungeonC != null) {
			dungeonC.updateDoor(e);
			doorSound.play();
		}
	}

	/**
	 * This method is used to set a goal to dungeon
	 * @param g Goal
	 */
	public void setGoal(Goal g) {
		this.goal = g;
	}
	
	/**
	 * This method is used to bind an observer to corresponding entities 
	 * @param o Observer
	 */
	public void addObserverToEntities(Observer o) {
		o.addObserverTo(entities);
	}

	/**
	 * This method is used to check the goal is done then invoke a winning screen.
	 */
	public void checkGoals() {
		if (goal.isDone()) {
			isGameFinished = true;
			dungeonC.winningScreen();
		}
	}

	/**
	 * This method is used to notify or update all enemies' movement by different situation.
	 * @param isInvincible boolean
	 */
	public void updateEnemies(boolean isInvincible) {
		for (Enemy e: enemies) {
			if (isInvincible) {
				((Enemy) e).moveAwayPlayer();;
			} else {
				((Enemy) e).moveTowardsPlayer();
			}
		}
		
	}

	/**
	 * This method is used to return game status.
	 * @return boolean
	 */
	public boolean isGameFinished() {
		return isGameFinished;
	}

	/**
	 * This method is used to call updateEnemies if player is having a potion or potion is finished
	 */
	public void tick() {
		if (player.isAlived()) {
			updateEnemies(player.getIsInvincible());
			player.check_alive();
		}
	}

	/**
	 * This method is used to set the screen to game win screen
	 */
	public void gameOverScreen() {
		dungeonC.gameOverScreen();
		
	}
	
	/**
	 * This method is used to update the player UI
	 * @param b - boolean
	 */
	public void setPlayerView(boolean b) {
		dungeonC.setPlayerView(b);
		
	}

	/**
	 * This method is used to show up the attack effect when player swing sword
	 * @param i position
	 * @param j position
	 */
	public void addSwordAttack(int i, int j) {
		dungeonC.addSwordAttack(i, j);
		
	}

}
