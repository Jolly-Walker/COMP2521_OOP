package unsw.dungeon;

import javafx.scene.media.AudioClip;

public class DoorKey extends Entity {
	
	private int kid;
    
	public DoorKey(int x, int y, int kid) {
        super(x, y);
        this.kid = kid;
    }
	public int getKid() {
		return kid;
	}
	
    public void collect (Dungeon d) {
    	d.getPlayer().obtainKey(this);
    }
}
