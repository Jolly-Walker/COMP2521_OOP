package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity {

	private Dungeon dungeon;
	/**
	 * Constructor
	 * @param dungeon - Dungeon
	 * @param x position
	 * @param y position
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
	/**
	 * Method to check if there is another entity on it's path blocking movement
	 */
	public boolean isBlocked (int x, int y, Dungeon d) {
		if (x < 0 || y < 0 || x > d.getWidth() - 1 || y > d.getHeight() - 1) return true; 

		ArrayList <Entity> entities = d.switchGet(x , y, 1);
		Entity checkSwitch = d.switchGet(this.getX(), this.getY());
		boolean result = true;
		if (entities.isEmpty()) {
			if (checkSwitch instanceof Switch) {
				((Switch) checkSwitch).deactivateSwitch();
			}
			move_boulder (x,y);
			d.checkGoals();
			return false;
		} 
		
		for (Entity e : entities) {
			if (e instanceof Switch) {
				((Switch) e).activateSwitch();
				if (checkSwitch instanceof Switch) {
					((Switch) checkSwitch).deactivateSwitch();
				}
				Entity e2 = d.getEntity(x, y);
				if (e2 != null) return true;
				move_boulder (x,y);
				result = false;
				d.checkGoals();	
			} 
		}

		return result;
	}
	/**
	 * Method to move the boulder to set location
	 * @param x  position
	 * @param y position
	 */
	public void move_boulder (int x, int y) {
		x().set(x);
		y().set(y);
	}
	
	
}
