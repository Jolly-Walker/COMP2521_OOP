package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }
    
    public boolean isBlocked (int x, int y, Dungeon d) {
    	return true;
    }

}
