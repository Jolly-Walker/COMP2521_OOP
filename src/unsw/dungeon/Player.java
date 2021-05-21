package unsw.dungeon;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private SwordState sword;
    private DoorKey key;
    private Boolean alive;
    private Boolean isInvincible;
    private int potionLife;
    private AudioClip killSound;
    private AudioClip swingSound;
    private AudioClip keyDropSound;
    private AudioClip fireSound;
    private AudioClip keyPickSound;
    private AudioClip ohmanSound;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.sword = new SwordState0();
        this.key = null;
        this.alive = true;
        this.isInvincible = false;
        this.potionLife = 0;
        this.killSound = new AudioClip(getClass().getResource("sounds/sword.mp3").toString());
        this.swingSound = new AudioClip(getClass().getResource("sounds/whoosh.mp3").toString());
        this.keyDropSound = new AudioClip(getClass().getResource("sounds/keydrop.mp3").toString());
        this.fireSound = new AudioClip(getClass().getResource("sounds/fireblast.mp3").toString());
        this.keyPickSound = new AudioClip(getClass().getResource("sounds/keypickup.mp3").toString());
        this.ohmanSound = new AudioClip(getClass().getResource("sounds/ohman.wav").toString());
    }

    public void moveUp() {
        if (getY() > 0 && !isBlocked(getX(), getY()-1, "up"))
            y().set(getY() - 1);
        pickup();
        decrementPotion();
        check_alive();
    }

	public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && !isBlocked(getX(), getY()+1, "down"))
            y().set(getY() + 1);
        pickup();
        decrementPotion();
        check_alive();
    }


	public void moveLeft() {
        if (getX() > 0 && !isBlocked(getX()-1, getY(), "left"))
            x().set(getX() - 1);
        pickup();
        decrementPotion();
        check_alive();
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && !isBlocked(getX()+1, getY(), "right"))
            x().set(getX() + 1);
        pickup();
        decrementPotion();
        check_alive();
    }
    
    /**
     * Called whenever player moves, to handle item pick-ups
     */
    private void pickup() {
		Entity currSpot = dungeon.getEntity(getX(), getY());
		if (currSpot != null) {
			currSpot.collect(dungeon);
		}
	}

    public boolean isBlocked (int x ,int y, String direction) {
    	Entity e = dungeon.getEntity(x, y);
    	if (e == null) {
    		return false;
    	} else {
    		int new_x = x;
    		int new_y = y;
    		if (direction == "up") {
    			new_y --;
    		} else if (direction == "down") {
    			new_y++;
    		} else if (direction == "right") {
    			new_x++;
    		} else {
    			new_x--;
    		}
    		return e.isBlocked(new_x, new_y, dungeon);
    	}
    }
    
    /**
     * Should only be called when player picks up sword or uses sword
     * @param sword
     */
    public void setSword(SwordState sword) {
    	this.sword = sword;
    }
    
    /**
     * Should only be called by Potion when player picks-up a Potion
     * @param e
     */
    public void obtainPotion (Entity e) {
		this.isInvincible = true;
		this.potionLife = 30;
		dungeon.setPlayerView(isInvincible);
		dungeon.removeEntity(e);
    }
    

    /**
     * Should only be called by DoorKey when player picks-up a DoorKey
     * @param e
     */
	public void obtainKey (DoorKey e) {
		if (this.key == null) {
			this.key = e;
			this.keyPickSound.play();
			dungeon.removeEntity(e);
		}
    }
    
    
    public void useSword() {
    	if(sword.isSword()) {
    		swingSound.play();
    	}
    	sword.swingSword(this);
    }
    
    /**
     * Only called by Sword states, to ensure AOE attack works
     * @param list of enemies
     */
    public void kill_enemies (ArrayList <Enemy> enemies) {
    	for (Enemy e : enemies) {
    		dungeon.removeEntity(e);
    		killSound.play();
    	}
    }
    
    /**
     * Called whenever player moves
     */
    public void check_alive() {
		ArrayList <Enemy> enemies = dungeon.getEnemies(getX(), getY());
		for (Enemy enemy: enemies) {
			if (enemy instanceof Enemy) {
				if (isInvincible) {
					fireSound.play();
					dungeon.removeEntity(enemy);
				} else {
					getKilled();	
				}
			}
		}
	}
 
    public void getKilled() {
    	this.alive = false;
    	dungeon.removeEntity(this);
    	dungeon.gameOverScreen();
    }
   
    public boolean isAlived() {
    	return this.alive;
    }

	public void dropKey() {
		if (key != null) {
			keyDropSound.play();
			key.x().set(getX());
			key.y().set(getY());
			dungeon.restoreKey(key);
			key = null;
		}
	}

	public boolean isExit() {
		Entity currSpot = dungeon.getEntity(getX(), getY());
		if (currSpot instanceof Exit) {
			return true;
		} else {
			return false;
		}
	}
	
	private void decrementPotion() {
    	if (isInvincible) {
        	potionLife--;
        	if (potionLife == 0) {
        		ohmanSound.play();
        		isInvincible = false;
        		dungeon.setPlayerView(isInvincible);
        	}
        }
	}

	public boolean getIsInvincible() {
		return isInvincible;
	}

	public int getKey() {
		if (this.key == null) return -1;
		else return this.key.getKid();
	}
	
	public void useKey(Door d) {
		this.key = null;
		dungeon.updateDoor(d);
	}
	
	public Dungeon getDungeon () {
		return this.dungeon;
	}
	
	public void addSwordAttack(int i, int j) {
		dungeon.addSwordAttack(i,j);
		
	}

	public boolean hasSword() {
		return sword.isSword();
	}
}
