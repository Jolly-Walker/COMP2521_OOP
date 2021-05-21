package unsw.dungeon;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;

public class Switch extends Entity implements Subject{

	private boolean isActivate;
	private ArrayList <Observer> obs;
	private AudioClip switchSound;
	
	public Switch(int x, int y) {
        super(x, y);
		this.isActivate = false;
		this.obs = new ArrayList<Observer>();
		this.switchSound = new AudioClip(getClass().getResource("sounds/switch.mp3").toString());
	}
	
	public void activateSwitch() {
		if (isActivate) return;
		this.isActivate = true;
		switchSound.play();
		notifyObservers();
	}
	
	public void deactivateSwitch() {
		if (!isActivate) return;
		this.isActivate = false;
		switchSound.play(1.75, 0, 1.5, 0, 1);
		notifyObservers();
	}

	public boolean getActivateStatus() {
		return this.isActivate;
	}


	@Override
	public void addObserver(Observer o) {
		obs.add(o);
	}


	@Override
	public void notifyObservers() {
		for(Observer o: obs) {
			o.update(this);
		}
	}

}
