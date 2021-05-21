package unsw.dungeon;

import java.util.List;

public class TreasureGoal implements Goal, Observer{
	
	private int numTreasure;
	private int pickedUpCount;	
	
	public TreasureGoal(int numTreasure) {
		
		this.numTreasure = numTreasure;
		this.pickedUpCount = 0;
	}

	@Override
	public void update(Subject obj) {
		pickedUpCount++;
		
	}

	@Override
	public boolean isDone() {
		if (pickedUpCount == numTreasure) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addObserverTo(List<Entity> entities) {
		for (Entity e: entities) {
			if (e instanceof Treasure) {
				((Treasure) e).addObserver(this);
			}
		}
	}

}
