package unsw.dungeon;

import java.util.ArrayList;

public class SwordState4 implements SwordState {

	@Override
	public void swingSword(Player player) {
		Dungeon d = player.getDungeon();
    	ArrayList <Enemy> top = d.getEnemies(player.getX(), player.getY() - 1);
    	ArrayList <Enemy>  bottom = d.getEnemies(player.getX(), player.getY() + 1);
    	player.kill_enemies(top);
    	player.kill_enemies(bottom);
    	player.addSwordAttack(player.getX(), player.getY() - 1);
    	player.addSwordAttack(player.getX(), player.getY() + 1);
		player.setSword((new SwordState3()));
		
	}
	
	@Override
	public boolean isSword() {
		return true;
	}
}
