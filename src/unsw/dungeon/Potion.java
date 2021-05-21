package unsw.dungeon;

import javafx.scene.media.AudioClip;

public class Potion extends Entity{
	
	private AudioClip potionSound;
	
	public Potion(int x, int y) {
        super(x, y);
        this.potionSound = new AudioClip(getClass().getResource("sounds/potion2.mp3").toString());
    }
	
	
    public void collect (Dungeon d) {
    	d.getPlayer().obtainPotion(this);
    	potionSound.play();
    }
}
