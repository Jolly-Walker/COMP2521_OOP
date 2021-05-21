package unsw.dungeon;

public class ExitGoal implements Goal{

	private Player player;
	
	public ExitGoal(Player player) {
		this.player = player;
	}

	@Override
	public boolean isDone() {
		return player.isExit();
	}
	
	
	
}
