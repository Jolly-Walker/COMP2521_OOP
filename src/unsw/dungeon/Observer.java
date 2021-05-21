package unsw.dungeon;

import java.util.List;

public interface Observer {
	public void update (Subject obj);
	public void addObserverTo (List<Entity> entities);
}
