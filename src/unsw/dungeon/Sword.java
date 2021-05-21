package unsw.dungeon;

import javafx.scene.media.AudioClip;

public class Sword extends Entity {

	private AudioClip swordPickSound;
	
	public Sword(int x, int y) {
        super(x, y);
        this.swordPickSound = new AudioClip(getClass().getResource("sounds/swordpickup.mp3").toString());
    }
	
    public void collect (Dungeon d) {
    	swordPickSound.play();
		d.getPlayer().setSword(new SwordState5());
		d.setPlayerView(d.getPlayer().getIsInvincible());
		d.removeEntity(this);
    }
}
