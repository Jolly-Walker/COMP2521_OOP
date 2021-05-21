package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity implements Subject {
	
	private Dungeon dungeon;
	ArrayList<Observer> observers;

	/**
	 * Constructor
	 * @param x position
	 * @param y position
	 * @param dungeon
	 */
	public Enemy(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.dungeon = dungeon;
        this.dungeon.addEnemies(this);
        this.dungeon.setnEnemies(this.dungeon.getnEnemies() + 1);
        this.observers = new ArrayList<Observer>() ;
    }
	
	/**
	 * Method using Manhattan Distance calculation for enemy intelligence.
	 * @param x1 position
	 * @param y1 position
	 * @param x2 position
	 * @param y2 position
	 * @return
	 */
	private int minDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	/**
	 * Method to determine which direction enemy moves towards the player
	 */
	public void moveTowardsPlayer() {
		Player player = dungeon.getPlayer();

		int[] moveDistance = new int[5];
		moveDistance[0] = minDist(player.getX(), player.getY(), this.getX(), this.getY() - 1);
		moveDistance[1] = minDist(player.getX(), player.getY(), this.getX(), this.getY() + 1);
		moveDistance[2] = minDist(player.getX(), player.getY(), this.getX() - 1, this.getY());
		moveDistance[3] = minDist(player.getX(), player.getY(), this.getX() + 1, this.getY());
		// diffMoves[4] = minDist(player.getX(), player.getY(), this.getX(), this.getY());

		boolean[] canMoves = new boolean[5];
		canMoves[0] = !isBlocked(this.getX(), this.getY() - 1);
		canMoves[1] = !isBlocked(this.getX(), this.getY() + 1);
		canMoves[2] = !isBlocked(this.getX() - 1, this.getY());
		canMoves[3] = !isBlocked(this.getX() + 1, this.getY());
		canMoves[4] = true;

		for (int i = 0; i < 4; i++) {
			int minMove = moveDistance[i];
			int moveIndex = 0;
			for (int j = 0; j < 4; j++) {
				if (moveDistance[j] < minMove) {
					minMove = moveDistance[j];
					moveIndex = j;
				}
			}
			if (canMoves[moveIndex]) {
				switch (moveIndex) {
				case 0:
					moveUp();
					break;
				case 1:
					moveDown();
					break;
				case 2:
					moveLeft();
					break;
				case 3:
					moveRight();
					break;
				}
				return;
			} else {
				moveDistance[moveIndex] = Integer.MAX_VALUE;
			}
		}
	}
	
	/**
	 * Method to determine which direction enemy to move away from the player
	 */
	public void moveAwayPlayer() {
		Player player = dungeon.getPlayer();

		int[] diffMoves = new int[5];
		diffMoves[0] = minDist(player.getX(), player.getY(), this.getX(), this.getY() - 1);
		diffMoves[1] = minDist(player.getX(), player.getY(), this.getX(), this.getY() + 1);
		diffMoves[2] = minDist(player.getX(), player.getY(), this.getX() - 1, this.getY());
		diffMoves[3] = minDist(player.getX(), player.getY(), this.getX() + 1, this.getY());
		// diffMoves[4] = minDist(player.getX(), player.getY(), this.getX(), this.getY());

		boolean[] canMoves = new boolean[5];
		canMoves[0] = !isBlocked(this.getX(), this.getY() - 1);
		canMoves[1] = !isBlocked(this.getX(), this.getY() + 1);
		canMoves[2] = !isBlocked(this.getX() - 1, this.getY());
		canMoves[3] = !isBlocked(this.getX() + 1, this.getY());
		canMoves[4] = true;

		for (int i = 0; i < 4; i++) {
			int maxMove = diffMoves[i];
			int idxMove = 0;
			for (int j = 0; j < 4; j++) {
				if (diffMoves[j] > maxMove) {
					maxMove = diffMoves[j];
					idxMove = j;
				}
			}
			if (canMoves[idxMove]) {
				switch (idxMove) {
				case 0:
					moveUp();
					break;
				case 1:

					moveDown();
					break;
				case 2:
					moveLeft();
					break;
				case 3:
					moveRight();

					break;
				}
				return;
			} else {
				diffMoves[idxMove] = Integer.MIN_VALUE;
			}
		}
	}
	
	
	
	public void moveUp() {
        if (getY() > 0 && !isBlocked(getX(), getY()-1))
            y().set(getY() - 1);
        // kill player method
        
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && !isBlocked(getX(), getY()+1))
            y().set(getY() + 1);
     // kill player method
    }

    public void moveLeft() {
        if (getX() > 0 && !isBlocked(getX()-1, getY()))
            x().set(getX() - 1);
     // kill player method
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && !isBlocked(getX()+1, getY()))
            x().set(getX() + 1);
     // kill player method
    }

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void notifyObservers() {
		for(Observer o: observers) {
			o.update(this);
		}
		
	}
	/**
	 * Method to determine if there is an Entity that should block enemy's movement
	 * @param x position
	 * @param y position
	 * @return true if it is blocked
	 */
	public boolean isBlocked (int x ,int y) {
    	Entity e = dungeon.getEntity(x, y);
    	if (e == null) {
    		return false;
    	} else if (e instanceof Wall) {
    		return true;
    		
    	} else if (e instanceof Boulder) {
    		return true;
    
    	} else if (e instanceof Door) {
    		return true;
    		
    	} else if (e instanceof Portal) {
    		return true;
    	} else if (e instanceof Enemy) {
    		return true;
    	}
    	return false;
	}
}
	
	

