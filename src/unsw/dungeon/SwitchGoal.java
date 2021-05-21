package unsw.dungeon;

import java.util.List;

public class SwitchGoal implements Goal, Observer{

	private int numSwitch;
	private int countSwitchActivated;
	
	public SwitchGoal(int count) {
		this.numSwitch = count;
		this.countSwitchActivated = 0;
	}
	
	public int getNumSwitch() {
		return numSwitch;
	}
	
	public int getCountSwitchActivated() {
		return countSwitchActivated;
	}

	@Override
	public void update(Subject obj) {
		if (!(obj instanceof Switch)) return;
		Switch s = (Switch) obj;
		if (s.getActivateStatus()) {
			this.countSwitchActivated += 1;
		}
		else {
			this.countSwitchActivated -= 1;
		}
	}

	@Override
	public boolean isDone() {
		if (this.countSwitchActivated == this.numSwitch) {
			return true;
		}
		return false;
	}

	@Override
	public void addObserverTo(List<Entity> entities) {
		for (Entity e: entities) {
			if (e instanceof Switch) {
				((Switch) e).addObserver(this);
			}
		}
	}
}
