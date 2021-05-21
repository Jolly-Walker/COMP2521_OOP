package unsw.dungeon;

public class Door extends Entity{

	private int id;
	private boolean locked;
	/**
	 * Constructor 
	 * @param x position
	 * @param y position
	 * @param id of door
	 */
    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.locked = true;
    }
    /**
     * Method to unlock and open the door
     * @param kid - int
     * @return boolean
     */
    public boolean unlock(int kid) {
    	if (this.id == kid) {
    		this.locked = false;
    		return true;
    	} 
    	return false;
    }

    /**
     * Method to check if entity is blocked at a given location
     * @param x position
     * @param y position
     * @param d - Dungeon
     * @return true if it is blocked
     */
    public boolean isBlocked(int x, int y, Dungeon d) {
    	if (this.locked == true) {
    		int kid = d.getPlayer().getKey();
    		if (unlock(kid)) {
    			d.getPlayer().useKey(this);
    		}
    	} 
    	return this.locked;
    }

}
